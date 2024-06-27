package com.example.featureconversation.archmodel

import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.mvibase.MviState
import sajjad.shahbazi.domain.models.ConversationRepoModel
import sajjad.shahbazi.domain.models.MessageRepoModel

data class ConversationState(
    val conversation: ConversationRepoModel?=null,
    val loading: Boolean,
    val error: ErrorHolder?
) : MviState {
    companion object {
        fun idle(): ConversationState {
            return ConversationState(
                conversation = null,
                loading = false,
                error = null,
            )
        }
    }
}