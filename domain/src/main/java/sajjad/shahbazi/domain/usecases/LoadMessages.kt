package sajjad.shahbazi.domain.usecases

import sajjad.shahbazi.domain.repositories.ConversationRepository

class LoadMessages (private val conversationRepository: ConversationRepository) {
    suspend operator fun invoke() = conversationRepository.getMessages()
}