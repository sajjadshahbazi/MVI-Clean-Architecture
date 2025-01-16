package sajjad.shahbazi.companyinfo.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import sajjad.shahbazi.common.Navigation
import sajjad.shahbazi.companyinfo.CompanyNewsViewModel

val INDEX_NEWS="index"

@Composable
fun CompanyNewsNavHost(viewModel: CompanyNewsViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.Routes.CompanyNews) {
        composable(Navigation.Routes.CompanyNews) {
            CompanyNewsListScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = "${Navigation.Routes.NewsDetails}/{${INDEX_NEWS}}",
            arguments = listOf(navArgument(INDEX_NEWS) { type = NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt(INDEX_NEWS)
            index?.let {
                NewsDetailsScreen(viewModel = viewModel, index = it)
            }
        }
    }
}

