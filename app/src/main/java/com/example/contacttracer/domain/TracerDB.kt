package com.example.contacttracer.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacttracer.data.api.ContactTracerDBService
import com.example.contacttracer.data.model.Contact
import com.example.contacttracer.data.model.Device
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class TracerDB(
) : ViewModel() {

    private var mAllDevicesResult = MutableLiveData<List<Device>>()
    val devices: LiveData<List<Device>>
        get() = mAllDevicesResult

    private var mAllContactsResult = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>>
        get() = mAllContactsResult

    // API variables
    private val retService =
        RetrofitInstance.getRetrofitInstance().create(ContactTracerDBService::class.java)

    private var mDeviceUseCases = DeviceUseCasesInstance.getDeviceUseCasesInstance(retService)
    private var mContactUseCase = ContactUseCasesInstance.getContactUseCaseInstance(retService)


    fun getAllDevice(){
        viewModelScope.launch {
            mAllDevicesResult.value = mDeviceUseCases.getAllDevice()!!
        }
    }

    fun getDevice(deviceID: String){
        viewModelScope.launch {
            mDeviceUseCases.getDevice(deviceID)!!
        }
    }

    fun insertDevice(_device: Device) {
        TODO()
    }

    fun patchDevice(deviceID: String, device: Device) {
        TODO()
    }

    fun deleteDevice(deviceID: String) {
        TODO()
    }

    fun getAllContact(_deviceID: String){
        viewModelScope.launch {
            mAllContactsResult.value = mContactUseCase.getAllContact(_deviceID)
        }
    }

    fun getContact(_id: String){
        viewModelScope.async {
            mContactUseCase.getContact(_id)!!
        }
    }

    fun insertContact(_contact: Contact){
        viewModelScope.async {
            mContactUseCase.insertContact(_contact)
        }
    }

    fun patchContact(_id: String, contact: Contact) {
        TODO()
    }

    fun deleteContact(_id: String) {
        TODO()
    }
}