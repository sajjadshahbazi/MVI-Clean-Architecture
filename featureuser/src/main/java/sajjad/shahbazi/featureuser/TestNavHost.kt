package sajjad.shahbazi.featureuser

import androidx.compose.runtime.Composable
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sajjad.shahbazi.common.Navigation
import sajjad.shahbazi.featureuser.ui.UiTest

@Composable
fun TesNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.Routes.Test) {
        composable(Navigation.Routes.Test){
            UiTest()
        }
    }
}