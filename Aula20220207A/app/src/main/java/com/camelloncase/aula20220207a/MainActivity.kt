package com.camelloncase.aula20220207a

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import java.io.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun callLoadImage(view: View) {
        val iv: ImageView = findViewById(R.id.iv_logo)
        Picasso.get().load("https://www.infnet.edu.br/infnet/wp-content/uploads/sites/6/2018/01/logotipo.png").into(iv)
    }

    fun callWriteOnSDCard(view: View) {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                )) {
                callDialog(
                    "É preciso liberar WRITE_EXTERNAL_STORAGE",
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                )
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS_CODE)
            }
        } else {
            createDeleteFile()
        }
    }

    fun callReadFromSDCard(view: View) {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.READ_EXTERNAL_STORAGE
                )) {
                callDialog(
                    "É preciso a liberar READ_EXTERNAL_STORAGE",
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                )
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS_CODE)
            }
        } else {
            readFile()
        }
    }

    fun callAccessLocation(view: View) {
        val permissionAFL = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val permissionACL = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        if (permissionAFL != PackageManager.PERMISSION_GRANTED &&
            permissionACL != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                callDialog("É preciso liberar ACCESS_FINE_LOCATION",
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSIONS_CODE)
            }
        } else {
            readMyCurrentCoordinates()
        }
    }

    // permissions
    private val REQUEST_PERMISSIONS_CODE = 12800

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
                        Manifest.permission.ACCESS_FINE_LOCATION -> readMyCurrentCoordinates()
                        Manifest.permission.WRITE_EXTERNAL_STORAGE -> createDeleteFile()
                        Manifest.permission.READ_EXTERNAL_STORAGE -> readFile()
                    }
                }
                i++
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun callDialog(mensagem: String,
                           permissions: Array<String>) {
        var mDialog = AlertDialog.Builder(this)
            .setTitle("Permissão")
            .setMessage(mensagem)
            .setPositiveButton("Ok")
            { dialog, id ->
                ActivityCompat.requestPermissions(
                    this@MainActivity, permissions,
                    REQUEST_PERMISSIONS_CODE)
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar")
            { dialog, id ->
                dialog.dismiss()
            }
        mDialog.show()
    }
    // permissions

    // tratamento da localização
    private val locationListener: LocationListener =
        object : LocationListener {
            override fun onLocationChanged(location: Location) {
                Toast.makeText(applicationContext,
                    "Lat: $location.latitude | Long: $location.longitude",
                    Toast.LENGTH_SHORT).show()
            }
            override fun onStatusChanged(
                provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

    private fun readMyCurrentCoordinates() {

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGPSEnabled && !isNetworkEnabled) {
            Toast.makeText(this, "Ative o GPS!", Toast.LENGTH_SHORT).show()
            Log.d("Permissao", "Ative os serviços necessários")
        } else {
            if (isGPSEnabled) {
                try {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        2000L, 0f, locationListener)
                } catch(ex: SecurityException) {
                    Log.d("Permissao", "Security Exception")
                }
            }
            else if (isNetworkEnabled) {
                try {
                    locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        2000L, 0f, locationListener)
                } catch(ex: SecurityException) {
                    Log.d("Permissao", "Security Exception")
                }
            }
        }
    }

    // tratamento da localização

    // armazenamento externo

    private fun createDeleteFile() {
        val file = File(getExternalFilesDir(null), "DemoFile.txt")
        if(file.exists()){
            file.delete()
        }
        else{
            try {
                val os: OutputStream = FileOutputStream(file)
                os.write("Pequeno Teste".toByteArray())
                os.close()
            } catch (e: IOException) {
                Log.d("Permissao", "Erro de escrita em arquivo")
            }
        }
    }

    private fun readFile() {
        val file = File(getExternalFilesDir(null), "DemoFile.txt")
        if(!file.exists()) {
            Toast.makeText(this@MainActivity,
                "Arquivo não encontrado",
                Toast.LENGTH_SHORT).show()
            return
        }
        val text = StringBuilder()
        try {
            val br = BufferedReader(FileReader(file))
            var line: String?
            while (br.readLine().also { line = it } != null) {
                text.append(line)
                text.append('\n')
            }
            br.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Toast.makeText(this@MainActivity,
            text.toString(),
            Toast.LENGTH_SHORT).show()
    }

    // armazenamento externo

}