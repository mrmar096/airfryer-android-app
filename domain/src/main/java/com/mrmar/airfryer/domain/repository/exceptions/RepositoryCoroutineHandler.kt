package com.mrmar.airfryer.domain.repository.exceptions

import com.mrmar.airfryer.core.utils.Logger
import com.mrmar.airfryer.domain.errors.DomainError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class RepositoryCoroutineHandler(
    private val handler: (DomainError) -> Unit
) : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        when (exception) {
            is NoConnectionException -> handler(DomainError.NoConnectionError)
            is RepositoryException -> handler(DomainError.ServiceError)
            else -> Logger.error("Repository Coroutine unmanaged error: \n ${exception.stackTraceToString()}")
        }
    }
}