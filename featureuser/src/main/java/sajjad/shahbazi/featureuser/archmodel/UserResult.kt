package sajjad.shahbazi.featureuser.archmodel

import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.mvibase.MviResult
import sajjad.shahbazi.domain.models.UserRepoModel

sealed class UserResult : MviResult{
    data class UserRes(
        val user : UserRepoModel
    ) : UserResult()
    data class UsersListRes(
        val users : List<UserRepoModel>
    ) : UserResult()
    data class Error(val error: ErrorHolder) : UserResult()
    object Loading : UserResult()
}