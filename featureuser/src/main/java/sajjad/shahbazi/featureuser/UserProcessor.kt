package sajjad.shahbazi.featureuser

import kotlinx.coroutines.flow.*
import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.mvibase.MviProcessor
import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.repositories.UserRepository
import sajjad.shahbazi.featureuser.archmodel.UserAction
import sajjad.shahbazi.featureuser.archmodel.UserResult

class UserProcessor(
    private val userRepository: UserRepository
) : MviProcessor<UserAction, UserResult> {

    private fun getUser(actions: Flow<UserAction.GetUser>): Flow<UserResult> =
        actions.transform<UserAction, UserResult> {
            when (val result = userRepository.getUser()) {
                is ApiResult.Success -> {
                    result.data?.let {
                        emit(UserResult.UserRes(it))
                    } ?: kotlin.run {
                        emit(UserResult.Error(ErrorHolder.Message("error unknown")))
                    }
                }
                is ApiResult.Error -> {
                    emit(UserResult.Error(ErrorHolder.Message(result.e.message ?: "")))
                }
            }
        }

    private fun userList(actions: Flow<UserAction.GetUsersList>): Flow<UserResult> =
        actions.transform<UserAction, UserResult> {
            when (val result = userRepository.getUsers()) {
                is ApiResult.Success -> {
                    result.data?.let {
                        emit(UserResult.UsersListRes(it))
                    } ?: kotlin.run {
                        emit(UserResult.Error(ErrorHolder.Message("error unknown")))
                    }
                }
                is ApiResult.Error -> {
                    emit(UserResult.Error(ErrorHolder.Message(result.e.message ?: "")))
                }
            }
        }

    override fun actionProcessor(actions: Flow<UserAction>): Flow<UserResult> =
        merge(
            actions.filterIsInstance<UserAction.GetUser>().let(::getUser),
            actions.filterIsInstance<UserAction.GetUsersList>().let(::userList)
        )
}