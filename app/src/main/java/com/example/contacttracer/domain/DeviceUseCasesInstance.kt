package com.example.contacttracer.domain

import com.example.contacttracer.config.SecretApiToken
import com.example.contacttracer.data.api.ContactTracerDBService
import com.example.contacttracer.data.datasource.DeviceRemoteDataSourceImpl
import com.example.contacttracer.data.repository.DeviceRepositoryImpl
import com.example.contacttracer.domain.usecases.DeviceUseCases
import com.example.contacttracer.functions.ApiUtils
import retrofit2.Retrofit

class DeviceUseCasesInstance {
    companion object {

        fun getDeviceUseCasesInstance(retService: ContactTracerDBService): DeviceUseCases {
            return DeviceUseCases(
                DeviceRepositoryImpl(
                    DeviceRemoteDataSourceImpl(
                        accessToken = ApiUtils.composeAccessToken(
                            SecretApiToken.tokenType,
                            SecretApiToken.token
                        ),
                        retService
                    )
                ),
            )
        }
    }
}