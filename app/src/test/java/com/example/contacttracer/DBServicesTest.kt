package com.example.contacttracer

import android.util.Log
import com.example.contacttracer.config.SecretApiToken
import com.example.contacttracer.data.datasource.DeviceRemoteDataSourceImpl
import com.example.contacttracer.data.model.Device
import com.example.contacttracer.data.repository.DeviceRepositoryImpl
import com.example.contacttracer.domain.usecases.DeviceUseCases
import com.example.contacttracer.functions.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Response

internal class DBServicesTest {

    @Test
    fun getAllDevicesTest() {

        var mDeviceRemoteDataSource =
            DeviceRemoteDataSourceImpl(
                accessToken = ApiUtils.composeAccessToken(
                    SecretApiToken.tokenType,
                    SecretApiToken.token
                )
            )
        var mDeviceRepository = DeviceRepositoryImpl(mDeviceRemoteDataSource)
        var mDeviceUseCases = DeviceUseCases(mDeviceRepository)
        var devicesList: List<Device>?
        devicesList = mDeviceUseCases.getAllDevice()

        println("ciao")
    }


}