package sajjad.shahbazi.data.remote

import sajjad.shahbazi.common.Mapper
import sajjad.shahbazi.data.models.RetrofitResult
import sajjad.shahbazi.domain.models.ApiResult


class MapRemoteApiServiceToApiResultModel<T, V>(private val mapper: Mapper<T, V>) :
    Mapper<RetrofitResult<T>, ApiResult<V>> {
    override fun map(item: RetrofitResult<T>): ApiResult<V> {
        return when (item) {
            is RetrofitResult.Success -> {
                ApiResult.Success(
                    data = mapper.map(item.data),
                    statusCode = item.statusCode
                )
            }
            is RetrofitResult.Error -> {
                ApiResult.Error(
                    e = (item.extra?:Exception()) as Exception,
                    statusCode = item.statusCode
                )
            }
        }
    }
}