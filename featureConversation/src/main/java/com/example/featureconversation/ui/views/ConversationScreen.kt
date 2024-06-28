package com.example.featureconversation.ui.views


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.featureconversation.ConversationViewModel
import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.domain.models.MessageRepoModel


@Composable
fun conversationScreen(viewModel: ConversationViewModel, navController: NavController) {
    viewModel.getMessages()
    val states by viewModel.viewStates.collectAsState()
    val listState = rememberLazyListState()
    var requestLoadMoreLock = false


    val threshold = 3
    val sizaList = states.messages.size

    if (states.error != null && states.error is ErrorHolder.Message) {
        Toast.makeText(
            LocalContext.current,
            (states.error as ErrorHolder.Message).message,
            Toast.LENGTH_SHORT
        ).show()
    }

    loading(states.loading)

    Log.d("Sahhad", " GGGGGGGG REEEECOMPSE ${requestLoadMoreLock}")

    LazyColumn(
        state = listState,
        reverseLayout = true,
        modifier = Modifier.fillMaxSize()
    ) {
        items(count = states.messages.size) { index ->
            MessageItem(message = states.messages[index])
            if ((((listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                    ?: 0) + threshold) > +sizaList) && !requestLoadMoreLock
            ) {
                requestLoadMoreLock = true
                LaunchedEffect(listState) {
                    viewModel.loadMoreMessages()
                }
            }
        }
    }
}


@Composable
fun MessageItem(message: MessageRepoModel?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (message?.sendByMe == true) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            modifier = Modifier
                .background(
                    if (message?.sendByMe == true) Color(0xFFDCF8C6) else Color.White
                )
                .padding(8.dp)
                .widthIn(max = 250.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = 1.dp
        ) {
            Text(
                text = message?.text ?: "",
                textAlign = if (message?.sendByMe == true) TextAlign.End else TextAlign.Start,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
private fun loading(isLoading: Boolean) {
    if (!isLoading)
        return
    Column(
        modifier = Modifier
            .width(64.dp)
            .height(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp)
                .height(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }

}