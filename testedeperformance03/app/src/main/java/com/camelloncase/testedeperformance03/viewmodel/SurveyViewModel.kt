package com.camelloncase.testedeperformance03.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.camelloncase.testedeperformance03.database.Survey
import com.camelloncase.testedeperformance03.database.SurveysDatabase
import com.camelloncase.testedeperformance03.repository.SurveyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SurveyViewModel (
    application: Application
) : AndroidViewModel(application) {

    val allSurveys : LiveData<List<Survey>>
    private val repository : SurveyRepository

    init {
        val dao = SurveysDatabase.getDatabase(application).getSurveysDao()
        repository = SurveyRepository(dao)
        allSurveys = repository.allSurveys
    }

    fun deleteSurvey (survey: Survey) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(survey)
    }

    fun addSurvey(survey: Survey) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(survey)
    }

    fun getSurveyById(surveyId: Int) = viewModelScope.launch(Dispatchers.IO)  {
        repository.getSurveyById(surveyId)
    }

    fun getAllSurveysBySurveyorId(surveyorId: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.getAllSurveysBySurveyorId(surveyorId)
    }

}
