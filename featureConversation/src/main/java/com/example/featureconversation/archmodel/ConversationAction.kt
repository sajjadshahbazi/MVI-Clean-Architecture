package com.example.featureconversation.archmodel

import sajjad.shahbazi.common.mvibase.MviAction

sealed class ConversationAction : MviAction{
    object GetMessages: ConversationAction()
    object LoadMoreMessages: ConversationAction()
}