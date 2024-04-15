package com.example.contacttracer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacttracer.data.model.DiscoveredDevice

class TracingViewModel : ViewModel() {

    private var _discoveredDevices = MutableLiveData<MutableList<DiscoveredDevice>>()
    val discoveredDevices: LiveData<MutableList<DiscoveredDevice>>
        get() = _discoveredDevices

    // Lista temporanea così da poter aggiornare il value di discoveredDevices, che triggera l'observer.
    private val tempDiscoveredDevices = mutableListOf<DiscoveredDevice>()

    fun addDeviceInfo(_discovered_device: DiscoveredDevice) {
        val device = _discovered_device
        tempDiscoveredDevices.add(device)
        _discoveredDevices.value = tempDiscoveredDevices
    }


}