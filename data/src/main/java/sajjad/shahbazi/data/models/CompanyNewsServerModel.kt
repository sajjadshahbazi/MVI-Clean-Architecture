package sajjad.shahbazi.data.models

import com.google.gson.annotations.SerializedName

data class CompanyNewsServerModel(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("totalResults")
    val totalResults: Int? = -1,
    @SerializedName("articles")
    val articles: List<ArticleServerModel>? = emptyList()
)

data class SourceServerModel(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null
)

data class ArticleServerModel(
    @SerializedName("source")
    val source: SourceServerModel? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @SerializedName("content")
    val content: String? = null
)
