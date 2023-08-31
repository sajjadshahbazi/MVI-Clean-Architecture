package sajjad.shahbazi.featureuser.archmodel

import sajjad.shahbazi.common.mvibase.MviIntent

sealed class UserIntent : MviIntent{
    object InitialIntent : UserIntent()
    data class GetUser(val uid : String) : UserIntent()
}