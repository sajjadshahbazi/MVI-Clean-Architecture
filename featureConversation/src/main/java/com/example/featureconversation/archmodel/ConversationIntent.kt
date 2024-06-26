package com.example.featureconversation.archmodel

import sajjad.shahbazi.common.mvibase.MviIntent

sealed class ConversationIntent : MviIntent{
    object InitialIntent : ConversationIntent()
}