package sajjad.shahbazi.companyinfo.archmodel

import sajjad.shahbazi.common.mvibase.MviAction

sealed class NewsAction : MviAction {
    data class FetchNextCompanyNews(
        val companyName: String,
        val page: Int
    ) : NewsAction()
}