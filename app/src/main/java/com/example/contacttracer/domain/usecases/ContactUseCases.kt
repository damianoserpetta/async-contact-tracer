package com.example.contacttracer.domain.usecases

import com.example.contacttracer.data.model.Contact
import com.example.contacttracer.data.repository.ContactRepository

class ContactUseCases(private val contactRepository: ContactRepository) {

    suspend fun getAllContact(_deviceId : String): List<Contact>? = contactRepository.getAllContact(_deviceId)

    suspend fun getContact(_id: String): Contact? = contactRepository.getContact(_id)

    suspend fun insertContact(_contact: Contact): Contact? =
        contactRepository.insertContact(_contact)

    suspend fun patchContact(_id: String, _contact: Contact): Contact? =
        contactRepository.patchContact(_id, _contact)

    suspend fun deleteContact(_id: String): Contact? = contactRepository.deleteContact(_id)
}