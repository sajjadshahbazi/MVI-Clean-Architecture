package sajjad.shahbazi.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import sajjad.shahbazi.common.mvibase.MviIntent
import sajjad.shahbazi.common.mvibase.MviState

abstract class BaseViewModel<I : MviIntent, S : MviState> : ViewModel(){
    abstract val viewState: StateFlow<S>
    abstract suspend fun processIntent(intent: Flow<I>)
}

