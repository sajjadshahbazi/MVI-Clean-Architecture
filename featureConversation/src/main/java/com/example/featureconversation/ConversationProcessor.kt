package com.example.featureconversation

import com.example.featureconversation.archmodel.ConversationAction
import com.example.featureconversation.archmodel.ConversationResult
import kotlinx.coroutines.flow.*
import sajjad.shahbazi.common.mvibase.MviProcessor


class ConversationProcessor (): MviProcessor<ConversationAction, ConversationResult> {

    override fun actionProcessor(actions: Flow<ConversationAction>): Flow<ConversationResult> =
        merge(
            actions.filterIsInstance<ConversationAction.Init>().let(::fakeRes),
        )

    private fun fakeRes(actions: Flow<ConversationAction.Init>): Flow<ConversationResult> =
        actions.transform<ConversationAction, ConversationResult> {
                        emit(ConversationResult.Loading)
            }

}