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
    private var page = 0
    private var size = 0
    private var loadMoreRequestLocker : Boolean = false
    override fun actionProcessor(actions: Flow<ConversationAction>): Flow<ConversationResult> =
        merge(
            actions.filterIsInstance<ConversationAction.GetMessages>().let(::getMessages),
            actions.filterIsInstance<ConversationAction.LoadMoreMessages>().let(::loadMoreMessages)
        )

    private fun getMessages(actions: Flow<ConversationAction.GetMessages>): Flow<ConversationResult> =
        actions.transform<ConversationAction, ConversationResult> {
            if (!loadMoreRequestLocker) {
                loadMoreRequestLocker = true
                emit(ConversationResult.Loading)
                when (val result = conversationRepository.getMessages()) {
                    is ApiResult.Success -> {
                        size = result.data?.size ?: 0
                        page = result.data?.page ?: 0
                        result.data?.let {
                            emit(ConversationResult.Messages(it))
                        } ?: kotlin.run {
                            emit(ConversationResult.Error(ErrorHolder.Message("error unknown")))
                        }
                        loadMoreRequestLocker = false
                    }

                    is ApiResult.Error -> {
                        emit(ConversationResult.Error(ErrorHolder.Message(result.e.message ?: "")))
                        loadMoreRequestLocker = false
                    }
                }
            }
        }

    private fun loadMoreMessages(actions: Flow<ConversationAction.LoadMoreMessages>): Flow<ConversationResult> =
        actions.transform<ConversationAction, ConversationResult> {
            if (loadMoreRequestLocker) {
            }else if (page>=size){
                emit(ConversationResult.Error(error = ErrorHolder.Message("out of size")))
            }else {
                loadMoreRequestLocker = true
                emit(ConversationResult.Loading)
                when (val result = conversationRepository.loadMoreMessages(page)) {
                    is ApiResult.Success -> {
                        size = result.data?.size?:0
                        page = result.data?.page?:0
                        result.data?.let {
                            emit(ConversationResult.Messages(it))
                        } ?: kotlin.run {
                            emit(ConversationResult.Error(ErrorHolder.Message("error unknown")))
                        }
                        loadMoreRequestLocker = false
                    }

                    is ApiResult.Error -> {
                        emit(ConversationResult.Error(ErrorHolder.Message(result.e.message ?: "")))
                        loadMoreRequestLocker = false
                    }
                }
            }
        }
}