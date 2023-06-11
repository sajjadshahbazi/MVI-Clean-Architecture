package sajjad.shahbazi.featureuser.archmodel

import sajjad.shahbazi.common.mvibase.MviAction

sealed class UserAction : MviAction{
    data class GetUser(val uid : String): UserAction()
    object GetUsersList: UserAction()
}