package sajjad.shahbazi.data.mappers

import sajjad.shahbazi.common.Mapper
import sajjad.shahbazi.data.models.ArticleServerModel
import sajjad.shahbazi.data.models.CompanyNewsServerModel
import sajjad.shahbazi.data.models.SourceServerModel
import sajjad.shahbazi.domain.models.ArticleRepoModel
import sajjad.shahbazi.domain.models.CompanyNewsRepoModel
import sajjad.shahbazi.domain.models.SourceRepoModel

class CompanyNewsServerModelToRepoModel : Mapper<CompanyNewsServerModel, CompanyNewsRepoModel> {

    private val sourceMapper = object : Mapper<SourceServerModel?, SourceRepoModel> {
        override fun map(item: SourceServerModel?): SourceRepoModel {
            return SourceRepoModel(
                id = item?.id.orEmpty(),
                name = item?.name.orEmpty()
            )
        }
    }

    private val articleMapper = object : Mapper<ArticleServerModel, ArticleRepoModel> {
        override fun map(item: ArticleServerModel): ArticleRepoModel {
            return ArticleRepoModel(
                source = sourceMapper.map(item.source),
                author = item.author.orEmpty(),
                title = item.title ?: "",
                description = item.description.orEmpty(),
                url = item.url ?: "",
                urlToImage = item.urlToImage.orEmpty(),
                publishedAt = item.publishedAt ?: "2025-01-09T14:06:02Z",
                content = item.content
            )
        }
    }

    override fun map(item: CompanyNewsServerModel): CompanyNewsRepoModel {
        return CompanyNewsRepoModel(
            status = item.status ?: "",
            totalResults = item.totalResults ?: -1,
            articles = item.articles?.map { articleMapper.map(it) } ?: emptyList()
        )
    }
}