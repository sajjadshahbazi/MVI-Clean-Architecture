package sajjad.shahbazi.companyinfo.archmodel

import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.mvibase.MviResult
import sajjad.shahbazi.companyinfo.models.CompanyNewsUiModel

sealed class NewsResult : MviResult{
    data class NewsList(
        val companyNewsUiModel : CompanyNewsUiModel
    ) : NewsResult()
    data class Error(val error: ErrorHolder) : NewsResult()
    data object Loading : NewsResult()
}