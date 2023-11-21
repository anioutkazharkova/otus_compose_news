package com.azharkova.test_kmm.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class NewsItem(
    @SerialName("author") val author: String?,
    val title: String?, val description: String?,
    val url: String?, val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)
