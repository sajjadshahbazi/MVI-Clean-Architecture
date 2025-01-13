package sajjad.shahbazi.featureuser

import androidx.activity.compose.setContent
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import sajjad.shahbazi.common.base.BaseActivity
import sajjad.shahbazi.featureuser.archmodel.UserIntent
import sajjad.shahbazi.featureuser.archmodel.UserState
import sajjad.shahbazi.featureuser.ui.UserNavHost


class MainActivity : BaseActivity<
        UserIntent,
        UserState,
        UserViewModel>(
) {

    override val viewModel: UserViewModel by viewModel()
    override fun setupViews() {
        setContent {
            UserNavHost(viewModel)
        }
    }

    override fun intents(): Flow<UserIntent> =
        merge(
            flowOf(UserIntent.InitialIntent),
            viewModel.viewIntents
        )

    override fun render(state: UserState) {
        viewModel.viewModelScope.launch {
            viewModel.viewStates.emit(state)
        }
    }
}