package com.gerasimosGk.kotlinmvvmsample.data

import androidx.annotation.StringRes

data class ErrorDataModel(
    var errorType: ErrorType,
    @StringRes var errorMessage: Int? = null
)