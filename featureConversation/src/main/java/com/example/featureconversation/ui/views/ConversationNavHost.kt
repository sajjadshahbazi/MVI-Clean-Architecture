package com.example.featureconversation.ui.views

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import sajjad.shahbazi.common.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.featureconversation.ConversationViewModel




@Composable
fun conversationNavHost(_viewModel: ConversationViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.Routes.Conversation) {
        composable(Navigation.Routes.Conversation) {
            conversationScreen(viewModel = _viewModel, navController)
        }
    }
}