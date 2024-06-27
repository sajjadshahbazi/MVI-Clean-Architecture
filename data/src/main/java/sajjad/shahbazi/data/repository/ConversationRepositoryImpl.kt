package sajjad.shahbazi.data.repository

import sajjad.shahbazi.common.Mapper
import sajjad.shahbazi.data.models.ConversationServerModel
import sajjad.shahbazi.data.remote.ConversationRemoteApi
import sajjad.shahbazi.data.remote.MapRemoteApiServiceToApiResultModel
import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.models.ConversationRepoModel
import sajjad.shahbazi.domain.repositories.ConversationRepository

class ConversationRepositoryImpl(
    private val conversationRemoteApi: ConversationRemoteApi,
    private val conversationMapper: Mapper<ConversationServerModel, ConversationRepoModel>
) : ConversationRepository {

    override suspend fun getMessages(): ApiResult<ConversationRepoModel> =
        MapRemoteApiServiceToApiResultModel(conversationMapper).map(conversationRemoteApi.getMessages())
}
