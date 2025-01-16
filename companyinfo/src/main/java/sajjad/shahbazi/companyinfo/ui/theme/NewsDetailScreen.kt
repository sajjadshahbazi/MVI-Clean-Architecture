package sajjad.shahbazi.companyinfo.ui.theme


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.view.SimpleDraweeView
import sajjad.shahbazi.common.ext.readableFormatDate
import sajjad.shahbazi.companyinfo.CompanyNewsViewModel

@Composable
fun NewsDetailsScreen(viewModel: CompanyNewsViewModel, index: Int) {
    val state = viewModel.viewStates.collectAsState().value
    val article = state.companyNewsUiModel?.articles?.getOrNull(index)

    if (article == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Article not found.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            FrescoImage(
                imageUrl = article.urlToImage ?: "",
                placeholder = android.R.drawable.gallery_thumb,
                errorImage = android.R.drawable.stat_notify_error,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "By ${article.author ?: "Unknown"} | ${article.publishedAt.readableFormatDate()}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = article.content ?: "No content available.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Source: ${article.source?.name ?: "Unknown"}",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun FrescoImage(
    imageUrl: String,
    placeholder: Int,
    errorImage: Int,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    AndroidView(
        factory = { context ->
            SimpleDraweeView(context).apply {
                hierarchy = GenericDraweeHierarchyBuilder(context.resources)
                    .setPlaceholderImage(placeholder)
                    .setFailureImage(errorImage)
                    .setActualImageScaleType(
                        when (contentScale) {
                            ContentScale.Crop -> ScalingUtils.ScaleType.CENTER_CROP
                            ContentScale.Fit -> ScalingUtils.ScaleType.FIT_CENTER
                            ContentScale.FillBounds -> ScalingUtils.ScaleType.FIT_XY
                            ContentScale.Inside -> ScalingUtils.ScaleType.CENTER_INSIDE
                            else -> ScalingUtils.ScaleType.CENTER
                        }
                    )
                    .build()
            }
        },
        modifier = modifier,
        update = { view ->
            view.setImageURI(imageUrl)
        }
    )
}
