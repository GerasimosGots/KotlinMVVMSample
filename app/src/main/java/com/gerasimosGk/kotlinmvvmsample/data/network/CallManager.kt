package com.gerasimosGk.kotlinmvvmsample.data.network

import com.gerasimosGk.kotlinmvvmsample.data.model.api.DataResource
import com.gerasimosGk.kotlinmvvmsample.data.model.error.toErrorDataResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

/**
 * A function responsible to make the network calls.
 * Takes as input a CoroutineDispatcher (for the network cases we use Dispatchers.IO)
 * and a function with the service call that returns a [T] object result
 */
suspend fun <T> performApiCall(
    dispatcher: CoroutineDispatcher,
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
                    throw HttpException(result as Response<*>)
                }
            }
        } catch (throwable: Throwable) {
            Timber.e("caught throwable -> $throwable")
            throwable.toErrorDataResource()
        }
    }
}