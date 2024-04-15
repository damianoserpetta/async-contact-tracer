package com.example.contacttracer

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.contacttracer.data.model.DiscoveredDevice
import com.example.contacttracer.viewmodel.TracingViewModel

class TracingBluetoothBR(_viewModel: TracingViewModel) : BroadcastReceiver() {

    private var viewModel = _viewModel

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            BluetoothDevice.ACTION_FOUND -> {
                val btDevice: BluetoothDevice =
                    intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)!!
                val deviceHardwareAddress = btDevice.address // MAC address
                val deviceRSSI: Short =
                    intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE)

                val device = DiscoveredDevice(deviceHardwareAddress, deviceRSSI)
                viewModel.addDeviceInfo(device)
            }
        }
    }
}