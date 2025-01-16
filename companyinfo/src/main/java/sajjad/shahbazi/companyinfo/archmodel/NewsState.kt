package sajjad.shahbazi.companyinfo.archmodel

import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.mvibase.MviState
import sajjad.shahbazi.companyinfo.models.CompanyNewsUiModel

data class NewsState(
    val companyNewsUiModel: CompanyNewsUiModel?,
    val loading: Boolean,
    val error: ErrorHolder?
) : MviState {
    companion object {
        fun idle(): NewsState {
            return NewsState(
                companyNewsUiModel = null,
                loading = false,
                error = null,
            )
        }
    }
}