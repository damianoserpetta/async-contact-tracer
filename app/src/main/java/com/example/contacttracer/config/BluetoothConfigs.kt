package com.example.contacttracer.config

class BluetoothConfigs {
    companion object {

        // 1 Meter RSSI property of BLE
        private var MEASURED_POWER: Int = -69

        // Constant depends on the Environmental factor. Range 2–4, low to-high strength
        private var costantN: Int = 2

        private var macAddress: String = "AA:AA"

        fun getMeasuredPower(): Int {
            return MEASURED_POWER
        }

        fun setMeasuredPower(_measuredPower: Int) {
            MEASURED_POWER = _measuredPower
        }

        fun getNCostant(): Int {
            return costantN
        }

        fun setNCostant(_costantN: Int) {
            costantN = _costantN
        }

        fun getMacAddress(): String {
            return macAddress
        }

        fun setMacAddress(_macAddress: String) {
            macAddress = _macAddress
        }
    }
}