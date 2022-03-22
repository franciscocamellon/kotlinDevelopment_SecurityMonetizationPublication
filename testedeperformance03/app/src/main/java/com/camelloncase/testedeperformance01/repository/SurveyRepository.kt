package com.camelloncase.testedeperformance01.repository

import androidx.lifecycle.LiveData
import com.camelloncase.testedeperformance01.database.Survey
import com.camelloncase.testedeperformance01.database.SurveyDao

class SurveyRepository(private val surveyDao: SurveyDao) {

    val allNotes: LiveData<List<Survey>> = surveyDao.getAllNotes()

    suspend fun insert(note: Survey) {
        surveyDao.insert(note)
    }

    suspend fun update(note: Survey){
        surveyDao.update(note)
    }

    suspend fun delete(note: Survey){
        surveyDao.delete(note)
    }
}
