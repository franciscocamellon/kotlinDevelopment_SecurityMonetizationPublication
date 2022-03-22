package com.camelloncase.testedeperformance01.ui.recipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.camelloncase.testedeperformance01.database.Survey
import com.camelloncase.testedeperformance01.database.SurveyDatabase
import com.camelloncase.testedeperformance01.repository.SurveyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SurveyViewModel (application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<Survey>>
    private val repository : SurveyRepository

    init {
        val dao = SurveyDatabase.getDatabase(application).getNotesDao()
        repository = SurveyRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteRecipe (survey: Survey) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(survey)
    }

    fun updateRecipe(survey: Survey) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(survey)
    }

    fun addRecipe(survey: Survey) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(survey)
    }
}
