package com.example.featureconversation.archmodel

import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.mvibase.MviResult
import sajjad.shahbazi.domain.models.ConversationRepoModel
import sajjad.shahbazi.domain.models.MessageRepoModel

sealed class ConversationResult : MviResult{
    data class Messages(val conversation: ConversationRepoModel) : ConversationResult()
    data class Error(val error: ErrorHolder) : ConversationResult()
    object Loading : ConversationResult()
}