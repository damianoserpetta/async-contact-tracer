package com.example.contacttracer.domain.usecases

import com.example.contacttracer.data.model.Device
import com.example.contacttracer.data.repository.DeviceRepository

class DeviceUseCases(private val deviceRepository: DeviceRepository) {

    suspend fun getAllDevice(): List<Device>? = deviceRepository.getAllDevice()

    suspend fun getDevice(_id: String): Device? = deviceRepository.getDevice(_id)

    suspend fun insertDevice(_device: Device): Device? = deviceRepository.insertDevice(_device)

    suspend fun patchDevice(_id: String, _device: Device): Device? =
        deviceRepository.patchDevice(_id, _device)

    suspend fun deleteDevice(_id: String): Device? = deviceRepository.deleteDevice(_id)

}