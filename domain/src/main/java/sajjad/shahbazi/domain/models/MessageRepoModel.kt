package sajjad.shahbazi.domain.models


data class MessageRepoModel (
    val uid: Int,
    val text: String,
    val data: String,
    val sendByMe: Boolean
)

data class ConversationRepoModel(
    val messages : List<MessageRepoModel>,
    val page : Int,
    val size : Int,
    val conversationId : Long
)