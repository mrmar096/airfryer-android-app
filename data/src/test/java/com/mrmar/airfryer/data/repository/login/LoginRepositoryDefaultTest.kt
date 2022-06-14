package com.mrmar.airfryer.data.repository.login

import com.mrmar.airfryer.data.datasources.cloud.api.DeviceApi
import com.mrmar.airfryer.data.datasources.cloud.model.response.CloudResponseModel
import com.mrmar.airfryer.data.datasources.local.dao.session.SessionContextDao
import com.mrmar.airfryer.data.datasources.local.entities.SessionContextEntity
import com.mrmar.airfryer.domain.repository.exceptions.NoSessionDetectedException
import com.mrmar.airfryer.domain.repository.exceptions.SessionExpiredException
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class LoginRepositoryDefaultTest {

    @Mock
    lateinit var deviceApi: DeviceApi

    @Mock
    lateinit var sessionContextDao: SessionContextDao

    @InjectMocks
    lateinit var repository: LoginRepositoryDefault

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test(expected = NoSessionDetectedException::class)
    fun checkUserSession_givenEmptySessionFromDao_shouldThrowNoSessionDetectedException() {
        runBlocking {
            whenever(sessionContextDao.getSessionContext()).thenReturn(null)

            repository.checkUserSession()
        }
    }

    @Test(expected = SessionExpiredException::class)
    fun checkUserSession_givenExpiredSessionFromDao_shouldThrowSessionExpiredException() {
        runBlocking {
            val mockEntity = SessionContextEntity("", "123124124124124")
            val mockResponse = CloudResponseModel(code = -1)

            whenever(sessionContextDao.getSessionContext()).thenReturn(mockEntity)
            whenever(deviceApi.getStatus(any())).thenReturn(mockResponse)

            repository.checkUserSession()
        }
    }

    @Test(expected = SessionExpiredException::class)
    fun checkUserSession_givenExpiredSessionFromDao_shouldRemoveSessionStored() {
        runBlocking {
            val mockEntity = SessionContextEntity("", "123124124124124")
            val mockResponse = CloudResponseModel(code = -1)

            whenever(sessionContextDao.getSessionContext()).thenReturn(mockEntity)
            whenever(deviceApi.getStatus(any())).thenReturn(mockResponse)

            repository.checkUserSession()

            verify(sessionContextDao).delete()
        }
    }

    @Test
    fun checkUserSession_givenNullSessionFromDao_shouldNoRemoveSessionStored() {
        runBlocking {
            val mockEntity = SessionContextEntity("", null)
            val mockResponse = CloudResponseModel(code = -1)

            whenever(sessionContextDao.getSessionContext()).thenReturn(mockEntity)
            whenever(deviceApi.getStatus(any())).thenReturn(mockResponse)

            try {
                repository.checkUserSession()
            } catch (ex: SessionExpiredException) {
            }

            verify(sessionContextDao, never()).delete()
        }
    }

    @Test
    fun checkUserSession_givenEmptySessionFromDao_shouldNoRemoveSessionStored() {
        runBlocking {
            val mockEntity = SessionContextEntity("", "")
            val mockResponse = CloudResponseModel(code = -1)

            whenever(sessionContextDao.getSessionContext()).thenReturn(mockEntity)
            whenever(deviceApi.getStatus(any())).thenReturn(mockResponse)

            try {
                repository.checkUserSession()
            } catch (ex: SessionExpiredException) {
            }

            verify(sessionContextDao, never()).delete()
        }
    }

    @Test
    fun handleCodeErrors_shouldDeleteSessionContext() {
        runBlocking {
            repository.handleCodeErrors(0, "")
            verify(sessionContextDao).delete()
        }
    }

    @Test
    fun doLogin() {
        //TODO add when available api call
    }
}