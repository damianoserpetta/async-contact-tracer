package com.example.contacttracer.config

class EnvConfig {
    companion object {
        // Lista dei permessi
        private val permissions = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.BLUETOOTH_ADMIN,
            android.Manifest.permission.BLUETOOTH,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.FOREGROUND_SERVICE
        )

        fun getPermissionsList(): Array<String> {
            return permissions
        }
    }
}