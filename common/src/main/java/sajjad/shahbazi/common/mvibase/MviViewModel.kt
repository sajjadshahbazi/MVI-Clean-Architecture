package sajjad.shahbazi.common.mvibase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MviViewModel<I : MviIntent, S : MviState> {
    val viewState: StateFlow<S>

//    val singleEvent: Flow<E>

    suspend fun processIntent(intent: I)
}

