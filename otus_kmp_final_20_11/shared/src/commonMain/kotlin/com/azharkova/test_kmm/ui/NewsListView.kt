package com.azharkova.test_kmm.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import com.azharkova.test_kmm.data.NewsItem
import com.azharkova.test_kmm.data.NewsList
import com.azharkova.test_kmm.viewmodel.NewsItemVM
import com.azharkova.test_kmm.viewmodel.NewsViewModel
import com.seiko.imageloader.rememberImagePainter

@Composable
fun NewsListItemView(item: NewsItem, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp,8.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
       Image(painter = rememberImagePainter(item.urlToImage.orEmpty()),
             contentDescription = "image",
                   modifier = Modifier.width(120.dp).height(120.dp).padding(0.dp, 0.dp, 8.dp, 0.dp))
        Column {
            Text(text = item.title.orEmpty(), style = MaterialTheme.typography.h6)
            Text(text = item.content.orEmpty(), style = MaterialTheme.typography.subtitle1, maxLines = 3)
            Text(text = item.publishedAt.orEmpty(), style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun NewsListView(items: List<NewsItem>,  onClick: (NewsItem)->Unit) {
    LazyColumn(
        contentPadding = // 1.
        PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items) {
            NewsListItemView(it,  modifier = Modifier.clickable {
                onClick.invoke(it)
            })
        }
    }

}

@Composable
fun NewsListScreen(viewModel: NewsViewModel, onClick: (NewsItem)->Unit) {
    LaunchedEffect(Unit) {
        viewModel.loadNews()
    }
   val newsList: NewsList? by viewModel.newsFlow.collectAsState()
    NewsListView(newsList?.articles.orEmpty(), onClick)
}

@Composable
fun NewsItemDataView(viewModel: NewsItemVM) {
    val data by viewModel.model.collectAsState()
    NewsItemView(
        image = rememberImagePainter(data?.urlToImage.orEmpty()),
        title = data?.title.orEmpty(),
        text = data?.content.orEmpty()
    )
}

@Composable
fun NewsItemView(
    modifier: Modifier = Modifier,
    image: Painter,
    title: String = "",
    text: String = ""
) {
    Column(modifier = modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {
        Image(painter = image,
              contentDescription = "image",
              modifier = Modifier.height(200.dp))
        Text(title,style = TextStyle.Default)
        Text(text)
    }
}
