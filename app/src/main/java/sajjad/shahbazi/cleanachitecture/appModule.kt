package sajjad.shahbazi.cleanachitecture


import com.example.featureconversation.ConversationProcessor
import com.example.featureconversation.ConversationViewModel
import com.example.featureconversation.archmodel.ConversationAction
import com.example.featureconversation.archmodel.ConversationResult
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import sajjad.shahbazi.common.mvibase.MviProcessor
import sajjad.shahbazi.featureuser.UserProcessor
import sajjad.shahbazi.featureuser.UserViewModel
import sajjad.shahbazi.featureuser.archmodel.UserAction
import sajjad.shahbazi.featureuser.archmodel.UserResult


val viewModelModules: Module = module {
    viewModel {
        UserViewModel(get())

    }
    viewModel{
        ConversationViewModel(get())
    }
}

val mviProcessorModules : Module = module {
    factory<MviProcessor<UserAction, UserResult>> { UserProcessor(get()) }
    factory<MviProcessor<ConversationAction, ConversationResult>> { ConversationProcessor(get()) }
}