package com.example.contacttracer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacttracer.data.model.Contact

class ContactsViewModel : ViewModel() {

    private var _contacts = MutableLiveData<MutableList<Contact>>()
    val contacts: LiveData<MutableList<Contact>>
        get() = _contacts

    // Lista temporanea così da poter aggiornare il value di discoveredDevices, che triggera l'observer.
    private val tempContacts = mutableListOf<Contact>()

    fun addContact(_contact: Contact) {
        val contact: Contact = _contact
        tempContacts.add(contact)
        _contacts.value = tempContacts
    }


}