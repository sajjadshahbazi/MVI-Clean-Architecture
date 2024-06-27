package com.example.featureconversation

import com.example.featureconversation.archmodel.ConversationAction
import com.example.featureconversation.archmodel.ConversationResult
import kotlinx.coroutines.flow.*
import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.mvibase.MviProcessor
import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.repositories.ConversationRepository


class ConversationProcessor(
    private val conversationRepository: ConversationRepository
) : MviProcessor<ConversationAction, ConversationResult> {

    override fun actionProcessor(actions: Flow<ConversationAction>): Flow<ConversationResult> =
        merge(
            actions.filterIsInstance<ConversationAction.LoadMessages>().let(::getMessages)
        )

    private fun getMessages(actions: Flow<ConversationAction.LoadMessages>): Flow<ConversationResult> =
        actions.transform<ConversationAction, ConversationResult> {
            when (val result = conversationRepository.getMessages()) {
                is ApiResult.Success -> {
                    result.data?.let {
                        emit(ConversationResult.Messages(it))
                    } ?: kotlin.run {
                        emit(ConversationResult.Error(ErrorHolder.Message("error unknown")))
                    }
                }

                is ApiResult.Error -> {
                    emit(ConversationResult.Error(ErrorHolder.Message(result.e.message ?: "")))
                }
            }
        }

}