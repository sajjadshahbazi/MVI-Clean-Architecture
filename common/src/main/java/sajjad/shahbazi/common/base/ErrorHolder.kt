package sajjad.shahbazi.common.base

import androidx.annotation.StringRes
import sajjad.shahbazi.common.R

sealed class ErrorHolder {
    data class Message(val message: String) : ErrorHolder()
    data class StringRes(@androidx.annotation.StringRes val stringID: Int) : ErrorHolder()
    data class UnAuthorized(val errorMessage : String) : ErrorHolder()
}

//@StringRes
//fun ErrorHolder.getStringRes() = when (this) {
//    is ErrorHolder.StringRes -> stringID
//    else -> R.string.error_unknown
//}