package com.camelloncase.testedeperformance03.ui.management

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.camelloncase.testedeperformance03.R
import com.camelloncase.testedeperformance03.MainActivity
import com.camelloncase.testedeperformance03.ui.survey.SurveyActivity
import com.camelloncase.testedeperformance03.util.*
import com.camelloncase.testedeperformance03.viewmodel.SurveyViewModel

class SurveyManagementActivity : AppCompatActivity() {

    private lateinit var businessNameEdt: EditText
    private lateinit var neighborhoodNameEdt: EditText
    private lateinit var submitButton: Button
    private lateinit var cancelButton: Button
    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: SurveyViewModel
    private var surveyId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_management)

        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[SurveyViewModel::class.java]

        businessNameEdt = findViewById(R.id.businessNameEditText)
        neighborhoodNameEdt = findViewById(R.id.neighborhoodNameEditText)
        submitButton = findViewById(R.id.submitButton)
        cancelButton = findViewById(R.id.cancelButton)


        val actionType = intent.getStringExtra("actionType")
        when(actionType) {
            "Edit" -> fillUpdateSurveyForm()
            else -> submitButton.text = getString(R.string.btn_title_save)
        }

        submitButton.setOnClickListener {

            val businessName = businessNameEdt.text.toString()
            val neighborhoodName = neighborhoodNameEdt.text.toString()

            when (actionType) {
                "Edit" -> fillFormToEditSurveyItem(businessName, neighborhoodName)
                else -> getCurrentDateAndStartSurvey(businessName, neighborhoodName)
            }

        }

        cancelButton.setOnClickListener {

            goToMainActivity()

        }
    }

    private fun fillUpdateSurveyForm() {

        val businessName = intent.getStringExtra("businessName")
        val neighborhoodName = intent.getStringExtra("neighborhoodName")

        surveyId = intent.getIntExtra("surveyId", -1)
        businessNameEdt.setText(businessName)
        neighborhoodNameEdt.setText(neighborhoodName)
        submitButton.text = getString(R.string.btn_title_update)
    }

    private fun fillFormToEditSurveyItem(surveyName: String, neighborhoodName: String) {

        val currentDate = formattedCurrentDate("dd MMM yy - hh:mm")

        if (surveyName.isNotEmpty() && neighborhoodName.isNotEmpty()) {

//            val updatedSurvey = Survey(surveyName, neighborhoodName, currentDate)

//            updatedSurvey.surveyId = surveyId
//            viewModel.updateSurvey(updatedSurvey)

            showMessageToUser(this, "Survey updated..")
            goToMainActivity()

        } else {

            showMessageToUser(this, "Error when try to edit survey")
            goToMainActivity()
        }
    }

    private fun getCurrentDateAndStartSurvey(businessName: String, neighborhoodName: String) {

        if (businessName.isNotEmpty() && neighborhoodName.isNotEmpty()) {

            val currentDate = formattedCurrentDate("dd MMM yy - hh:mm")

            startSurveyActivity(businessName, neighborhoodName, currentDate)

        } else {
            showMessageToUser(this, "Please enter the information required!")
        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        this.finish()
    }

    private fun startSurveyActivity(businessName: String, neighborhoodName: String, currentDate: String) {

        val intent = Intent(this@SurveyManagementActivity, SurveyActivity::class.java)
        intent.putExtra("businessName", businessName)
        intent.putExtra("neighborhoodName", neighborhoodName)
        intent.putExtra("currentDate", currentDate)
        startActivity(intent)
        this.finish()
    }
}
