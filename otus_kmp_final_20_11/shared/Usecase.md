
###Base UseCase
```
abstract class BaseUseCase<in T, out R>(
    private val dispatcher: CoroutineDispatcher = ioDispatcher
) {

    abstract suspend fun execute(param: T): R

    suspend operator fun invoke(param: T): Result<R> = withContext(dispatcher) {
        runCatching { execute(param) }
    }
}

```

###NewsUsecase
```
class NewsUseCase (private val newsService: NewsService): BaseUseCase<Unit, NewsList?>() {
    override suspend fun execute(param: Unit): NewsList? {
       return try {
           newsService.loadNews()?.getOrNull()
       }catch (e: Exception) {
           throw e
       }
    }
}
```