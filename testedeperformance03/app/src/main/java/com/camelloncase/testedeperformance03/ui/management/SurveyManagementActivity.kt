package com.camelloncase.testedeperformance03.ui.management

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.camelloncase.testedeperformance03.R
import com.camelloncase.testedeperformance03.database.Survey
import com.camelloncase.testedeperformance03.ui.surveys.SurveysActivity
import com.camelloncase.testedeperformance03.util.formattedCurrentDate
import com.camelloncase.testedeperformance03.util.showMessageToUser
import com.camelloncase.testedeperformance03.viewmodel.SurveyViewModel

class SurveyManagementActivity : AppCompatActivity() {

    private lateinit var businessNameEdt: EditText
    private lateinit var neighborhoodNameEdt: EditText
    private lateinit var createDateEdt: EditText
    private lateinit var firstEdt: EditText
    private lateinit var secondEdt: EditText
    private lateinit var thirdEdt: EditText
    private lateinit var fourthEdt: EditText
    private lateinit var fifthEdt: EditText
    private lateinit var sixthEdt: EditText
    private lateinit var submitButton: Button
    private lateinit var cancelButton: Button
    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: SurveyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_management)

        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[SurveyViewModel::class.java]

        initWidgetComponents()

        val actionType = intent.getStringExtra("actionType")

        when(actionType) {
            "Edit" -> {
                setWidgetComponents()
                disableWidgetComponents()
                submitButton.visibility = View.GONE

            }
            else -> {
                val createDate = formattedCurrentDate("dd-MMM-yy hh:mm")
                createDateEdt.setText(createDate)
                createDateEdt.isEnabled = false

                submitButton.setOnClickListener {

                    val business = businessNameEdt.text.toString()
                    val neighborhood = neighborhoodNameEdt.text.toString()
                    val first = firstEdt.text.toString()
                    val second = secondEdt.text.toString()
                    val third = thirdEdt.text.toString()
                    val fourth = fourthEdt.text.toString()
                    val fifth = fifthEdt.text.toString()
                    val sixth = sixthEdt.text.toString()

                    val survey = Survey("1", business, neighborhood, first, second, third, fourth, fifth, sixth, createDate)

                    viewModel.addSurvey(survey)

                    showMessageToUser(this, "$business successful added!")

                    goToMainActivity()
                }
            }
        }

        cancelButton.setOnClickListener {

            goToMainActivity()

        }
    }

    private fun initWidgetComponents() {
        businessNameEdt = findViewById(R.id.businessEditText)
        neighborhoodNameEdt = findViewById(R.id.neighborhoodEditText)
        createDateEdt = findViewById(R.id.createDateEditText)
        firstEdt = findViewById(R.id.firstEditText)
        secondEdt = findViewById(R.id.secondEditText)
        thirdEdt = findViewById(R.id.thirdEditText)
        fourthEdt = findViewById(R.id.fourthEditText)
        fifthEdt = findViewById(R.id.fifthEditText)
        sixthEdt = findViewById(R.id.sixthEditText)
        submitButton = findViewById(R.id.submitButton)
        cancelButton = findViewById(R.id.cancelButton)
    }

    private fun setWidgetComponents() {
        val business = intent.getStringExtra("business")
        val neighborhood = intent.getStringExtra("neighborhood")
        val first = intent.getStringExtra("first")
        val second = intent.getStringExtra("second")
        val third = intent.getStringExtra("third")
        val fourth = intent.getStringExtra("fourth")
        val fifth = intent.getStringExtra("fifth")
        val sixth = intent.getStringExtra("sixth")
        val date = intent.getStringExtra("createDate")

        businessNameEdt.setText(business)
        neighborhoodNameEdt.setText(neighborhood)
        createDateEdt.setText(date)
        firstEdt.setText(first)
        secondEdt.setText(second)
        thirdEdt.setText(third)
        fourthEdt.setText(fourth)
        fifthEdt.setText(fifth)
        sixthEdt.setText(sixth)
    }

    private fun disableWidgetComponents() {
        businessNameEdt.isEnabled = false
        neighborhoodNameEdt.isEnabled = false
        createDateEdt.isEnabled = false
        firstEdt.isEnabled = false
        secondEdt.isEnabled = false
        thirdEdt.isEnabled = false
        fourthEdt.isEnabled = false
        fifthEdt.isEnabled = false
        sixthEdt.isEnabled = false
    }

    private fun goToMainActivity() {
        startActivity(Intent(applicationContext, SurveysActivity::class.java))
        this.finish()
    }
}
