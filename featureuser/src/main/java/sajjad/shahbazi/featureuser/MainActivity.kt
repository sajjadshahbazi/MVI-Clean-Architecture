package sajjad.shahbazi.featureuser

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import org.koin.androidx.viewmodel.ext.android.viewModel
import sajjad.shahbazi.common.base.BaseActivity
import sajjad.shahbazi.featureuser.archmodel.UserIntent
import sajjad.shahbazi.featureuser.archmodel.UserState
import sajjad.shahbazi.featureuser.databinding.ActivityMainBinding

class MainActivity : BaseActivity<
        UserIntent,
        UserState,
        UserViewModel>(R.layout.activity_main) {

//    private val binding by viewBinding<MainActivity>()
    override val viewModel : UserViewModel by viewModel()
    override fun setupViews() {
        ActivityMainBinding.
    }

    override fun intents(): Flow<UserIntent> = merge(
        flow { emit(UserIntent.InitialIntent) }
    )

    override fun render(state: UserState) {
        showUsersList(_state = state)
    }

    private fun showUsersList(_state : UserState){
        if (_state.users.isNotEmpty()) {
//            tvShowUser
        }
    }
}