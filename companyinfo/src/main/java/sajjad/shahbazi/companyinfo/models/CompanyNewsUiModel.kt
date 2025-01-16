package sajjad.shahbazi.companyinfo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CompanyNewsUiModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleUiModel>
) : Parcelable

@Parcelize
data class ArticleUiModel(
    val source: SourceUiModel?,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String? = null
) : Parcelable



@Parcelize
data class SourceUiModel(
    val id: String,
    val name: String
) : Parcelable
