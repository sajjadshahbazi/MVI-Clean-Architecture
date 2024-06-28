package com.example.featureconversation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.featureconversation.archmodel.ConversationAction
import com.example.featureconversation.archmodel.ConversationIntent
import com.example.featureconversation.archmodel.ConversationResult
import com.example.featureconversation.archmodel.ConversationState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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
                .take(1),
            filterIsInstance<ConversationIntent.LoadMore>()
        )

    private fun actionFromIntent(intent: ConversationIntent): ConversationAction {
        return when (intent) {
            is ConversationIntent.InitialIntent -> {
                ConversationAction.GetMessages
            }

            is ConversationIntent.LoadMore -> {
                Log.d("Sahhad", "************* ")
                ConversationAction.LoadMoreMessages
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
                    error = null
                )
            }

            is ConversationResult.Error -> {
                previousState.copy(
                    error = result.error,
                    loading = false
                )
            }

            is ConversationResult.Messages -> {

                previousState.messages.addAll(result.conversation.messages)
                Log.d("Sahhad", "0000000000000000 ${previousState.messages.size}")
                previousState.copy(
                    page = result.conversation.page,
                    size = result.conversation.size,
                    error = null,
                    loading = false
                )
            }
        }
    }

    fun getMessages(){
        viewModelScope.launch { viewIntents.emit(ConversationIntent.InitialIntent) }
    }

    fun loadMoreMessages(){

        viewModelScope.launch {
            viewIntents.emit(ConversationIntent.LoadMore)
            Log.d("Sahhad", "======================")
        }

    }
}