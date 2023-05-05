package sajjad.shahbazi.data.mappers

import sajjad.shahbazi.common.Mapper
import sajjad.shahbazi.data.models.UserServerModel
import sajjad.shahbazi.domain.models.UserRepoModel

class UsersServerToUsersRepoModel(private val mapper: Mapper<UserServerModel, UserRepoModel>) :
    Mapper<List<UserServerModel>, List<UserRepoModel>> {
    override fun map(item: List<UserServerModel>): List<UserRepoModel> = item.map { mapper.map(it) }
}