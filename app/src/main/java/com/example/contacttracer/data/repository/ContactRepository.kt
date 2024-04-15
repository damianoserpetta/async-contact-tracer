package com.example.contacttracer.data.repository

import com.example.contacttracer.data.model.Contact

interface ContactRepository {

    suspend fun getAllContact(_deviceId: String): List<Contact>?

    suspend fun getContact(_id: String): Contact?

    suspend fun insertContact(_contact: Contact): Contact?

    suspend fun patchContact(_id: String, _contact: Contact): Contact?

    suspend fun deleteContact(_id: String): Contact?

}