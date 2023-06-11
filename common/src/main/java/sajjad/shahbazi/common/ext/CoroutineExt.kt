package sajjad.shahbazi.common.ext

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

public fun <T> flowFromSuspend(function: suspend () -> T): Flow<T> = flow { emit(function()) }