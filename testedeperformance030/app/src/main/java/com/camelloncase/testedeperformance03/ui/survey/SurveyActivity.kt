package com.camelloncase.testedeperformance03.ui.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import com.camelloncase.testedeperformance03.R
import com.camelloncase.testedeperformance03.viewmodel.SurveyViewModel

class SurveyActivity : AppCompatActivity() {

    private lateinit var submitButton: Button
    private lateinit var cancelButton: Button
    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: SurveyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        initWidgets()

        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[SurveyViewModel::class.java]

        val businessName = intent.getStringExtra("businessName")
        val neighborhoodName = intent.getStringExtra("neighborhoodName")
        val currentDate = intent.getStringExtra("currentDate")

        when (businessName) {
            null -> //toast
            else ->
        }


    }

    private fun initWidgets() {
        submitButton = findViewById(R.id.submitButton)
        cancelButton = findViewById(R.id.cancelButton)
        val firstQuestionNo: RadioButton = findViewById(R.id.firstQuestionRadioButtonNo)
        val firstQuestionYes: RadioButton = findViewById(R.id.firstQuestionRadioButtonYes)
        val secondQuestionNo: RadioButton = findViewById(R.id.secondQuestionRadioButtonNo)
        val secondQuestionYes: RadioButton = findViewById(R.id.secondQuestionRadioButtonYes)
        val thirdQuestionNo: RadioButton = findViewById(R.id.thirdQuestionRadioButtonNo)
        val thirdQuestionYes: RadioButton = findViewById(R.id.thirdQuestionRadioButtonYes)
        val fourthQuestionNo: RadioButton = findViewById(R.id.fourthQuestionRadioButtonNo)
        val fourthQuestionYes: RadioButton = findViewById(R.id.fourthQuestionRadioButtonYes)
        val fifthQuestionNo: RadioButton = findViewById(R.id.fifthQuestionRadioButtonNo)
        val fifthQuestionYes: RadioButton = findViewById(R.id.fifthQuestionRadioButtonYes)
        val sixthQuestionNo: RadioButton = findViewById(R.id.sixthQuestionRadioButtonNo)
        val sixthQuestionYes: RadioButton = findViewById(R.id.sixthQuestionRadioButtonYes)
    }
}