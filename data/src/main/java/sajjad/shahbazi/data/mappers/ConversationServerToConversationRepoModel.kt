package sajjad.shahbazi.data.mappers

import sajjad.shahbazi.common.Mapper
import sajjad.shahbazi.data.models.ConversationServerModel
import sajjad.shahbazi.data.models.MessageServerModel
import sajjad.shahbazi.domain.models.ConversationRepoModel
import sajjad.shahbazi.domain.models.MessageRepoModel


class ConversationServerToConversationRepoModel :
    Mapper<ConversationServerModel, ConversationRepoModel> {


    val messageMapper = object : Mapper<MessageServerModel, MessageRepoModel> {
        override fun map(item: MessageServerModel): MessageRepoModel = MessageRepoModel(
            uid = item.uid ?: -1,
            text = item.text ?: "",
            data = item.data ?: "",
            sendByMe = item.sendByMe ?: false
        )
    }

    override fun map(item: ConversationServerModel): ConversationRepoModel {
        return ConversationRepoModel(
            messages = item.messages?.map { messageMapper.map(it) } ?: emptyList(),
            page = item.page ?: 1,
            size = item.size ?: 0,
            conversationId = item.conversation ?: 1
        )
    }
}