package com.gerasimosGk.kotlinmvvmsample

import androidx.core.util.PatternsCompat
import java.util.regex.Pattern

// Validates a string phone number based on a Regex
fun String.isValidMail(): Boolean {
    return PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
}


private const val phoneRegex = "[0-9]{10}"

// Validates a string mail address based on a Regex
fun String.isValidPhone() : Boolean {
    return Pattern.compile(phoneRegex).matcher(this).matches()
}