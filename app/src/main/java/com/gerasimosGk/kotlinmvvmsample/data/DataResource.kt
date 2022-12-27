package com.gerasimosGk.kotlinmvvmsample.data

import com.gerasimosGk.kotlinmvvmsample.R

sealed class DataResource<out T> {
    data class Success<out T>(val value: T? = null) : DataResource<T>()

    data class Error<T>(
        val errorData: ErrorDataModel,
    ) : DataResource<T>()

    companion object {

        fun <T> error(
            errorData: ErrorDataModel,
        ): DataResource<T> = Error(errorData)

        fun <T> defaultError(errorData: ErrorDataModel? = null): DataResource<T> {

            val errorModel = ErrorDataModel(
                errorType = ErrorType.CustomError,
                errorMessage  = R.string.generic_error_message
            )

            return DataResource.Error(errorData = errorModel)
        }

        fun <T> success(data: T? = null): DataResource<T> = Success(data)
    }
}


/**
 * Extension function to map a simple DataResource to a Resource,
 * when no other transformation or logic required
 */
fun <T> DataResource<T>.toResource(): Resource<T> {
    return when (this) {
        is DataResource.Success -> {
            Resource.success(this.value)
        }
        is DataResource.Error -> {
            Resource.error(this.errorData)
        }
    }
}

fun <T> DataResource<T>.toRawValue(): T? {
    return when (this) {
        is DataResource.Success -> {
            this.value as T
        }
        is DataResource.Error -> {
            null
        }
    }
}