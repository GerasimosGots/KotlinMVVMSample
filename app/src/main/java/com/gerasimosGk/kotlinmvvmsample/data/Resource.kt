package com.gerasimosGk.kotlinmvvmsample.data

sealed class Resource<out T> {
    data class Success<out T>(
        val value: T? = null,
    ) : Resource<T>()

    data class Loading<T>(
        val isLoading: Boolean = true,
    ) : Resource<T>()

    data class Error<T>(
        val errorData: ErrorDataModel,
    ) : Resource<T>()

    companion object {
        fun <T> loading(isLoading: Boolean = true): Resource<T> = Loading(isLoading)

        fun <T> error(errorData: ErrorDataModel): Resource<T> = Error(errorData)

        fun <T> success(data: T? = null): Resource<T> = Success(data)
    }
}


/**
 * An extension function on a Resource object.
 * This function should be used to collect a result in the presentation layer,
 * instead of the "old way" using a `when` block
 *
 * Note: Non exhaustive 'when' statements on sealed class/interface will be prohibited in 1.7.
 */
inline fun <T> Resource<T>.collect(
    onLoading: (isLoading: Boolean?) -> Unit = {},
    onSuccess: (result: T?) -> Unit,
    onError: (errorResult: ErrorDataModel) -> Unit = {},
) {
    when (this) {
        is Resource.Loading -> onLoading(this.isLoading)
        is Resource.Success -> onSuccess(this.value)
        is Resource.Error -> onError(this.errorData)
    }
}