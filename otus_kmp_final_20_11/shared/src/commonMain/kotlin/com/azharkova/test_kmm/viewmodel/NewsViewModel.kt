package com.azharkova.test_kmm.viewmodel

import com.azharkova.test_kmm.data.NewsItem
import com.azharkova.test_kmm.data.NewsList
import com.azharkova.test_kmm.usecase.NewsUseCase
import com.azharkova.test_kmm.util.ioDispatcher
import com.azharkova.test_kmm.util.uiDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class ViewModel{
    val job = SupervisorJob()
    protected var scope: CoroutineScope = CoroutineScope(uiDispatcher + job)
}
class NewsViewModel(private val useCase: NewsUseCase) : ViewModel() {
    var newsFlow = MutableStateFlow<NewsList?>(null)

    fun loadNews() {
        scope.launch {
            val result = withContext(ioDispatcher) {
                useCase.invoke(Unit)
            }
            result.getOrNull()?.let {
                newsFlow.tryEmit(it)
            }
        }
    }
}


class NewsItemVM(item: NewsItem?): ViewModel() {
    private val _item: MutableStateFlow<NewsItem?> = MutableStateFlow<NewsItem?>(item)
    val model = _item.asStateFlow()
}