package com.camelloncase.testedeperformance03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.camelloncase.testedeperformance03.ui.surveys.SurveysActivity

class MainActivity : AppCompatActivity() {

    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton = findViewById(R.id.button)

        submitButton.setOnClickListener {

            goToMainActivity()

        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(applicationContext, SurveysActivity::class.java))
        this.finish()
    }
}