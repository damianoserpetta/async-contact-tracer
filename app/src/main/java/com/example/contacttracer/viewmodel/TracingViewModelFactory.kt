package com.example.contacttracer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class TracingViewModelFactory
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TracingViewModel::class.java)) {
            return TracingViewModel() as T
        }

        throw IllegalArgumentException("Unknow View Model Class")
    }
}