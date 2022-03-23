package com.camelloncase.testedeperformance03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.testedeperformance03.database.Survey
import com.camelloncase.testedeperformance03.ui.management.SurveyManagementActivity
import com.camelloncase.testedeperformance03.adapter.SurveyClickDeleteInterface
import com.camelloncase.testedeperformance03.adapter.SurveyClickInterface
import com.camelloncase.testedeperformance03.viewmodel.SurveyViewModel
import com.camelloncase.testedeperformance03.adapter.RecipesRecyclerViewAdapter
import com.camelloncase.testedeperformance03.util.showMessageToUser
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), SurveyClickInterface, SurveyClickDeleteInterface {

    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: SurveyViewModel
    private lateinit var recipesRecyclerView: RecyclerView
    private lateinit var addFloatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recipesRecyclerView = findViewById(R.id.surveysRecyclerView)
        addFloatingActionButton = findViewById(R.id.addFloatingActionButton)

        recipesRecyclerView.layoutManager = LinearLayoutManager(this)

        val recipesRecyclerViewAdapter = RecipesRecyclerViewAdapter(this, this, this)

        recipesRecyclerView.adapter = recipesRecyclerViewAdapter

        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[SurveyViewModel::class.java]

        viewModel.allSurveys.observe(this) { list ->
            list?.let {
                recipesRecyclerViewAdapter.updateList(it)
            }
        }
        addFloatingActionButton.setOnClickListener {
            goToSurveyManagementActivity()
        }
    }

    override fun onSurveyClick(survey: Survey) {

        val intent = Intent(this@MainActivity, SurveyManagementActivity::class.java)

        intent.putExtra("actionType", "Edit")
        intent.putExtra("businessName", survey.businessName)
        intent.putExtra("neighborhoodName", survey.neighborhoodName)
        intent.putExtra("surveyId", survey.surveyId)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(survey: Survey) {

        val message = "${survey.businessName} successful deleted!"

        viewModel.deleteSurvey(survey)
        showMessageToUser(this, message)
    }

    private fun goToSurveyManagementActivity() {
        val intent = Intent(this@MainActivity, SurveyManagementActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}
