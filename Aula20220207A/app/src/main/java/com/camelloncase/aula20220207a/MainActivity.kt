package com.camelloncase.aula20220207a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun callLoadImage(view: View) {}
    fun callWriteOnSDCard(view: View) {}
    fun callReadFromSDCard(view: View) {}
    fun callAccessLocation(view: View) {}
}