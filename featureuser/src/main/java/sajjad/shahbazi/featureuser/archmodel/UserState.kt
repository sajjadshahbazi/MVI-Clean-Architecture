package sajjad.shahbazi.featureuser.archmodel

import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.mvibase.MviState
import sajjad.shahbazi.domain.models.UserRepoModel

data class UserState(
    val user: UserRepoModel?,
    val users: List<UserRepoModel>,
    val loading: Boolean,
    val error: ErrorHolder?
) : MviState {
    companion object {
        fun idle(): UserState {
            return UserState(
                user = null,
                users = emptyList(),
                loading = false,
                error = null,
            )
        }
    }
}