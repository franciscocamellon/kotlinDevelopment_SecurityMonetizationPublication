package com.camelloncase.testedeperformance01

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.testedeperformance01.databinding.ActivityLocationListBinding
import com.camelloncase.testedeperformance01.util.callPermissionDialog

class LocationListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationListBinding
    private val itemsList = ArrayList<String>()
    private lateinit var customAdapter: LocationListAdapter

    companion object {
        private const val REQUEST_PERMISSIONS_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLocationListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.recyclerView

        customAdapter = LocationListAdapter(itemsList)

        val layoutManager = LinearLayoutManager(applicationContext)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter

        callListAllFiles()
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
                        Manifest.permission.READ_EXTERNAL_STORAGE -> listAllFiles()
                    }
                }
                i++
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun callListAllFiles() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.READ_EXTERNAL_STORAGE
                )) {
                callPermissionDialog(this, this@LocationListActivity,
                    getString(R.string.read_permission),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS_CODE
                )
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS_CODE
                )
            }
        } else {
            listAllFiles()
        }
    }

    private fun listAllFiles() {

        val filesPath = getExternalFilesDir(null)?.listFiles()

        if (filesPath != null) {
            filesPath.forEach {
                itemsList.add(it.name.toString())
            }
        } else {
            itemsList.add(getString(R.string.empty_folder))
        }
    }
}