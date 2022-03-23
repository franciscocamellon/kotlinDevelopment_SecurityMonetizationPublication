package com.camelloncase.testedeperformance03.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "health_surveillance_survey_table")
class Survey (

    @ColumnInfo(name = "surveyor_id")
    val surveyorId: String?,

    @ColumnInfo(name = "business_name")
    val businessName: String,

    @ColumnInfo(name = "neighborhood_name")
    val neighborhoodName: String,

    @ColumnInfo(name = "first_answers")
    val firstAnswer: String,

    @ColumnInfo(name = "second_answers")
    val secondAnswer: String,

    @ColumnInfo(name = "third_answers")
    val thirdAnswer: String,

    @ColumnInfo(name = "fourth_answers")
    val fourthAnswer: String,

    @ColumnInfo(name = "fifth_answers")
    val fifthAnswer: String,

    @ColumnInfo(name = "sixth_answers")
    val sixthAnswer: String,

    @ColumnInfo(name = "create_date")
    val createDate: String,

    ) {
    @PrimaryKey(autoGenerate = true) var surveyId = 0
}
