package com.example.contacttracer

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context


class TracingBluetoothManager(_context: Context) {

    // Bluetooth
    private var mBluetoothManager: BluetoothManager? = null
    private var mBluetoothAdapter: BluetoothAdapter? = null

    init {
        //  Inizializza oggetto per la gestione del bluetooth.
        mBluetoothManager = _context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        mBluetoothAdapter = mBluetoothManager?.adapter
    }

    fun isBluetoothSupported(): Boolean {
        return (mBluetoothAdapter != null)
    }

    fun isBluetoothEnabled(): Boolean? {
        return mBluetoothAdapter?.isEnabled
    }

    fun startScan(): Boolean? {
        return mBluetoothAdapter?.startDiscovery()
    }
}