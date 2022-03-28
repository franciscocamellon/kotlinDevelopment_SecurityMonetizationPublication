package com.camelloncase.testedeperformance09.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public class Notification (

    @SerializedName("week_alias")
    @Expose
    val weekAlias: String,

    @SerializedName("week_start")
    @Expose
    val weekStart: String,

    @SerializedName("week_observations")
    @Expose
    val weekObservations: String,

    @SerializedName("week_create_date")
    @Expose
    val weekCreateDate: String,

    @SerializedName("week_update_date")
    @Expose
    val weekUpdateDate: String,
)