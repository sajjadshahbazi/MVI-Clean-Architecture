package sajjad.shahbazi.companyinfo.ui.theme

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import sajjad.shahbazi.common.Navigation
import sajjad.shahbazi.common.ext.readableFormatDate
import sajjad.shahbazi.companyinfo.CompanyNewsViewModel
import sajjad.shahbazi.companyinfo.archmodel.NewsIntent
import sajjad.shahbazi.companyinfo.models.ArticleUiModel

@Composable
fun CompanyNewsListScreen(viewModel: CompanyNewsViewModel, navController: NavController) {
    val states by viewModel.viewStates.collectAsState()
    val listState = rememberLazyListState()
    val context = LocalContext.current

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex == states.companyNewsUiModel?.articles?.lastIndex && !states.loading) {
                    viewModel.viewIntents.emit(NewsIntent.GetNextCompanyNews)
                    Toast.makeText(context, "Loading more news...", Toast.LENGTH_SHORT).show()
                }
            }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        states.companyNewsUiModel?.articles?.let { articles ->
            items(articles.size) { index ->
                val article = articles[index]
                val queryName = viewModel.companyName
                NewsItem(
                    article = article,
                    queryName = queryName,
                    onClick = {
                        navController.navigate("${Navigation.Routes.NewsDetails}/$index")
                    }
                )
            }
        }

        if (states.loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun NewsItem(
    article: ArticleUiModel,
    queryName: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {

            FrescoImage(
                imageUrl = article.urlToImage ?: "",
                placeholder = android.R.drawable.gallery_thumb,
                errorImage = android.R.drawable.stat_notify_error,
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = article.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Query: $queryName",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = article.publishedAt.readableFormatDate(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
