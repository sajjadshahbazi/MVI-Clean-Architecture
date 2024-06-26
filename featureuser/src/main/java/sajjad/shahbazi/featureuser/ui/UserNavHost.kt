package sajjad.shahbazi.featureuser.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.activity
import androidx.navigation.compose.rememberNavController
import sajjad.shahbazi.common.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.featureconversation.ConversationActivity
import sajjad.shahbazi.common.Navigation.KeyArgs.UserId
import sajjad.shahbazi.common.Navigation.Routes.UserDetail
import sajjad.shahbazi.featureuser.TestActivity
import sajjad.shahbazi.featureuser.UserViewModel


@Composable
fun userNavHost(_viewModel: UserViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.Routes.UsersList) {
        composable(Navigation.Routes.UsersList) {
            previewUserScreen(viewModel = _viewModel, navController)
        }
        composable(
            route = "${UserDetail}/{${UserId}}",
            arguments = listOf(
                navArgument(UserId) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString(UserId) ?: ""
            previewUserDetailScreen(viewModel = _viewModel, uid = userId, navController)
        }

        activity(Navigation.Routes.Conversation){
            activityClass = ConversationActivity::class
        }

        activity(Navigation.Routes.Test){
            activityClass = TestActivity::class
        }
    }
}