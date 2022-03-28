package com.camelloncase.testedeperformance03.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.camelloncase.testedeperformance03.database.Survey

@Dao
interface SurveysDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(survey: Survey)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSurveyor(surveyor: Surveyor)

    @Update
    suspend fun update(survey: Survey)

    @Delete
    suspend fun delete(survey: Survey)

    @Query("SELECT * FROM health_surveillance_inspection_table ORDER BY create_date ASC")
    fun getAllSurveys(): LiveData<List<Survey>>

    @Query("SELECT * FROM health_surveillance_inspection_table WHERE surveyId = :key")
    fun getSurveyById(key: Int): LiveData<Survey>

    @Query("SELECT * FROM user_table WHERE surveyorId = :key")
    fun getSurveyorById(key: String): LiveData<Surveyor>

    @Query("SELECT * FROM health_surveillance_inspection_table WHERE surveyorId = :key")
    fun getAllSurveysBySurveyorId(key: String): LiveData<List<Survey>>
}