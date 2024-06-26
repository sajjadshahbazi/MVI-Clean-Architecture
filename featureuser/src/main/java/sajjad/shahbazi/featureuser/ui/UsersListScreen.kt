package sajjad.shahbazi.featureuser.ui


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import sajjad.shahbazi.common.Navigation
import sajjad.shahbazi.domain.models.UserRepoModel
import sajjad.shahbazi.featureuser.UserViewModel


@Composable
fun userList(users: List<UserRepoModel>, onUserClick: (UserRepoModel) -> Unit) {
    LazyColumn {
        items(users) { user ->
            userListItem(user = user, onUserClick)
        }
    }
}

@Composable
fun userListItem(user: UserRepoModel, onUserClick: (UserRepoModel) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                expanded = !expanded
                onUserClick(user)
            }
    ) {
        Text(
            text = user.firstName ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )

        if (expanded) {
            Text(
                text = "Age: ${user.age}",
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Last Name: ${user.lastName}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun userScreen(viewModel: UserViewModel, navController: NavController) {
    val states by viewModel.viewStates.collectAsState()
    if (states.users.isNotEmpty()) {
        Column {
            userList(states.users) { user ->
                user.uid?.let {
                    navController.navigate(Navigation.Routes.Test)
                }
            }
        }
    }
}

@Composable
fun ButtonConversation(_navController: NavController) {
    androidx.compose.material3.Button(
        modifier = Modifier
            .fillMaxWidth(1.0f)
            .height(100.dp),
        onClick = {
            _navController.navigate(Navigation.Routes.Conversation)
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(5.dp, color = Color.Red),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp,
            pressedElevation = 5.dp,
            focusedElevation = 4.dp
        )
    ) {
        androidx.compose.material3.Text(text = "Click ", color = Color.Magenta)
        androidx.compose.material3.Text(text = "Here", color = Color.Green)
    }

}

@Composable
fun previewUserScreen(viewModel: UserViewModel, navController: NavController) {
    userScreen(viewModel, navController)
    ButtonConversation(_navController = navController)
}
