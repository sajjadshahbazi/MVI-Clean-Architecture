package sajjad.shahbazi.data.mappers

import sajjad.shahbazi.common.Mapper
import sajjad.shahbazi.data.models.MessageServerModel
import sajjad.shahbazi.domain.models.MessageRepoModel

class MessageServerToMessageRepoModel : Mapper<MessageServerModel, MessageRepoModel> {
    override fun map(item: MessageServerModel): MessageRepoModel = MessageRepoModel(
        uid = item.uid ?: -1,
        text = item.text ?: "",
        data = item.data ?: "",
        sendByMe = item.sendByMe ?: false
    )
}