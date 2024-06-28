package sajjad.shahbazi.domain.repositories

import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.models.ConversationRepoModel

interface ConversationRepository {
    suspend fun getMessages(): ApiResult<ConversationRepoModel>
    suspend fun loadMoreMessages(page : Int): ApiResult<ConversationRepoModel>
}