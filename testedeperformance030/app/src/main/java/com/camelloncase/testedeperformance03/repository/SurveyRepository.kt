package com.camelloncase.testedeperformance03.repository

import androidx.lifecycle.LiveData
import com.camelloncase.testedeperformance03.database.Survey
import com.camelloncase.testedeperformance03.database.SurveysDao

class SurveyRepository(private val surveysDao: SurveysDao) {

    val allSurveys: LiveData<List<Survey>> = surveysDao.getAllSurveys()

    suspend fun insert(survey: Survey) {
        surveysDao.insert(survey)
    }

    suspend fun update(survey: Survey){
        surveysDao.update(survey)
    }

    suspend fun delete(survey: Survey){
        surveysDao.delete(survey)
    }
}
