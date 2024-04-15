package com.example.contacttracer

import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import com.example.contacttracer.functions.BluetoothUtils
import com.example.contacttracer.data.model.Contact
import com.example.contacttracer.data.model.DiscoveredDevice
import com.example.contacttracer.domain.HashTool

import java.util.*

class ContactTracer(_context: Context, _enabledLocation: Boolean) {

    private var enabledLocation: Boolean = _enabledLocation
    private val locationManager: LocationManager =
        _context.getSystemService(Context.LOCATION_SERVICE) as LocationManager;

    fun createContactOnDiscoveredDevice(
        _myMacAddress: String,
        _discovered_device: DiscoveredDevice
    ): Contact {

        val myMacAddress: String = HashTool.toHash(_myMacAddress)
        val deviceMacAddress: String = HashTool.toHash(_discovered_device.macAddress)
        val distance: Double = BluetoothUtils.calculateDistanceFromRSSI(_discovered_device.RSSI)
        var position: String = ""
        val date = Date()
        // TODO("Aggiunta della geo localizzazione")
        if (enabledLocation) position = "" else position = "Non rilevata"

        return Contact(myMacAddress, deviceMacAddress, distance, position, date)
    }

    fun enableLocation() {
        enabledLocation = true
    }

    fun disableLocation() {
        enabledLocation = false
    }
}