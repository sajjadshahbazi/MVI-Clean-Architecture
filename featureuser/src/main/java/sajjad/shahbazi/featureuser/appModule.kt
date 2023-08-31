package sajjad.shahbazi.featureuser

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    viewModel { UserViewModel(get()) }
}