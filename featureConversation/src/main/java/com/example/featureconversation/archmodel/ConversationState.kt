package com.example.featureconversation.archmodel

import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.mvibase.MviState
import sajjad.shahbazi.domain.models.ConversationRepoModel
import sajjad.shahbazi.domain.models.MessageRepoModel

data class ConversationState(
    val messages: MutableList<MessageRepoModel> = mutableListOf<MessageRepoModel>(),
    val page: Int,
    val size: Int,
    val loading: Boolean,
    val error: ErrorHolder?
) : MviState {
    companion object {
        fun idle(): ConversationState {
            return ConversationState(
                messages = mutableListOf<MessageRepoModel>(),
                page = 0,
                size = 0,
                loading = false,
                error = null,
            )
        }
    }
}