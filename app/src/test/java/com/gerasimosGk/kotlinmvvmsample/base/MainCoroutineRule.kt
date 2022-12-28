package com.gerasimosGk.kotlinmvvmsample.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * MainCoroutineRule is used only in tests and it has the same synchronous execution behavior found in runBlocking.
 * However, MainCoroutineRule has more specialized testing features not found in runBlocking such as control flow management.
 * In addition, using MainCoroutineRule will significantly make your testing code cleaner.
 * In a much more detailed manner, MainCoroutineRule is directly related to JUnit Rule.
 * If you have used JUnit before, you might remember filling out @Before @After to provide a testing environment for the test cases. If you have multiple test cases,
 * you would eventually have to write multiple @Before @After to provide multiple testing environments, which could possibly lead to redundant boilerplate code. Now,
 * this is where MainCoroutineRule shines! With MainCoroutineRule you can declare a testing environment once and reuse them for multiple test cases.
 *
 * for more See [this](https://github.com/googlecodelabs/kotlin-coroutines/blob/master/coroutines-codelab/finished_code/src/test/java/com/example/android/kotlincoroutines/main/utils/MainCoroutineScopeRule.kt)
 */

/**
 * Rules
 * JUnit rules are classes where you can define generic testing code that can execute before, after, or during a test–-it's a way to take your code that would have been in
 * @Before and @After, and put it in a class where it can be reused.
 * for more See [this](https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-survey#3)
 */

@ExperimentalCoroutinesApi
open class MainCoroutineRule(
    val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(dispatcher) {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}