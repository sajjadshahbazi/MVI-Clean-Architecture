package sajjad.shahbazi.data.models

import com.google.gson.annotations.SerializedName

data class MessageServerModel (
    @SerializedName("Id")
    val uid: Int?=null,
    @SerializedName("Text")
    val text: String?=null,
    @SerializedName("Date")
    val data: String?=null,
    @SerializedName("SendByMe")
    val sendByMe: Boolean?=null
)

data class ConversationServerModel (
    @SerializedName("messages")
    val messages: List<MessageServerModel>?=null,
    @SerializedName("page")
    val page: Int?=null,
    @SerializedName("conversation")
    val conversation: Long?=null
)