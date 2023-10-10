package sajjad.shahbazi.featureuser

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import sajjad.shahbazi.common.mvibase.MviProcessor
import sajjad.shahbazi.featureuser.archmodel.UserAction
import sajjad.shahbazi.featureuser.archmodel.UserResult

val viewModelModules: Module = module {
    viewModel { UserViewModel(get()) }
}

val mviProcessorModules : Module = module {
    factory<MviProcessor<UserAction, UserResult>> { UserProcessor(get() )}
}