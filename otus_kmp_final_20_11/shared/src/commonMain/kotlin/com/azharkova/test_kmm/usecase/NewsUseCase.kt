package com.azharkova.test_kmm.usecase

import com.azharkova.test_kmm.data.NewsList
import com.azharkova.test_kmm.service.NewsService

class NewsUseCase (private val newsService: NewsService): BaseUseCase<Unit, NewsList?>() {
    override suspend fun execute(param: Unit): NewsList? {
        return try {
            newsService.loadNews()?.getOrNull()
        }catch (e: Exception) {
            throw e
        }
    }
}