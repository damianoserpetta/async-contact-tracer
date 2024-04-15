package com.example.contacttracer.data.datasource

import com.example.contacttracer.data.api.ContactTracerDBService
import com.example.contacttracer.data.model.Contact
import com.example.contacttracer.domain.RetrofitInstance
import retrofit2.Response

class ContactRemoteDataSourceImpl(
    private val accessToken: String,
    private val retService: ContactTracerDBService
) :
    ContactRemoteDataSource {

    override suspend fun getAllContact(_deviceId : String): Response<List<Contact>> {
        return retService.getAllContact(accessToken, _deviceId)
    }

    override suspend fun getContact(_id: String): Response<Contact> {
        return retService.getContact(accessToken, _id)
    }

    override suspend fun insertContact(_contact: Contact): Response<Contact> {
        return retService.insertContact(accessToken, _contact)
    }

    override suspend fun patchContact(_id: String, _contact: Contact): Response<Contact> {
        return retService.patchContact(accessToken, _id, _contact)
    }

    override suspend fun deleteContact(_id: String): Response<Contact> {
        return retService.deleteContact(accessToken, _id)
    }


}