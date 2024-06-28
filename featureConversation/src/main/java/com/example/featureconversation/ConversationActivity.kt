package com.example.featureconversation

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import com.example.featureconversation.archmodel.ConversationIntent
import com.example.featureconversation.archmodel.ConversationState
import com.example.featureconversation.ui.views.conversationNavHost
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import sajjad.shahbazi.common.base.BaseActivity


class ConversationActivity : BaseActivity<
        ConversationIntent,
        ConversationState,
        ConversationViewModel>(
) {

    override val viewModel: ConversationViewModel by viewModel()
    override fun setupViews() {
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    conversationNavHost(viewModel)
                }
            }
        }
    }

    override fun intents(): Flow<ConversationIntent> =
        merge(
            flowOf(ConversationIntent.InitialIntent),
            viewModel.viewIntents
        )

    override fun render(state: ConversationState) {
        viewModel.viewModelScope.launch {
            viewModel.viewStates.emit(state)
        }
    }
}