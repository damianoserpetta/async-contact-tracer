package com.example.contacttracer

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.contacttracer.config.BluetoothConfigs
import com.example.contacttracer.config.DBConfig
import com.example.contacttracer.config.EnvConfig
import com.example.contacttracer.databinding.ActivityMainBinding
import com.example.contacttracer.domain.HashTool
import com.example.contacttracer.viewmodel.*
import com.example.contacttracer.domain.TracerDB
import kotlin.NullPointerException

// Initial state of tracing
const val TRACING_INITIAL_STATE: Boolean = false

class MainActivity : AppCompatActivity() {

    /* ----------   Start Variables declarations  ---------- */
    //  Data Binding
    private lateinit var binding: ActivityMainBinding

    //  View Models
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var tracingViewModel: TracingViewModel
    private lateinit var contactsViewModel: ContactsViewModel

    //  Activity result launchers
    private lateinit var enableBt: ActivityResultLauncher<Intent>
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    // Bluetooth Manager
    private lateinit var mBtManager: TracingBluetoothManager

    // Bluetooth Broadcast Receiver
    private lateinit var mBtBroadcastReceiver: TracingBluetoothBR

    // Contact Tracer
    private lateinit var mContactTracer: ContactTracer

    // API DB
    private lateinit var mTracerDB: TracerDB

    /* ----------   End Variables declarations  ---------- */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* ----------   Start Variables Init  ---------- */

        // Bluetooth Manager
        mBtManager = TracingBluetoothManager(this as Context)

        // Contact Tracer
        mContactTracer = ContactTracer(this as Context, false)
        // API
        mTracerDB = TracerDB()

        // Inizializzazione Factories dei viewModel
        val viewModelFactory = MainActivityViewModelFactory(
            TRACING_INITIAL_STATE,
            BluetoothConfigs.getMacAddress()
        )
        val tracingViewModelFactory = TracingViewModelFactory()
        val contactsViewModelFactory = ContactsViewModelFactory()

        // Inizializzazione ViewModels
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)
        viewModel.setBluetoothSupported(mBtManager.isBluetoothSupported())
        viewModel.setLocationSupported(this.packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION))

        tracingViewModel =
            ViewModelProvider(this, tracingViewModelFactory).get(TracingViewModel::class.java)
        contactsViewModel =
            ViewModelProvider(this, contactsViewModelFactory).get(ContactsViewModel::class.java)

        // Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //  Bluetooth Broadcast Receiver
        mBtBroadcastReceiver = TracingBluetoothBR(tracingViewModel)

        /* ----------   End Variables Init  ---------- */

        mTracerDB.getAllContact(HashTool.toHash(BluetoothConfigs.getMacAddress()))


        // Register for broadcasts when a device is discovered.
        registerReceiver(mBtBroadcastReceiver, IntentFilter(BluetoothDevice.ACTION_FOUND))

        viewModel.tracingStateData.observe(this, {
            binding.buttonStart.isSelected = viewModel.tracingStateData.value!!

            if(it == true) {
                binding.textButtonStart.setText(R.string.TRACING_STOP)
                binding.tracingProgressBar.visibility = View.VISIBLE
            }
            else {
                binding.textButtonStart.setText(R.string.TRACING_START)
                binding.tracingProgressBar.visibility = View.INVISIBLE
            }
        })

        tracingViewModel.discoveredDevices.observe(this, {
            try{
                val contact =
                    mContactTracer.createContactOnDiscoveredDevice(viewModel.getMacAddres(), it.removeLast())
                contactsViewModel.addContact(contact)
            } catch (err : NullPointerException){
                Log.i("Discover Error", "Null pointer exception")
            }
        })

        contactsViewModel.contacts.observe(this, {
            val lastContact = it.removeLast()
            mTracerDB.insertContact(lastContact)
            mTracerDB.getAllContact(HashTool.toHash(BluetoothConfigs.getMacAddress()))
        })

        mTracerDB.contacts.observe(this, {
            if(it.isNotEmpty()) {
                binding.textAllDeviceFound.text = (it.size + 1).toString()
                binding.textDistanceDevice.text = String.format("%.2f", it.last().distance)
            }
        })
        
        //  Activity Result per abilitare il bluetooth.
        enableBt = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            when (it.resultCode) {
                0 -> {
                    viewModel.setTracingState(false)
                    Toast.makeText(
                        this@MainActivity,
                        R.string.BLUETOOTH_NOT_ACTIVATED,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
                -1 -> {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.BLUETOOTH_ACTIVATED,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }

        }

        //  Activity Result per accedere ai permessi
        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this@MainActivity, "Granted!", Toast.LENGTH_SHORT).show()
                if (checkPermissions()) {
                    enableBluetooth()
                    viewModel.setTracingState(true)
                    mBtManager.startScan()

                }
            } else {
                viewModel.setTracingState(false)
                Toast.makeText(this@MainActivity, "Not Granted!", Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun onStartTracingSwitchClicked(view: View?) {
        viewModel.setTracingState(!viewModel.tracingStateData.value!!)
        //  Se lo switch e' impostato su ON.
        if (viewModel.tracingStateData.value == true) {

            if (!checkPermissions())
                requestPermissions()
            else {
                enableBluetooth()
                mBtManager.startScan()
            }
        }
    }

    fun onSummaryButtonClicked(view:View?){
        val uri: Uri = Uri.parse(DBConfig.getBaseUrl()+"/mycontacts")

        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    // TODO Separare richiesta permessi dall'attivazione bluetooth
    private fun requestPermissions() {
        // Per ogni permesso
        for (p in EnvConfig.getPermissionsList()) {
            when {
                ActivityCompat.checkSelfPermission(
                    this as Context,
                    p
                ) == PackageManager.PERMISSION_GRANTED
                -> {
                }
                ActivityCompat.shouldShowRequestPermissionRationale(this as Activity, p) -> {
                    // TODO Rational da visualizzare
                    requestPermissionLauncher.launch(p)
                }
                else -> {
                    requestPermissionLauncher.launch(p)
                }
            }
        }
    }

    private fun checkPermissions(): Boolean {
        for (p in EnvConfig.getPermissionsList()) {
            if (ActivityCompat.checkSelfPermission(
                    this as Context,
                    p
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }

        }
        return true
    }

    private fun enableBluetooth() {
        /*
         *  Se il bluetooth è supportato, avviamo il bluetooth.
         */
        if (viewModel.isBluetoothSupported.value == true) {

            /*
         *  Se il bluetooth non è abilitato, chiediamo di abilitarlo.
         */
            if (mBtManager.isBluetoothEnabled() == false) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                enableBt.launch(enableBtIntent)
            }
        } else {
            viewModel.setTracingState(false)
            Toast.makeText(
                this@MainActivity,
                R.string.BLUETOOTH_NOT_SUPPORTED,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(mBtBroadcastReceiver)
    }

}