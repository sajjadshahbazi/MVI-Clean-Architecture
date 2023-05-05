package sajjad.shahbazi.domain.usecases

import sajjad.shahbazi.domain.repositories.UserRepository

class GetUserUseCase (private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.getUser()
}