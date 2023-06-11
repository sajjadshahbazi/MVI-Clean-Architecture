package sajjad.shahbazi.featureuser

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.transform
import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.ext.flowFromSuspend
import sajjad.shahbazi.common.mvibase.MviProcessor
import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.repositories.UserRepository
import sajjad.shahbazi.featureuser.archmodel.UserAction
import sajjad.shahbazi.featureuser.archmodel.UserResult

class UserProcessor(
    private val userRepository: UserRepository
) : MviProcessor<UserAction, UserResult> {

    private fun getUser(actions: Flow<UserAction>): Flow<UserResult> =
        actions.transform<UserAction, UserResult> {
            flowFromSuspend { userRepository.getUser() }.map { result ->
                when (result) {
                    is ApiResult.Success -> {
                        result.data?.let {
                            UserResult.UserRes(it)
                        } ?: kotlin.run {
                            ErrorHolder.Message("error unknown")
                        }
                    }

                    is ApiResult.Error -> {
                        ErrorHolder.Message(result.e.message ?: "")
                    }
                }
            }
        }

    private fun getUsersList(actions: Flow<UserAction>): Flow<UserResult> =
        actions.transform<UserAction, UserResult> {
            flowFromSuspend { userRepository.getUsers() }.map { result ->
                when (result) {
                    is ApiResult.Success -> {
                        result.data?.let {
                            UserResult.UsersListRes(it)
                        } ?: kotlin.run {
                            ErrorHolder.Message("error unknown")
                        }
                    }

                    is ApiResult.Error -> {
                        ErrorHolder.Message(result.e.message ?: "")
                    }
                }
            }
        }

    override fun actionProcessor(actions: Flow<UserAction>): Flow<UserResult> =
        actions.transform<UserAction, UserResult> {
            merge(
                getUser(actions),
                getUsersList(actions)
            )
        }
}