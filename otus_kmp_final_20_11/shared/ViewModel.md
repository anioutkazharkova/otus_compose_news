###BaseVM
```
open class ViewModel{
    val job = SupervisorJob()
    protected var scope: CoroutineScope = CoroutineScope(uiDispatcher + job)
}
```

###NewsViewModel
```
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
```