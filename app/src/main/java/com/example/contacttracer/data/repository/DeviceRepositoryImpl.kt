package com.example.contacttracer.data.repository

import android.util.Log
import com.example.contacttracer.data.model.Device
import com.example.contacttracer.data.datasource.DeviceRemoteDataSource

import kotlin.Exception

class DeviceRepositoryImpl(private val deviceRemoteDataSource: DeviceRemoteDataSource) :
    DeviceRepository {
    override suspend fun getAllDevice(): List<Device>? {


        var deviceList: List<Device> = listOf()

        /*try {*/
        val response = deviceRemoteDataSource.getAllDevice()
        val body = response.body()
        if (body != null)
            deviceList = body
        /*} catch (exception: Exception) {
            Log.d("Error", exception.message.toString())
        }*/

        return deviceList
    }

    override suspend fun getDevice(_id: String): Device? {
        lateinit var device: Device

        try {
            val response = deviceRemoteDataSource.getDevice(_id)
            val body = response.body()
            if (body != null) {
                device = body
                return device
            }
        } catch (exception: Exception) {
            Log.i("Error", exception.message.toString())
        }

        return null
    }

    override suspend fun insertDevice(_device: Device): Device? {
        lateinit var device: Device

        try {
            val response = deviceRemoteDataSource.insertDevice(_device)
            val body = response.body()
            if (body != null) {
                device = body
                return device
            }
        } catch (exception: Exception) {
            Log.i("Error", exception.message.toString())
        }

        return null
    }

    override suspend fun patchDevice(_id: String, _device: Device): Device? {
        lateinit var device: Device

        try {
            val response = deviceRemoteDataSource.patchDevice(_id, _device)
            val body = response.body()
            if (body != null) {
                device = body
                return device
            }
        } catch (exception: Exception) {
            Log.i("Error", exception.message.toString())
        }

        return null
    }

    override suspend fun deleteDevice(_id: String): Device? {
        lateinit var device: Device

        try {
            val response = deviceRemoteDataSource.deleteDevice(_id)
            val body = response.body()
            if (body != null) {
                device = body
                return device
            }
        } catch (exception: Exception) {
            Log.i("Error", exception.message.toString())
        }

        return null
    }

}