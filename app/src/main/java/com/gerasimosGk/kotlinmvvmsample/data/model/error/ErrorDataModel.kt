package com.gerasimosGk.kotlinmvvmsample.data.model.error

import androidx.annotation.StringRes
import com.gerasimosGk.kotlinmvvmsample.R

data class ErrorDataModel(
    var errorType: ErrorType,
    @StringRes var errorMessage: Int = R.string.error_communication_msg
)