package sajjad.shahbazi.data.repository

import sajjad.shahbazi.common.Mapper
import sajjad.shahbazi.data.models.CompanyNewsServerModel
import sajjad.shahbazi.data.remote.CompanyNewsRemoteApi
import sajjad.shahbazi.data.remote.MapRemoteApiServiceToApiResultModel
import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.models.CompanyNewsRepoModel
import sajjad.shahbazi.domain.repositories.CompanyNewsRepository

class CompanyNewsRepositoryImpl(
    private val companyNewsRemoteApi: CompanyNewsRemoteApi,
    private val companyNewsMapper: Mapper<CompanyNewsServerModel, CompanyNewsRepoModel>
) : CompanyNewsRepository {
    override suspend fun getCompanyNews(
        companyReq: String,
        dateFrom: String,
        dateTo: String,
        pageNews: Int
    ): ApiResult<CompanyNewsRepoModel> =
        MapRemoteApiServiceToApiResultModel(companyNewsMapper).map(
            companyNewsRemoteApi.getCompanyNews(
                company = companyReq,
                from = dateFrom,
                to = dateTo,
                page = pageNews
            )
        )
}