package com.example.contacttracer.data.datasource

import com.example.contacttracer.data.model.Device
import retrofit2.Call
import retrofit2.Response


interface DeviceRemoteDataSource {
    suspend fun getAllDevice(): Response<List<Device>>

    suspend fun getDevice(_id: String): Response<Device>

    suspend fun insertDevice(_device: Device): Response<Device>

    suspend fun patchDevice(_id: String, _device: Device): Response<Device>

    suspend fun deleteDevice(_id: String): Response<Device>
}