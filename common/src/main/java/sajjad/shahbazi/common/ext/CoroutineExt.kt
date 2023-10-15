package sajjad.shahbazi.common.ext

import kotlinx.coroutines.flow.*
import sajjad.shahbazi.common.BuildConfig
import timber.log.Timber
import java.util.concurrent.atomic.AtomicInteger

public fun <T> flowFromSuspend(function: suspend () -> T): Flow<T> = flow { emit(function()) }

fun <T> Flow<T>.debugLog(logTag: String, subject: String): Flow<T> =
    if (BuildConfig.DEBUG) {
        onEach { Timber.tag(logTag).d(">>> $subject: $it") }
    } else {
        this
    }

fun <T> SharedFlow<T>.debugLog(logTag: String, subject: String): SharedFlow<T> =
    if (BuildConfig.DEBUG) {
        val self = this

        object : SharedFlow<T> by self {
            val subscriberCount = AtomicInteger(0)

            override suspend fun collect(collector: FlowCollector<T>): Nothing {
                val count = subscriberCount.getAndIncrement()

                self.collect {
                    Timber.tag(logTag).d(">>> $subject ~ $count: $it")
                    collector.emit(it)
                }
            }
        }
    } else {
        this
    }