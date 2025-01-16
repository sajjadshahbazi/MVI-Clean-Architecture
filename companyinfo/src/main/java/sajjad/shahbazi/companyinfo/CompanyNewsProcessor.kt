package sajjad.shahbazi.companyinfo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.ext.DaysWeek
import sajjad.shahbazi.common.ext.getDate
import sajjad.shahbazi.common.mvibase.MviProcessor
import sajjad.shahbazi.companyinfo.archmodel.NewsAction
import sajjad.shahbazi.companyinfo.archmodel.NewsResult
import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.usecases.GetCompaniesNewsUseCase


class CompanyNewsProcessor(
    private val getCompaniesNewsUseCase: GetCompaniesNewsUseCase
) : MviProcessor<NewsAction, NewsResult> {

    private fun fetchCompanyNews(actions: Flow<NewsAction.FetchNextCompanyNews>): Flow<NewsResult> =
        actions.transform<NewsAction.FetchNextCompanyNews, NewsResult> { action ->
            when (val result = getCompaniesNewsUseCase(
                companyReq = action.companyName,
                dateFrom = DaysWeek.Yesterday.getDate(),
                dateTo = DaysWeek.Today.getDate(),
                pageNews = action.page
            )) {
                is ApiResult.Success -> {
                    result.data?.let {
                        emit(NewsResult.NewsList(it))
                    } ?: kotlin.run {
                        emit(NewsResult.Error(ErrorHolder.Message("error unknown")))
                    }
                }

                is ApiResult.Error -> {
                    emit(NewsResult.Error(ErrorHolder.Message(result.e.message ?: "")))
                }
            }
        }

    override fun actionProcessor(actions: Flow<NewsAction>): Flow<NewsResult> =
        actions.filterIsInstance<NewsAction.FetchNextCompanyNews>().let(::fetchCompanyNews)
}