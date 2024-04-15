package com.example.contacttracer.data.repository

import com.example.contacttracer.data.model.Device

interface DeviceRepository {

    suspend fun getAllDevice(): List<Device>?

    suspend fun getDevice(_id: String): Device?

    suspend fun insertDevice(_device: Device): Device?

    suspend fun patchDevice(_id: String, _device: Device): Device?

    suspend fun deleteDevice(_id: String): Device?

}