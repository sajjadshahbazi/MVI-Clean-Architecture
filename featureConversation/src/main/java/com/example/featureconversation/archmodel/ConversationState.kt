package com.example.featureconversation.archmodel

import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.mvibase.MviState
import sajjad.shahbazi.domain.models.UserRepoModel

data class ConversationState(
    val user: UserRepoModel?,
    val users: List<UserRepoModel>,
    val loading: Boolean,
    val error: ErrorHolder?
) : MviState {
    companion object {
        fun idle(): ConversationState {
            return ConversationState(
                user = null,
                users = emptyList(),
                loading = false,
                error = null,
            )
        }
    }
}