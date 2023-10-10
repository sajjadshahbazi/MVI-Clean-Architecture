package sajjad.shahbazi.featureuser

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import sajjad.shahbazi.common.mvibase.MviProcessor
import sajjad.shahbazi.common.base.BaseViewModel
import sajjad.shahbazi.featureuser.archmodel.UserAction
import sajjad.shahbazi.featureuser.archmodel.UserIntent
import sajjad.shahbazi.featureuser.archmodel.UserResult
import sajjad.shahbazi.featureuser.archmodel.UserState

class UserViewModel(
    private val processor: MviProcessor<UserAction, UserResult>
) : BaseViewModel<
        UserIntent,
        UserState>() {

    private var sharedFlow: MutableSharedFlow<UserIntent> = MutableSharedFlow<UserIntent>()
    override val viewState: StateFlow<UserState> = compose()

    private fun intentFilter(userIntent: Flow<UserIntent>): Flow<UserIntent> {
        val sharedIntent = userIntent.shareIn(viewModelScope, SharingStarted.Eagerly, 1)
        return userIntent.transform<UserIntent, UserIntent> {
            sharedIntent.filter { it is UserIntent.InitialIntent }.take(1)
            sharedIntent.filter { it !is UserIntent.InitialIntent }
        }
    }

    private fun actionFromIntent(intent: UserIntent): UserAction {
        return when (intent) {
            is UserIntent.InitialIntent -> {
                UserAction.GetUsersList
            }

            is UserIntent.GetUser -> {
                UserAction.GetUser(intent.uid)
            }
        }
    }

    override suspend fun processIntent(intent: Flow<UserIntent>) {
        intent.collect(sharedFlow)
    }

    private fun compose(): StateFlow<UserState> {
        return sharedFlow.let(::intentFilter)
            .map(::actionFromIntent)
            .let(processor::actionProcessor)
            .scan(UserState.idle(), ::reducer)
            .distinctUntilChanged()
            .stateIn<UserState>(viewModelScope, SharingStarted.Eagerly, UserState.idle())
    }

    private fun reducer(previousState: UserState, result: UserResult): UserState {
        return when (result) {
            is UserResult.Loading -> {
                previousState.copy(
                    loading = true,
                    error = null,
                    user = null
                )
            }

            is UserResult.UserRes -> {
                previousState.copy(
                    user = result.user,
                    error = null,
                    loading = false
                )
            }

            is UserResult.Error -> {
                previousState.copy(
                    error = result.error,
                    loading = false
                )
            }

            is UserResult.UsersListRes -> {
                previousState.copy(
                    user = null,
                    users = result.users,
                    error = null,
                    loading = false
                )
            }
        }
    }
}