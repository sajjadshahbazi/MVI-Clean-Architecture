package sajjad.shahbazi.common.mvibase

import androidx.annotation.CheckResult
import kotlinx.coroutines.flow.Flow

interface MviView<I : MviIntent,
        S : MviState
//        E : MviSingleEvent,
        > {
    /**
     * Entry point for the [MviView] to render itself based on a [MviViewState].
     */
    fun render(viewState: S)

    /**
     * Entry point for the [MviView] to handle single event.
     */
//    fun handleSingleEvent(event: E)

    /**
     * Unique [Flow] used by the [MviViewModel] to listen to the [MviView].
     * All the [MviView]'s [MviIntent]s must go through this [Flow].
     */
    @CheckResult
    fun viewIntents(): Flow<I>
}