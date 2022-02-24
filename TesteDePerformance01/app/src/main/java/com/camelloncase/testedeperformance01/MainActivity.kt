package com.camelloncase.testedeperformance01

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.camelloncase.testedeperformance01.util.createFileName
import com.camelloncase.testedeperformance01.util.showMessageToUser
import com.camelloncase.testedeperformance01.databinding.ActivityMainBinding
import com.camelloncase.testedeperformance01.util.callPermissionDialog
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.*
import java.util.concurrent.Executor
import java.util.function.Consumer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var currentLatitude = String()
    var currentLongitude = String()

    companion object {
        private const val REQUEST_PERMISSIONS_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            callAccessLocation(it)
        }

        binding.locationListButton.setOnClickListener {
            goToNextActivity()
        }
    }

    private fun goToNextActivity() {
        val newIntent = Intent(applicationContext, LocationListActivity::class.java)
        startActivity(newIntent)
    }

    private fun callWriteOnSDCard() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                )) {
                callPermissionDialog(this, this@MainActivity,
                    getString(R.string.write_permission),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS_CODE
                )
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS_CODE)
            }
        } else {
            createLocationFile()
        }
    }

    private fun callAccessLocation(view: View) {

        val permissionAFL = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val permissionACL = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (permissionAFL != PackageManager.PERMISSION_GRANTED &&
            permissionACL != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                callPermissionDialog(this, this@MainActivity,getString(R.string.fine_location_permission),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_PERMISSIONS_CODE)
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSIONS_CODE)
            }
        } else {
            getCurrentCoordinates()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            var i = 0
            while (i < permissions.size) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    when (permissions[i]) {
                        Manifest.permission.ACCESS_FINE_LOCATION -> getCurrentCoordinates()
                        Manifest.permission.WRITE_EXTERNAL_STORAGE -> createLocationFile()
                    }
                }
                i++
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun getCurrentCoordinates() {

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGPSEnabled && !isNetworkEnabled) {
            showMessageToUser(this, getString(R.string.activate_gps))
        } else {
            if (isGPSEnabled) {
                try {
                    val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if (lastLocation != null) {
                        currentLatitude = lastLocation.latitude.toString()
                        currentLongitude = lastLocation.longitude.toString()
                    }
                    callWriteOnSDCard()
                } catch(ex: SecurityException) {
                    Log.d(getString(R.string.log_tag), getString(R.string.security_exception))
                }
            }
            else if (isNetworkEnabled) {
                try {
                    val lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    if (lastLocation != null) {
                        currentLatitude = lastLocation.latitude.toString()
                        currentLongitude = lastLocation.longitude.toString()
                    }
                    callWriteOnSDCard()
                } catch(ex: SecurityException) {
                    Log.d(getString(R.string.log_tag), getString(R.string.security_exception))
                }
            }
        }
    }

    private fun createLocationFile() {

        val fileName = createFileName()
        val file = File(getExternalFilesDir(null), fileName)

        if(file.exists()){
            file.delete()
        }
        else{
            try {
                val os: OutputStream = FileOutputStream(file)
                os.write("$currentLatitude, $currentLongitude".toByteArray())
                os.close()
                showMessageToUser(this, getString(R.string.file_saved))
            } catch (e: IOException) {
                Log.d(getString(R.string.log_tag), getString(R.string.file_error))
            }
        }
    }
}