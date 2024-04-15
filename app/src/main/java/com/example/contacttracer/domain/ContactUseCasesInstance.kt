package com.example.contacttracer.domain

import com.example.contacttracer.config.SecretApiToken
import com.example.contacttracer.data.api.ContactTracerDBService
import com.example.contacttracer.data.datasource.ContactRemoteDataSourceImpl
import com.example.contacttracer.data.repository.ContactRepositoryImpl
import com.example.contacttracer.domain.usecases.ContactUseCases
import com.example.contacttracer.functions.ApiUtils
import retrofit2.Retrofit

class ContactUseCasesInstance {
    companion object {

        fun getContactUseCaseInstance(retService: ContactTracerDBService): ContactUseCases {
            return ContactUseCases(
                ContactRepositoryImpl(
                    ContactRemoteDataSourceImpl(
                        accessToken = ApiUtils.composeAccessToken(
                            SecretApiToken.tokenType,
                            SecretApiToken.token
                        ),
                        retService
                    )
                )
            )
        }
    }
}