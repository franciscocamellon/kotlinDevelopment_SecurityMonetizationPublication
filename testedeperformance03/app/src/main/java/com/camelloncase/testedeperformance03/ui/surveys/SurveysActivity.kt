package com.camelloncase.testedeperformance03.ui.surveys

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.testedeperformance03.R
import com.camelloncase.testedeperformance03.adapter.SurveyAdapter
import com.camelloncase.testedeperformance03.adapter.SurveyClickDeleteInterface
import com.camelloncase.testedeperformance03.adapter.SurveyClickInterface
import com.camelloncase.testedeperformance03.database.Survey
import com.camelloncase.testedeperformance03.ui.management.SurveyManagementActivity
import com.camelloncase.testedeperformance03.util.showMessageToUser
import com.camelloncase.testedeperformance03.viewmodel.SurveyViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SurveysActivity : AppCompatActivity(), SurveyClickInterface, SurveyClickDeleteInterface {

    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: SurveyViewModel
    private lateinit var surveysRecyclerView: RecyclerView
    private lateinit var addFloatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surveys)

        surveysRecyclerView = findViewById(R.id.surveysRecyclerView)
        addFloatingActionButton = findViewById(R.id.addFloatingActionButton)

        surveysRecyclerView.layoutManager = LinearLayoutManager(this)

        val recipesRecyclerViewAdapter = SurveyAdapter(this, this, this)

        surveysRecyclerView.adapter = recipesRecyclerViewAdapter

        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[SurveyViewModel::class.java]

        viewModel.allSurveys.observe(this) { list ->
            list?.let {
                recipesRecyclerViewAdapter.updateList(it)
            }
        }
        addFloatingActionButton.setOnClickListener {
            goToRecipeManagementActivity()
        }
    }

    override fun onSurveyClick(survey: Survey) {

        val intent = Intent(this@SurveysActivity, SurveyManagementActivity::class.java)

        intent.putExtra("actionType", "Edit")
        intent.putExtra("business", survey.businessName)
        intent.putExtra("neighborhood", survey.neighborhoodName)
        intent.putExtra("first", survey.firstAnswer)
        intent.putExtra("second", survey.secondAnswer)
        intent.putExtra("third", survey.thirdAnswer)
        intent.putExtra("fourth", survey.fourthAnswer)
        intent.putExtra("fifth", survey.fifthAnswer)
        intent.putExtra("sixth", survey.sixthAnswer)
        intent.putExtra("createDate", survey.createDate)

        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(survey: Survey) {

        val message = "${survey.businessName} successful deleted!"

        viewModel.deleteSurvey(survey)
        showMessageToUser(this, message)
    }

    private fun goToRecipeManagementActivity() {
        val intent = Intent(this@SurveysActivity, SurveyManagementActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}