package com.example.featureconversation.archmodel

import sajjad.shahbazi.common.mvibase.MviAction

sealed class ConversationAction : MviAction{
    data class LoadMessages(val page : Int): ConversationAction()
}