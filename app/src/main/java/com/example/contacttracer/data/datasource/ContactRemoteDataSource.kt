package com.example.contacttracer.data.datasource

import com.example.contacttracer.data.model.Contact
import retrofit2.Response


interface ContactRemoteDataSource {

    suspend fun getAllContact(_device : String): Response<List<Contact>>

    suspend fun getContact(_id: String): Response<Contact>

    suspend fun insertContact(_contact: Contact): Response<Contact>

    suspend fun patchContact(_id: String, _contact: Contact): Response<Contact>

    suspend fun deleteContact(_id: String): Response<Contact>
}