package com.camelloncase.testedeperformance03.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_surveillance_inspection_table")
data class Survey (

    @PrimaryKey(autoGenerate = true) var surveyId: Int = 0,

    @ColumnInfo(name = "surveyor_id")
    val surveyorId: String,

    @ColumnInfo(name = "business_name")
    val businessName: String,

    @ColumnInfo(name = "neighborhood_name")
    val neighborhoodName: String,

    @ColumnInfo(name = "first_answer")
    val firstAnswer: String,

    @ColumnInfo(name = "second_answer")
    val secondAnswer: String,

    @ColumnInfo(name = "third_answer")
    val thirdAnswer: String,

    @ColumnInfo(name = "fourth_answer")
    val fourthAnswer: String,

    @ColumnInfo(name = "fifth_answer")
    val fifthAnswer: String,

    @ColumnInfo(name = "sixth_answer")
    val sixthAnswer: String,

    @ColumnInfo(name = "create_date")
    val createDate: String,

    @Embedded var surveyor: Surveyor? = null

)

data class Surveyor (

    @PrimaryKey(autoGenerate = true) var surveyorId: Int = 0,

    @ColumnInfo(name = "surveyor_name")
    val surveyorName: String,

    @ColumnInfo(name = "surveyor_email")
    val surveyorEmail: String,

    @ColumnInfo(name = "surveyor_user_name")
    val surveyorUserName: String,
)