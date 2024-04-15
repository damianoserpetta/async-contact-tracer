package com.example.contacttracer.data.repository

import android.util.Log
import com.example.contacttracer.data.model.Contact
import com.example.contacttracer.data.datasource.ContactRemoteDataSource

class ContactRepositoryImpl(private val contactRemoteDataSource: ContactRemoteDataSource) :
    ContactRepository {
    override suspend fun getAllContact(_deviceId : String): List<Contact>? {
        lateinit var contactList: List<Contact>

        try {
            val response = contactRemoteDataSource.getAllContact(_deviceId)
            val body = response.body()
            if (body != null) {
                contactList = body
                return contactList
            }
        } catch (exception: Exception) {
            Log.i("Error", exception.message.toString())
        }

        return null
    }

    override suspend fun getContact(_id: String): Contact? {
        lateinit var contact: Contact

        try {
            val response = contactRemoteDataSource.getContact(_id)
            val body = response.body()
            if (body != null)
                contact = body
        } catch (exception: Exception) {
            Log.i("Error", exception.message.toString())
        }

        return contact
    }

    override suspend fun insertContact(_contact: Contact): Contact? {
        lateinit var contact: Contact

        try {
            val response = contactRemoteDataSource.insertContact(_contact)
            val body = response.body()
            if (body != null) {
                contact = body
                return contact
            }
        } catch (exception: Exception) {
            Log.i("Error", exception.message.toString())
        }

        return null
    }

    override suspend fun patchContact(_id: String, _contact: Contact): Contact? {
        lateinit var contact: Contact

        try {
            val response = contactRemoteDataSource.patchContact(_id, _contact)
            val body = response.body()
            if (body != null) {
                contact = body
                return contact
            }
        } catch (exception: Exception) {
            Log.i("Error", exception.message.toString())
        }

        return null
    }

    override suspend fun deleteContact(_id: String): Contact? {
        lateinit var contact: Contact

        try {
            val response = contactRemoteDataSource.deleteContact(_id)
            val body = response.body()
            if (body != null) {
                contact = body
                return contact
            }
        } catch (exception: Exception) {
            Log.i("Error", exception.message.toString())
        }

        return null
    }

}