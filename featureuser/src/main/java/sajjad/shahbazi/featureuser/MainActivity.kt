package sajjad.shahbazi.featureuser

import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sajjad.shahbazi.common.base.BaseActivity
import sajjad.shahbazi.common.base.clicks
import sajjad.shahbazi.domain.models.UserRepoModel
import sajjad.shahbazi.featureuser.archmodel.UserIntent
import sajjad.shahbazi.featureuser.archmodel.UserState
import sajjad.shahbazi.featureuser.databinding.ActivityMainBinding

class MainActivity : BaseActivity<
        UserIntent,
        UserState,
        UserViewModel>(
) {

    private var binding: ActivityMainBinding? = null
    private var users: MutableList<UserRepoModel> = arrayListOf()

    override val viewModel: UserViewModel by viewModel()
    override fun setupViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.root?.let {
            setContentView(it)
        }
        binding?.btnTest?.setOnClickListener {

        }
    }

    override fun intents(): Flow<UserIntent> =
        binding?.run {
            merge(
                flowOf(UserIntent.InitialIntent),
                btnTest.clicks().map { UserIntent.GetUser(users.firstOrNull()?.uid ?: "") }
            )
        } ?: kotlin.run { merge() }

    override fun render(state: UserState) {
        showUsersList(_state = state)
        showUser(_state = state)
    }

    private fun showUsersList(_state: UserState) {
        if (_state.users.isNotEmpty()) {
            users.addAll(_state.users)
            binding?.tvShowUser?.text = users.toString()
        }
    }

    private fun showUser(_state: UserState) {
        if (_state.user != null) {
            binding?.tvShowUser?.text = _state.user.toString()
        }
    }
}