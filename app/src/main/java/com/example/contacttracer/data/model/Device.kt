package com.example.contacttracer.data.model

import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("deviceID")
    val deviceID: String,
)