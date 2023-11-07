package sajjad.shahbazi.featureuser.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import sajjad.shahbazi.domain.models.UserRepoModel
import sajjad.shahbazi.featureuser.UserViewModel

@Composable
fun UserList(users: List<UserRepoModel>, onUserClick: (UserRepoModel) -> Unit) {
    LazyColumn {
        items(users) { user ->
            UserListItem(user = user, onUserClick)
        }
    }
}

@Composable
fun UserListItem(user: UserRepoModel, onUserClick: (UserRepoModel) -> Unit) {
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
            text = user.firstName?:"",
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
fun UserDetails(user: UserRepoModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = user.firstName?:"",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )
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

@Composable
fun UserScreen(viewModel: UserViewModel) {
    var selectedUser by remember { mutableStateOf<UserRepoModel?>(null) }
    val states by viewModel.viewStates.collectAsState()
    if (states.users.isNotEmpty()) {
        Column {
            UserList(states.users) { user ->
                selectedUser = user
                selectedUser?.uid?.let {
                    viewModel.getDetailUser(it)
                }
            }
        }
    }
    if (states.user != null) {
        states.user?.let {
            UserDetails(user = it)
        }
    }
}

@Composable
fun PreviewUserScreen(viewModel : UserViewModel) {
    UserScreen(viewModel)
}
