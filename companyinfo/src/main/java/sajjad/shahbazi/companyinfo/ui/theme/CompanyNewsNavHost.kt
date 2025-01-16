package sajjad.shahbazi.companyinfo.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sajjad.shahbazi.common.Navigation
import sajjad.shahbazi.companyinfo.CompanyNewsViewModel

@Composable
fun CompanyNewsNavHost(_viewModel: CompanyNewsViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation.Routes.CompanyNews) {
        composable(Navigation.Routes.CompanyNews) {
            CompanyNewsListScreen(viewModel = _viewModel)
        }
    }
}