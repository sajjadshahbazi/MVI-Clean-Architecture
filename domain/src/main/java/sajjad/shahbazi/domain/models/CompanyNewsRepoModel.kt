package sajjad.shahbazi.domain.models

data class CompanyNewsRepoModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleRepoModel>
)

data class SourceRepoModel(
    val id: String,
    val name: String
)

data class ArticleRepoModel(
    val source: SourceRepoModel?,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String? = null
)