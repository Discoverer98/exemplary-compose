package com.discoverer.exemplary.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * A class that encapsulate the special code needed to deal with Kotlin coroutines inside unit tests.
 * Unfortunately (as far as I know) this is still marked as an "experimental" extension, hence why we
 * need to do all these fun things with the Dispatcher. The good news is that by using this rule you
 * don't need to repeat this code all over your unit test classes.
 */
@ExperimentalCoroutinesApi
class MainCoroutineRule(private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    TestWatcher(), TestCoroutineScope by TestCoroutineScope(dispatcher)  {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

} // MainCoroutineRule class