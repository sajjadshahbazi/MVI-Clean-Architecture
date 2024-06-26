package com.example.featureconversation

import androidx.lifecycle.viewModelScope
import com.example.featureconversation.archmodel.ConversationAction
import com.example.featureconversation.archmodel.ConversationIntent
import com.example.featureconversation.archmodel.ConversationResult
import com.example.featureconversation.archmodel.ConversationState
import kotlinx.coroutines.flow.*
import sajjad.shahbazi.common.mvibase.MviProcessor
import sajjad.shahbazi.common.base.BaseViewModel

class ConversationViewModel(
    private val processor: MviProcessor<ConversationAction, ConversationResult>
) : BaseViewModel<
        ConversationIntent,
        ConversationState>() {

    private var sharedFlow: MutableSharedFlow<ConversationIntent> = MutableSharedFlow<ConversationIntent>()
    override val viewState: StateFlow<ConversationState> = compose()
    val viewIntents: MutableSharedFlow<ConversationIntent> = MutableSharedFlow()
    val viewStates: MutableStateFlow<ConversationState> = MutableStateFlow(ConversationState.idle())

    private fun Flow<ConversationIntent>.intentFilter(): Flow<ConversationIntent> =
        merge(
            filterIsInstance<ConversationIntent.InitialIntent>()
                .take(1)
        )

    private fun actionFromIntent(intent: ConversationIntent): ConversationAction {
        return when (intent) {
            is ConversationIntent.InitialIntent -> {
                ConversationAction.Init
            }
        }
    }

    override suspend fun processIntent(intent: ConversationIntent) {
        sharedFlow.emit(intent)
    }

    private fun compose(): StateFlow<ConversationState> {
        return sharedFlow.intentFilter()
            .map(::actionFromIntent)
            .let(processor::actionProcessor)
            .scan(ConversationState.idle(), ::reducer)
            .distinctUntilChanged()
            .stateIn<ConversationState>(viewModelScope, SharingStarted.Eagerly, ConversationState.idle())
    }

    private fun reducer(previousState: ConversationState, result: ConversationResult): ConversationState {
        return when (result) {
            is ConversationResult.Loading -> {
                previousState.copy(
                    loading = true,
                    error = null,
                    user = null
                )
            }

            is ConversationResult.Error -> {
                previousState.copy(
                    error = result.error,
                    loading = false
                )
            }
        }
    }
}