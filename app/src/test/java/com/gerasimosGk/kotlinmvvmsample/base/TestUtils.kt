package com.gerasimosGk.kotlinmvvmsample.base

import com.gerasimosGk.kotlinmvvmsample.data.model.api.DataResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

// Returns If DataResource is Success
fun <T> DataResource<T>.isSuccess(): Boolean {
    return this is DataResource.Success
}

// Returns If DataResource is Error
fun <T> DataResource<T>?.isError(): Boolean {
    return this is DataResource.Error
}

// Return tha value of the Success DataResource
fun <T> DataResource<T>?.getRawValue(): T? {
    return (this as? DataResource.Success)?.value
}

@ExperimentalCoroutinesApi
// Run a coroutine from MainCoroutineRule of the test
// Usage in Test only!
fun MainCoroutineRule.runTestCoroutine(block: suspend () -> Unit) =
    this.dispatcher.runBlockingTest {
        block()
    }