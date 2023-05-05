package sajjad.shahbazi.data.repository

import sajjad.shahbazi.common.Mapper
import sajjad.shahbazi.data.models.UserServerModel
import sajjad.shahbazi.data.remote.MapRemoteApiServiceToApiResultModel
import sajjad.shahbazi.data.remote.UserRemoteApi
import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.models.UserRepoModel
import sajjad.shahbazi.domain.repositories.UserRepository

class UserRepositoryImpl(
    private val userRemoteApi: UserRemoteApi,
    private val userMapper: Mapper<UserServerModel, UserRepoModel>,
    private val usersMapper: Mapper<List<UserServerModel>, List<UserRepoModel>>
) : UserRepository {

    override suspend fun getUsers(): ApiResult<List<UserRepoModel>> =
        MapRemoteApiServiceToApiResultModel(usersMapper).map(userRemoteApi.getUsers())


    override suspend fun getUser(): ApiResult<UserRepoModel> =
        MapRemoteApiServiceToApiResultModel(userMapper).map(userRemoteApi.getUser())
}