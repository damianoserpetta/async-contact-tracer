package com.example.contacttracer

import com.example.contacttracer.functions.BluetoothUtils
import org.junit.Test

import org.junit.Assert.*

internal class BluetoothUtilsTest {

    @Test
    fun calculateDistanceTest() {

        assertEquals(1.0, BluetoothUtils.calculateDistanceFromRSSI(-69), 0.02)
        assertEquals(1.995, BluetoothUtils.calculateDistanceFromRSSI(-75), 0.02)
        assertEquals(3.54, BluetoothUtils.calculateDistanceFromRSSI(-80), 0.02)
    }

}