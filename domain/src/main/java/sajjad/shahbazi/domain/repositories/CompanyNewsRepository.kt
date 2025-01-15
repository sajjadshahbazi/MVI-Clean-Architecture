package sajjad.shahbazi.domain.repositories

import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.models.CompanyNewsRepoModel

interface CompanyNewsRepository {
    suspend fun getCompanyNews(
        companyReq : String,
        dateFrom : String,
        dateTo : String
    ): ApiResult<CompanyNewsRepoModel>
}