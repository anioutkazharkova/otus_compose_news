### Setup koin
```
implementation( "io.insert-koin:koin-core:3.2.0")
implementation( "io.insert-koin:koin-android:3.2.0")
```

###DI 
```

class KoinDI : KoinComponent {
    val serviceModule = module {
        single { NetworkClient() }
        single { NewsService(get()) }
    }

    val usecaseModule = module {
        factory {
            NewsUseCase(get())
        }
    }

    val vmModule = module {
        factory<NewsViewModel> { NewsViewModel(get()) }
    }

    fun start() = startKoin {
        modules(listOf(serviceModule, usecaseModule, vmModule))
    }
}
```

### Koin Component
```
class KoinDI : KoinComponent {
   val serviceModule = module {
       single { NetworkClient() }
       single { NewsService(get()) }
   }

    val presenterModule = module {
        factory<NewsPresenter> { NewsPresenter(get()) }
    }

    fun start() = startKoin {
        modules(listOf(serviceModule, presenterModule))
    }
}
```

### Koin Factory 1
```
object KoinDIFactory {
    val di by lazy {
        KoinDI().apply {
            start()
        }
    }
}
```

### Koin Factory 2
``` 
fun<T:Any> KoinDIFactory.resolve(clazz: KClass<*>):T? {
        return di.getKoin().get(clazz)
    }
```

###  ObjcClass
```

fun<T:Any> createType(clazz: String):KClass<*>? {
    val objCClass = NSClassFromString(clazz)
    if (objCClass != null) {
        return getOriginalKotlinClass(objCClass)
    }
    return null // no type found
}

fun<T:Any> KoinDIFactory.createType(clazz: ObjCClass):KClass<*>? {
    return getOriginalKotlinClass(clazz)
}

fun<T:Any> KoinDIFactory.resolve(clazz: ObjCClass):T? {
    val kClass = createType<T>(clazz)
    if (kClass != null) {
        return di.getKoin().get(kClass)
    }
    return null
}

```

### Config
```
object VMFactory {
    fun createVM(type: BaseView): ViewModel? {
        return when (type) {
            is NewsView -> KoinDIFactory.resolve(NewsViewModel::class)
            else -> null
        }
        return null
    }
}

```