package sajjad.shahbazi.domain.usecases

import sajjad.shahbazi.domain.models.ApiResult
import sajjad.shahbazi.domain.models.CompanyNewsRepoModel
import sajjad.shahbazi.domain.repositories.CompanyNewsRepository

class GetCompaniesNewsUseCase(private val companyNewsRepository: CompanyNewsRepository) {
    suspend operator fun invoke(
        _companyReq: String,
        _dateFrom: String,
        _dateTo: String
    ): ApiResult<CompanyNewsRepoModel> = companyNewsRepository.getCompanyNews(
        companyReq = _companyReq,
        dateFrom = _dateFrom,
        dateTo = _dateTo
    )
}