package com.mrmar.airfryer.data.repository

import com.mrmar.airfryer.core.utils.Logger
import com.mrmar.airfryer.domain.repository.exceptions.EndpointNotFoundException
import com.mrmar.airfryer.domain.repository.exceptions.NoConnectionException
import com.mrmar.airfryer.domain.repository.exceptions.RepositoryException
import com.mrmar.airfryer.domain.repository.exceptions.UnknownRepositoryException
import retrofit2.HttpException
import java.io.IOException
import java.net.InetAddress

internal abstract class BaseRepository {
    companion object {
        private const val ERROR_404 = 404
    }

    suspend fun <T> safe(execution: suspend () -> T): T {
        return try {
            execution()
        } catch (ex: Exception) {
            ex.printStackTrace()
            when (ex) {
                is IOException -> {
                    if (hasInternet()) {
                        Logger.error("Inaccessible: ${ex.message.orEmpty()}")
                        throw RepositoryException(ex.hashCode(), ex.message.orEmpty())
                    } else {
                        Logger.error("No internet connection: ${ex.message.orEmpty()}")
                        throw NoConnectionException
                    }
                }
                is HttpException -> {
                    val message = ex.localizedMessage ?: ex.message()
                    Logger.error("Network call error: Code: ${ex.code()} Message: $message")
                    throw handleCodeErrors(ex.code(), message)
                }
                else -> throw UnknownRepositoryException
            }
        }
    }

    private fun hasInternet(): Boolean {
        return try {
            !InetAddress.getByName("google.com").equals("")
        } catch (ex: Exception) {
            false
        }
    }

    protected open suspend fun handleCodeErrors(code: Int, message: String): Throwable {
        return when (code) {
            ERROR_404 -> EndpointNotFoundException(code)
            else -> RepositoryException(code, message)
        }
    }
}