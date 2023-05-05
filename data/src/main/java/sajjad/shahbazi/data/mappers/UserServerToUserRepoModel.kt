package sajjad.shahbazi.data.mappers

import sajjad.shahbazi.common.Mapper
import sajjad.shahbazi.data.models.UserServerModel
import sajjad.shahbazi.domain.models.UserRepoModel

class UserServerToUserRepoModel : Mapper<UserServerModel, UserRepoModel> {
    override fun map(item: UserServerModel): UserRepoModel = UserRepoModel(
        uid = item.uid ?: "",
        firstName = item.firstName ?: "",
        lastName = item.lastName ?: "",
        age = item.age ?: -1
    )
}