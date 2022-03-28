package com.camelloncase.testedeperformance09.util

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import java.util.regex.Pattern

fun showMessageToUser(context: Context?, message: String) {

    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

}

fun checkPasswordPattern(password: String): Boolean {

    val regex = "^(?=.*[-@!#\$%^&+=]).{6,}\$"
    val passwordPattern = Pattern.compile(regex)

    return when (password.isEmpty()) {
        true -> false
        else -> passwordPattern.matcher(password).matches()
    }
}

fun checkEmailPattern(email: String): Boolean {

    return when (email.isEmpty()) {
        true -> false
        else -> Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}