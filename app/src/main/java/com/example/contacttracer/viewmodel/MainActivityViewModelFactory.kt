package com.example.contacttracer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MainActivityViewModelFactory(
    private val tracingState: Boolean,
    private val macAddress: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(
                tracingState,
                macAddress
            ) as T
        }

        throw IllegalArgumentException("Unknow View Model Class")
    }
}