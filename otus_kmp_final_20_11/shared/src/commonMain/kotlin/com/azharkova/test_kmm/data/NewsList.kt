package com.azharkova.test_kmm.data

@kotlinx.serialization.Serializable
data class NewsList(
    var articles: List<NewsItem>? = null
)