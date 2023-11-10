package sajjad.shahbazi.featureuser.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import sajjad.shahbazi.common.Navigation.Routes.UserDetail
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
fun userScreen(viewModel: UserViewModel, navController: NavController) {
    val states by viewModel.viewStates.collectAsState()
    if (states.users.isNotEmpty()) {
        Column {
            userList(states.users) { user ->
                user.uid?.let {
                    navController.navigate("${UserDetail}/${it}")
                }
            }
        }
    }
}

@Composable
fun previewUserScreen(viewModel : UserViewModel, navController: NavController) {
    userScreen(viewModel, navController)
}
