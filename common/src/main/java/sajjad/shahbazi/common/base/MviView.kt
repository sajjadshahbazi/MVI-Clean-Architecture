package sajjad.shahbazi.common.base

import sajjad.shahbazi.common.mvibase.MviIntent
import sajjad.shahbazi.common.mvibase.MviState
import kotlinx.coroutines.flow.Flow

interface MviView<out I : MviIntent, in S : MviState> {
    fun intents(): Flow<I>
    fun render(state: S)
}
