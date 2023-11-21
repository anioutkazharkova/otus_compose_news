###Config
```
class NetworkConfig {
companion object shared{
val apiUrl = "newsapi.org"
val apiKey = "5b86b7593caa4f009fea285cc74129e2"

        val header: HashMap<String, String> =  hashMapOf("X-Api-Key" to apiKey)
    }
}
```

###Client
```
expect fun createHttpClient():HttpClient

class NetworkClient {
    val httpClient = createHttpClient()

    suspend inline fun<reified T> request(path: String): Result<T> {
        return try {
            val data = httpClient.get(path)
            print(data)
            val result = data.body<T>()
            Result.success(result)
        }catch (e: Exception) {
            Result.failure(e)
        }

    }
}
```
###iOS
```
actual fun createHttpClient(): HttpClient {
    return iOSClient()
}

fun iOSClient() = HttpClient(Darwin.create()) {
    install(ContentNegotiation) {
        json(Json{
            prettyPrint = true
            ignoreUnknownKeys = true
            explicitNulls = true
        })
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}
```

###Android
```
actual fun createHttpClient(): HttpClient {
    return AndroidClient()
}

fun AndroidClient() = HttpClient(OkHttp) {
    install(ContentNegotiation) {
        json(Json{
            prettyPrint = true
            ignoreUnknownKeys = true
            explicitNulls = true
        })
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}
```