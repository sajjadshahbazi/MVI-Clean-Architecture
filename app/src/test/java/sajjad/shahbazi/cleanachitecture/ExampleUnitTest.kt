package sajjad.shahbazi.cleanachitecture

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    suspend fun performRequest(request: Int): String {
        delay(1000) // imitate long-running asynchronous work
        return "response $request"
    }

    @Test
    fun addition_isCorrect() {
        runBlocking<Unit> {
            (1..3).asFlow() // a flow of requests
                .transform { request ->
                    emit("Making request $request")
                    emit(performRequest(request))
                }.collect { response -> println(response) }
        }
    }
}