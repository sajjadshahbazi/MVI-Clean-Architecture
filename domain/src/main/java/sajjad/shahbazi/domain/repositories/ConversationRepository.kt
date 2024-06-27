package sajjad.shahbazi.domain.repositories

import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.models.ConversationRepoModel
import sajjad.shahbazi.domain.models.MessageRepoModel

interface ConversationRepository {
    suspend fun getMessages(): ApiResult<ConversationRepoModel>
}