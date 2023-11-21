package com.azharkova.test_kmm.di

import com.azharkova.test_kmm.network.NetworkClient
import com.azharkova.test_kmm.service.NewsService
import com.azharkova.test_kmm.usecase.NewsUseCase
import com.azharkova.test_kmm.viewmodel.NewsItemVM
import com.azharkova.test_kmm.viewmodel.NewsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module
import kotlin.reflect.KClass

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

object KoinDIFactory {
    val di by lazy {
        KoinDI().apply {
            start()
        }
    }
}

fun<T:Any> KoinDIFactory.resolve(clazz: KClass<*>):T? {
    return di.getKoin().get(clazz)
}