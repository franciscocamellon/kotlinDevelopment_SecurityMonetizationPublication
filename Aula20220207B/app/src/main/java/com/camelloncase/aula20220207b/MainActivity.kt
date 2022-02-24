package com.camelloncase.aula20220207b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnSecond: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSecond = findViewById(R.id.btnSecond)
        btnSecond.setOnClickListener {
            val i1 = Intent()
            i1.action = "com.camelloncase.testedeperformance01.ActionLocation"
            i1.addCategory("android.intent.category.DEFAULT")
            startActivity(i1)
        }
    }
}