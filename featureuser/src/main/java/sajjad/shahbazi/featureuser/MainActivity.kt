package sajjad.shahbazi.featureuser

import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import sajjad.shahbazi.common.base.BaseActivity
import sajjad.shahbazi.featureuser.archmodel.UserIntent
import sajjad.shahbazi.featureuser.archmodel.UserState

class MainActivity : BaseActivity<
        UserIntent,
        UserState,
        UserViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val viewModel: UserViewModel by viewModel()
    }

    override fun intents(): Flow<UserIntent> = merge(
        flow { emit(UserIntent.InitialIntent) }
    )

    override fun render(state: UserState) {
        showUsersList(_state = state)
    }

    private fun showUsersList(_state : UserState){
        if (_state.users.isNotEmpty()) {
            Toast.makeText(this@MainActivity, _state.users.first().firstName, Toast.LENGTH_SHORT).show()
        }
    }
}