package com.gerasimosGk.kotlinmvvmsample.data.network

import com.gerasimosGk.kotlinmvvmsample.data.DataResource
import com.gerasimosGk.kotlinmvvmsample.data.toErrorDataResource
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

suspend fun <T> performApiCall(
    dispatcher: kotlinx.coroutines.CoroutineDispatcher,
    apiCall: suspend () -> T,
): DataResource<T> {
    return withContext(dispatcher) {
        try {
            val result = apiCall.invoke()

            when {
                result is Response<*> && result.isSuccessful -> {
                    DataResource.success(result)
                }

                else -> {
                    Timber.e( "throw HttpException -> $result" )
                Timber.e( "throw HttpException -> " + (result as? Response<*>)?.errorBody())

                    throw HttpException(result as Response<*>)
                }
            }
        } catch (throwable: Throwable) {
            Timber.e("caught throwable -> $throwable")
            throwable.toErrorDataResource()
        }
    }
}