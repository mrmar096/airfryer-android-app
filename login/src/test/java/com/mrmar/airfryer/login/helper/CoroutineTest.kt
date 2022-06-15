package com.mrmar.airfryer.login.helper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
open class CoroutineTest {
    private val scope = TestScope()

    protected lateinit var dispatcher: TestDispatcher

    @Before
    fun setUpCoroutines() {
        dispatcher = StandardTestDispatcher(scope.testScheduler)
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    fun runBlockingTest(block: suspend TestScope.() -> Unit) {
        scope.runTest(testBody = block)
    }

}