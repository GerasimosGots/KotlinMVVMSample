package com.gerasimosGk.kotlinmvvmsample.data.model.api

import com.gerasimosGk.kotlinmvvmsample.R
import com.gerasimosGk.kotlinmvvmsample.data.model.domain.Resource
import com.gerasimosGk.kotlinmvvmsample.data.model.error.ErrorDataModel
import com.gerasimosGk.kotlinmvvmsample.data.model.error.ErrorType

/**
 * Sealed wrapper class representing the simple states of a data call, that is
 *  1. Success
 *  2. Error
 *
 *  This class can contain a value object [T].
 *  In Data layer the Repository is responsible to map the data from the call to a DataResource object.
 *  In Domain layer the UseCase is responsible to get a DataResource object and map it according
 *  to some business rules.
 *  After completion the UseCase will return a DataResource to the next layer (e.g Presentation).
 */
sealed class DataResource<out T> {
    data class Success<out T>(val value: T) : DataResource<T>()

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

        fun <T> success(data: T): DataResource<T> = Success(data)
    }
}


/**
 * Extension function to map a simple Object to a Success Resource,
 * when no other transformation or logic required
 */
fun <T> T.toSuccessResource(): Resource<T> {
    return Resource.success(this)
}


/**
 * Extension function to map a simple Object to a Error Resource,
 * when no other transformation or logic required
 */
fun ErrorDataModel.toErrorResource(): Resource<ErrorDataModel> {
    return Resource.error(this)
}
