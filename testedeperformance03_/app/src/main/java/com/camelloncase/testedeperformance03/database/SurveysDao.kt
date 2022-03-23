package com.camelloncase.testedeperformance03.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SurveysDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(survey: Survey)

    @Update
    suspend fun update(survey: Survey)

    @Delete
    suspend fun delete(survey: Survey)

    @Query("Select * from health_surveillance_survey_table order by create_date ASC")
    fun getAllSurveys(): LiveData<List<Survey>>

    @Query("Select * from health_surveillance_survey_table where surveyor_id = :id order by create_date ASC")
    fun getAllSurveysBySurveyor(id: String): LiveData<List<Survey>>
}