package sajjad.shahbazi.domain.repositories

import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.models.UserRepoModel

interface UserRepository {
    suspend fun getUsers(): ApiResult<List<UserRepoModel>>
    suspend fun getUser(): ApiResult<UserRepoModel>
}