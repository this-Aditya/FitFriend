package com.aditya.fitfriend_android

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Collects the values of a [Flow] or waits for it to emit values, with a timeout.
 *
 * Use this extension from host-side (JVM) tests.
 */
@ExperimentalCoroutinesApi
suspend fun <T> Flow<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)

    val job = CoroutineScope(Dispatchers.Default).launch {
        try {
            collect {
                data = it
                latch.countDown()
            }
        } catch (e: CancellationException) {
            // Ignore cancellation exception
        }
    }

    try {
        // Don't wait indefinitely if the Flow doesn't emit values.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("Flow didn't emit any values within the specified time.")
        }
    } finally {
        job.cancel()
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}
