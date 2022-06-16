package com.mrmar.airfryer.data.repository.device

import com.mrmar.airfryer.data.datasources.cloud.api.DeviceApi
import com.mrmar.airfryer.data.datasources.cloud.model.mixed.cook.CookStatusMixed
import com.mrmar.airfryer.data.datasources.cloud.model.response.GenericResponseResultModel
import com.mrmar.airfryer.data.datasources.cloud.model.response.device.DeviceStatusResponseResult
import com.mrmar.airfryer.data.datasources.cloud.model.response.device.DeviceStatusResponseReturn
import com.mrmar.airfryer.data.datasources.local.dao.session.SessionContextDao
import com.mrmar.airfryer.data.datasources.local.entities.SessionContextEntity
import com.mrmar.airfryer.domain.models.CookSetup
import com.mrmar.airfryer.domain.models.DeviceStatus
import com.mrmar.airfryer.domain.models.Meal
import com.mrmar.airfryer.domain.repository.exceptions.SessionExpiredException
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class DeviceRepositoryDefaultTest {

    @Mock
    lateinit var deviceApi: DeviceApi

    @Mock
    lateinit var sessionContextDao: SessionContextDao

    @InjectMocks
    lateinit var repository: DeviceRepositoryDefault

    @Before
    fun setUpCoroutines() {
        MockitoAnnotations.openMocks(this)
    }

    @Test(expected = SessionExpiredException::class)
    fun getStatus_givenEmptySessionFromDao_shouldThrowSessionExpiredException() {
        runBlocking {
            whenever(sessionContextDao.getSessionContext()).thenReturn(null)

            repository.getStatus()
        }
    }

    @Test
    fun getStatus_givenCooking_shouldMapToDeviceStatus() {
        runBlocking {
            val mockEntity = SessionContextEntity("", "")
            whenever(sessionContextDao.getSessionContext()).thenReturn(mockEntity)
            val result =
                DeviceStatusResponseResult(status = DeviceStatusResponseReturn(CookStatusMixed.COOKING))
            val mockResponse = GenericResponseResultModel(result = result)
            whenever(deviceApi.sendOperation(any())).thenReturn(mockResponse)

            repository.getStatus() shouldBe DeviceStatus.COOKING
        }
    }

    @Test
    fun getStatus_givenStandBy_shouldMapToDeviceStatus() {
        runBlocking {
            val mockEntity = SessionContextEntity("", "")
            whenever(sessionContextDao.getSessionContext()).thenReturn(mockEntity)
            val result =
                DeviceStatusResponseResult(status = DeviceStatusResponseReturn(CookStatusMixed.STANDBY))
            val mockResponse = GenericResponseResultModel(result = result)
            whenever(deviceApi.sendOperation(any())).thenReturn(mockResponse)

            repository.getStatus() shouldBe DeviceStatus.ONLINE
        }
    }

    @Test
    fun getStatus_givenHeating_shouldMapToDeviceStatus() {
        runBlocking {
            val mockEntity = SessionContextEntity("", "")
            whenever(sessionContextDao.getSessionContext()).thenReturn(mockEntity)
            val result =
                DeviceStatusResponseResult(status = DeviceStatusResponseReturn(CookStatusMixed.HEATING))
            val mockResponse = GenericResponseResultModel(result = result)
            whenever(deviceApi.sendOperation(any())).thenReturn(mockResponse)

            repository.getStatus() shouldBe DeviceStatus.HEATING
        }
    }

    @Test
    fun getStatus_givenPauseCooking_shouldMapToDeviceStatus() {
        runBlocking {
            val mockEntity = SessionContextEntity("", "")
            whenever(sessionContextDao.getSessionContext()).thenReturn(mockEntity)
            val result =
                DeviceStatusResponseResult(status = DeviceStatusResponseReturn(CookStatusMixed.PAUSE_COOKING))
            val mockResponse = GenericResponseResultModel(result = result)
            whenever(deviceApi.sendOperation(any())).thenReturn(mockResponse)

            repository.getStatus() shouldBe DeviceStatus.COOKING
        }
    }

    @Test
    fun getStatus_givenNullCookStatus_shouldMapToDeviceStatus() {
        runBlocking {
            val mockEntity = SessionContextEntity("", "")
            whenever(sessionContextDao.getSessionContext()).thenReturn(mockEntity)
            val mockResponse = GenericResponseResultModel<DeviceStatusResponseResult>()
            whenever(deviceApi.sendOperation(any())).thenReturn(mockResponse)

            repository.getStatus() shouldBe DeviceStatus.OFFLINE
        }
    }

    @Test
    fun startCooking_shouldCallToSendOperation() {
        runBlocking {
            val mockEntity = SessionContextEntity("", "")
            whenever(sessionContextDao.getSessionContext()).thenReturn(mockEntity)
            val mockResponse = GenericResponseResultModel<DeviceStatusResponseResult>()
            whenever(deviceApi.sendOperation(any())).thenReturn(mockResponse)

            repository.startCooking(Meal("", 0, CookSetup(15, 5)))

            verify(deviceApi).sendOperation(any())
        }
    }

    @Test
    fun finishCooking_shouldCallToSendOperation() {
        runBlocking {
            val mockEntity = SessionContextEntity("", "")
            whenever(sessionContextDao.getSessionContext()).thenReturn(mockEntity)
            val mockResponse = GenericResponseResultModel<DeviceStatusResponseResult>()
            whenever(deviceApi.sendOperation(any())).thenReturn(mockResponse)

            repository.finishCooking()

            verify(deviceApi).sendOperation(any())
        }
    }
}