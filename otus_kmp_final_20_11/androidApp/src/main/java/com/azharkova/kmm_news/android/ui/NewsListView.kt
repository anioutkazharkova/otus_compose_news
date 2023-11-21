package com.azharkova.kmm_news.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.azharkova.test_kmm.data.NewsItem
import com.azharkova.test_kmm.data.NewsList
//import com.azharkova.test_kmm.data.viewmodel.NewsViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsListItemView(item: NewsItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp,8.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        GlideImage(model = item.urlToImage?.orEmpty(), contentDescription = "NewItem",
            modifier = Modifier.width(120.dp).height(120.dp).padding(0.dp,0.dp,8.dp, 0.dp))
        Column {
            Text(text = item.title.orEmpty(), style = MaterialTheme.typography.h6)
            Text(text = item.content.orEmpty(), style = MaterialTheme.typography.subtitle1, maxLines = 3)
            Text(text = item.publishedAt.orEmpty(), style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun NewsListView(items: List<NewsItem>) {
    LazyColumn(
        contentPadding = // 1.
        PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items) {
            NewsListItemView(it)
        }
    }

}
