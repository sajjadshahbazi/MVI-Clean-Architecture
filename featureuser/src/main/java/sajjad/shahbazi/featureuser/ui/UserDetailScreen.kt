package sajjad.shahbazi.featureuser.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import sajjad.shahbazi.domain.models.UserRepoModel
import sajjad.shahbazi.featureuser.UserViewModel


@Composable
fun UserDetails(user: UserRepoModel, _navigation : NavController) {
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
        Button(
            onClick = {
                // Save logic here
                _navigation.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp)
        ) {
            Text("Back")
        }
    }
}

@Composable
fun previewUserDetailScreen(viewModel : UserViewModel, uid : String, navController: NavController) {

    viewModel.getDetailUser(uid)
    val states by viewModel.viewStates.collectAsState()
    if (states.user != null) {
        states.user?.let {
            UserDetails(user = it, _navigation = navController)
        }
    }
}
