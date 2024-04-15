package com.example.contacttracer.data.model

import java.util.*
import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("deviceDetector")
    val deviceDetector: String,
    @SerializedName("deviceDetected")
    val deviceDetected: String,
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("position")
    val position: String,
    @SerializedName("contactDate")
    val contactDate: Date
)