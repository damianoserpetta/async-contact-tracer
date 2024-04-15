package com.example.contacttracer.functions

import com.example.contacttracer.config.BluetoothConfigs
import kotlin.math.*

class BluetoothUtils {

    companion object Utils {
        private val MEASURED_POWER: Int = BluetoothConfigs.getMeasuredPower()
        private val costantN: Int = BluetoothConfigs.getNCostant()

        /*
         *
         */
        fun calculateDistanceFromRSSI(_rssi: Short): Double {
            var distanceInMeters: Double = 0.0
            var exp: Double = 10.0

            distanceInMeters =
                exp.pow(((MEASURED_POWER - (_rssi)) / (10.0 * costantN)))
            return distanceInMeters
        }
    }
}