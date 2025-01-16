package sajjad.shahbazi.companyinfo

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import sajjad.shahbazi.common.base.BaseViewModel
import sajjad.shahbazi.common.mvibase.MviProcessor
import sajjad.shahbazi.companyinfo.archmodel.NewsAction
import sajjad.shahbazi.companyinfo.archmodel.NewsIntent
import sajjad.shahbazi.companyinfo.archmodel.NewsResult
import sajjad.shahbazi.companyinfo.archmodel.NewsState

class CompanyNewsViewModel(
    private val processor: MviProcessor<NewsAction, NewsResult>
) : BaseViewModel<
        NewsIntent,
        NewsState>() {

    val companyQueue = ArrayDeque(listOf("Microsoft", "Apple", "Google", "Tesla"))

    var companyName = ""
    var page = 1

    val viewIntents: MutableSharedFlow<NewsIntent> = MutableSharedFlow()
    private var sharedFlow: MutableSharedFlow<NewsIntent> = MutableSharedFlow<NewsIntent>()
    override val viewState: StateFlow<NewsState> = compose()
    val viewStates: MutableStateFlow<NewsState> = MutableStateFlow(NewsState.idle())

    private fun Flow<NewsIntent>.intentFilter(): Flow<NewsIntent> =
        merge(
            filterIsInstance<NewsIntent.InitialIntent>()
                .take(1),
            filterIsInstance<NewsIntent.GetNextCompanyNews>().takeWhile { companyQueue.isNotEmpty() }
        )


    private fun actionFromIntent(intent: NewsIntent): NewsAction {
        return when (intent) {
            is NewsIntent.InitialIntent -> {
                companyName = companyQueue.removeFirst()
                Log.d("Sajad", "fetch next companu === ${companyName}")
                NewsAction.FetchNextCompanyNews(
                    companyName = companyName,
                    page = page
                )
            }

            is NewsIntent.GetNextCompanyNews -> {
                companyName = companyQueue.removeFirst()
                Log.d("Sajad", "fetch next companu === ${companyName}")
                NewsAction.FetchNextCompanyNews(
                    companyName = companyName,
                    page = page
                )
            }
        }
    }

    override suspend fun processIntent(intent: NewsIntent) {
        sharedFlow.emit(intent)
    }

    private fun compose(): StateFlow<NewsState> {
        return sharedFlow.intentFilter()
            .map(::actionFromIntent)
            .let(processor::actionProcessor)
            .scan(NewsState.idle(), ::reducer)
            .distinctUntilChanged()
            .stateIn<NewsState>(viewModelScope, SharingStarted.Eagerly, NewsState.idle())
    }

    private fun reducer(previousState: NewsState, result: NewsResult): NewsState {
        return when (result) {
            is NewsResult.Loading -> {
                previousState.copy(
                    loading = true,
                    error = null
                )
            }

            is NewsResult.Error -> {
                previousState.copy(
                    error = result.error,
                    loading = false
                )
            }

            is NewsResult.NewsList -> {
                val updatedArticles = previousState.companyNewsRepoModel?.articles.orEmpty() +
                        result.companyNewsRepoModel.articles

                val updatedCompanyNewsRepoModel = result.companyNewsRepoModel.copy(
                    articles = updatedArticles
                )

                previousState.copy(
                    companyNewsRepoModel = updatedCompanyNewsRepoModel,
                    loading = false
                )
            }
        }
    }
}