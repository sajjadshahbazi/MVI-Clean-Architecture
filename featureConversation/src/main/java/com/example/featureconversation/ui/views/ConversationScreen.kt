package com.example.featureconversation.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.featureconversation.ConversationViewModel


@Composable
fun conversationScreen(viewModel: ConversationViewModel, navController: NavController) {
    viewModel.getMessages(1)
    val states by viewModel.viewStates.collectAsState()
    Text(
        text = states.conversation?.messages?.size.toString(),
        modifier = Modifier
        .fillMaxWidth(1.0f)
        .height(60.dp))

}