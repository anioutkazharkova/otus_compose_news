package com.azharkova.test_kmm.service

import com.azharkova.test_kmm.data.NewsList
import com.azharkova.test_kmm.network.NetworkClient
import com.azharkova.test_kmm.network.NetworkConfig

class NewsService(private val httpClient: NetworkClient) {

    suspend fun loadNews(): Result<NewsList> {
        return httpClient.request(URL)
    }

    companion object {
        val  URL = "https://newsapi.org/v2/everything?q=science&apiKey=${NetworkConfig.apiKey}"
    }
}