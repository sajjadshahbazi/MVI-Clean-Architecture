package sajjad.shahbazi.companyinfo.archmodel

import sajjad.shahbazi.common.mvibase.MviIntent

sealed class NewsIntent : MviIntent{
    data object InitialIntent : NewsIntent()
    data object GetNextCompanyNews : NewsIntent()
}