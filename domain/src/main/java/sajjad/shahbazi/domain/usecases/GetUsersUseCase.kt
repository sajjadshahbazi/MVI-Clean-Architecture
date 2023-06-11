package sajjad.shahbazi.domain.usecases

import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.models.UserRepoModel
import sajjad.shahbazi.domain.repositories.UserRepository

class GetUsersUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): ApiResult<List<UserRepoModel>> = userRepository.getUsers()
}