package com.gerasimosGk.kotlinmvvmsample.data

import com.gerasimosGk.kotlinmvvmsample.R
import retrofit2.HttpException
import timber.log.Timber

/**
 * Build ErrorDataModel based on a specific throwable
 */
internal fun <T> Throwable.toErrorDataResource(): DataResource.Error<T> {
    val throwable = this
    val errorType = throwable.mapToErrorType()

    val errorDataModel = throwable.buildErrorDataModelByType(errorType = errorType)
    Timber.e( "errorDataModel = $errorDataModel")

    return DataResource.Error(errorDataModel)
}

internal fun Throwable.mapToErrorType(): ErrorType {
    return when (this) {
        is HttpException -> ErrorType.ApiErrorResponse

        else -> ErrorType.CustomError
    }
}

//From ErrorType to ErrorDataModel
internal fun Throwable?.buildErrorDataModelByType(errorType: ErrorType): ErrorDataModel {
    return when (errorType) {
        ErrorType.ApiErrorResponse -> {
            ErrorDataModel(
                errorType = ErrorType.CustomError,
                errorMessage = R.string.error_api_error_msg
            )
        }

        ErrorType.NetworkConnection -> {
            ErrorDataModel(
                errorType = ErrorType.CustomError,
                errorMessage = R.string.error_network_msg
            )
        }

        else -> {
            ErrorDataModel(
                errorType = ErrorType.CustomError,
                errorMessage = R.string.error_communication_msg
            )
        }
    }
}