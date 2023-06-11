package sajjad.shahbazi.featureuser.archmodel

import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.mvibase.MviResult
import sajjad.shahbazi.domain.models.UserRepoModel

sealed class UserResult : MviResult{
    data class UserRes(
        val users : UserRepoModel
    ) : UserResult()
    data class UsersListRes(
        val users : List<UserRepoModel>
    ) : UserResult()
    data class Error(val err: ErrorHolder) : UserResult()
    data class Loading(val user : UserRepoModel) : UserResult()
}