###Service
```
class NewsService(private val httpClient: NetworkClient) {

    suspend fun loadNews(): Result<NewsList> {
        return httpClient.request(URL)
    }

    companion object {
        val  URL = "https://newsapi.org/v2/everything?q=science&apiKey=${NetworkConfig.apiKey}"
    }
}
```

###Usecase
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