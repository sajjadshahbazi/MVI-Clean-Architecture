package sajjad.shahbazi.data.repository

import sajjad.shahbazi.common.Mapper
import sajjad.shahbazi.data.mappers.UsersServerToUsersRepoModel
import sajjad.shahbazi.data.models.UserServerModel
import sajjad.shahbazi.data.remote.MapRemoteApiServiceToApiResultModel
import sajjad.shahbazi.data.remote.UserRemoteApi
import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.models.UserRepoModel
import sajjad.shahbazi.domain.repositories.UserRepository

class UserRepositoryImpl(
    private val userRemoteApi: UserRemoteApi,
    private val userMapper: Mapper<UserServerModel, UserRepoModel>
) : UserRepository {

    override suspend fun getUsers(): ApiResult<List<UserRepoModel>> {
        val listMapper = object : Mapper<List<UserServerModel>, List<UserRepoModel>> {
            override fun map(item: List<UserServerModel>): List<UserRepoModel> =
                item.map { userMapper.map(it) }
        }
        return MapRemoteApiServiceToApiResultModel(listMapper).map(userRemoteApi.getUsers())
    }


    override suspend fun getUser(): ApiResult<UserRepoModel> =
        MapRemoteApiServiceToApiResultModel(userMapper).map(userRemoteApi.getUser())
}