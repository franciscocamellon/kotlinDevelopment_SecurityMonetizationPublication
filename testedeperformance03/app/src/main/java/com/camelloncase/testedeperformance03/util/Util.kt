package com.camelloncase.testedeperformance03.util

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

fun formattedCurrentDate(pattern: String): String {

    val date = Calendar.getInstance().time
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())

    return formatter.format(date)
}

fun showMessageToUser(context: Context, message: String) {

    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

}