package com.camelloncase.testedeperformance01.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SurveyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(survey: Survey)

    @Update
    suspend fun update(survey: Survey)

    @Delete
    suspend fun delete(survey: Survey)

    @Query("Select * from craft_beer_recipe_table order by recipeId ASC")
    fun getAllNotes(): LiveData<List<Survey>>
}