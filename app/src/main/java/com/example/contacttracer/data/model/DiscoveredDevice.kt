package com.example.contacttracer.data.model

import com.google.gson.annotations.SerializedName

data class DiscoveredDevice(
    @SerializedName("macAddress")
    val macAddress: String,
    @SerializedName("rssi")
    val RSSI: Short
)