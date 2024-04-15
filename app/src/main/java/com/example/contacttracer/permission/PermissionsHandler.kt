package com.example.contacttracer.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.contacttracer.MainActivity
import com.example.contacttracer.R
import java.util.*
import java.util.jar.Manifest

object PermissionsHandler : AppCompatActivity() {


    // Array di permessi da soddisfare
    // TODO associare un reqCode a ogni permesso
    private val permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.BLUETOOTH_ADMIN,
        android.Manifest.permission.BLUETOOTH,
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.FOREGROUND_SERVICE
    )
    // TODO Background Location & Bluetooth_SCAN ?

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
        } else {

        }
    }

    /*
     *   Richiede i permessi.
     *
     */

    fun checkAndRequestPermissions(_context: Context, _activity: Activity) {
        // Per ogni permesso
        for (p in permissions) {
            when {
                ActivityCompat.checkSelfPermission(
                    _context,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
                -> {
                    Toast.makeText(_context, "Request permission", Toast.LENGTH_SHORT).show()
                    //TODO Quando il permesso è stato gia' garantito
                }
                ActivityCompat.shouldShowRequestPermissionRationale(_activity, p) -> {
                    // TODO Rational da visualizzare
                    requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                }
                else -> {
                    requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                }
            }
        }
    }

    // TODO Far ritornare solo la lista dei nomi senza i reqCodes?
    fun getPermissionsNeededList(): Array<String> {
        return permissions
    }
}