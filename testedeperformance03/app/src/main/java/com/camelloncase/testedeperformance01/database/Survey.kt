package com.camelloncase.testedeperformance01.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "craft_beer_recipe_table")
class Survey (

    @ColumnInfo(name = "business_name")
    val businessName: String,

    @ColumnInfo(name = "neighborhood_name")
    val neighborhoodName: String,

    @ColumnInfo(name = "first_question")
    val firstQuestion: String,

    @ColumnInfo(name = "second_question")
    val secondQuestion: String,

    @ColumnInfo(name = "third_question")
    val thirdQuestion: String,

    @ColumnInfo(name = "fourth_question")
    val fourthQuestion: String,

    @ColumnInfo(name = "fifth_question")
    val fifthQuestion: String,

    @ColumnInfo(name = "sixth_question")
    val sixthQuestion: String

    ) {
    @PrimaryKey(autoGenerate = true) var surveyorId = 0
}
