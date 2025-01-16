package sajjad.shahbazi.domain.usecases

import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.models.CompanyNewsRepoModel
import sajjad.shahbazi.domain.repositories.CompanyNewsRepository

class GetCompaniesNewsUseCase(private val companyNewsRepository: CompanyNewsRepository) {
    suspend operator fun invoke(
        companyReq: String,
        dateFrom: String,
        dateTo: String,
        pageNews: Int
    ): ApiResult<CompanyNewsRepoModel> = companyNewsRepository.getCompanyNews(
        companyReq = companyReq,
        dateFrom = dateFrom,
        dateTo = dateTo,
        pageNews = pageNews
    )
}