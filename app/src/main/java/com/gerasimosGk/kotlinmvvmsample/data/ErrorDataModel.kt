package com.gerasimosGk.kotlinmvvmsample.data

data class ErrorDataModel(
    var errorType: ErrorType,
    var errorMessage: String? = null
)