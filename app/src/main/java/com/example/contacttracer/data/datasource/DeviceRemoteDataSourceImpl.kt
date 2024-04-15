package com.example.contacttracer.data.datasource

import com.example.contacttracer.data.api.ContactTracerDBService
import com.example.contacttracer.data.model.Device
import com.example.contacttracer.domain.RetrofitInstance
import retrofit2.Response

class DeviceRemoteDataSourceImpl(
    private val accessToken: String,
    private val retService: ContactTracerDBService
) :
    DeviceRemoteDataSource {


    override suspend fun getAllDevice(): Response<List<Device>> {
        return retService.getAllDevice(accessToken)
    }

    override suspend fun getDevice(_id: String): Response<Device> {
        return retService.getDevice(accessToken, _id)
    }

    override suspend fun insertDevice(_device: Device): Response<Device> {
        return retService.insertDevice(accessToken, _device)
    }

    override suspend fun patchDevice(_id: String, _device: Device): Response<Device> {
        return retService.patchDevice(accessToken, _id, _device)
    }

    override suspend fun deleteDevice(_id: String): Response<Device> {
        return retService.deleteDevice(accessToken, _id)
    }
}