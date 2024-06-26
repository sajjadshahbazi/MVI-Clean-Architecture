package com.example.featureconversation.archmodel

import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.mvibase.MviResult

sealed class ConversationResult : MviResult{
    data class Error(val error: ErrorHolder) : ConversationResult()
    object Loading : ConversationResult()
}