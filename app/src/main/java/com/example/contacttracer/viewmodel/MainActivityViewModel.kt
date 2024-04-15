package com.example.contacttracer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(
    _tracingState: Boolean,
    _macAddress: String
) : ViewModel() {

    // Stato del tracciamento : ON / OFF
    private var tracingState = MutableLiveData<Boolean>(false)
    val tracingStateData: LiveData<Boolean>
        get() = tracingState

    private var mIsBluetoothSupported = MutableLiveData<Boolean>(false)
    val isBluetoothSupported: LiveData<Boolean>
        get() = mIsBluetoothSupported

    private var mIsLocationSupported = MutableLiveData<Boolean>(false)
    val isLocationSupported: LiveData<Boolean>
        get() = mIsLocationSupported

    private var mMacAddress: String = ""

    init {
        tracingState.value = _tracingState
        mIsBluetoothSupported.value = false
        mMacAddress = _macAddress
    }

    fun setTracingState(_tracingState: Boolean) {
        tracingState.value = _tracingState
    }

    fun setBluetoothSupported(_isBluetoothSupported: Boolean) {
        mIsBluetoothSupported.value = _isBluetoothSupported
    }

    fun setLocationSupported(_isLocationSupported: Boolean) {
        mIsLocationSupported.value = _isLocationSupported
    }

    fun getMacAddres(): String {
        return mMacAddress
    }

}