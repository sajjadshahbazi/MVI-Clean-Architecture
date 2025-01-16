package sajjad.shahbazi.companyinfo.archmodel

import sajjad.shahbazi.common.base.ErrorHolder
import sajjad.shahbazi.common.mvibase.MviState
import sajjad.shahbazi.domain.models.CompanyNewsRepoModel

data class NewsState(
    val companyNewsRepoModel: CompanyNewsRepoModel?,
    val loading: Boolean,
    val error: ErrorHolder?
) : MviState {
    companion object {
        fun idle(): NewsState {
            return NewsState(
                companyNewsRepoModel = null,
                loading = false,
                error = null,
            )
        }
    }
}