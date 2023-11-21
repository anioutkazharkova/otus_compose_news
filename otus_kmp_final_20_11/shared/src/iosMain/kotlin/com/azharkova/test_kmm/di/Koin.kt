package com.azharkova.test_kmm.di

import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.getOriginalKotlinClass
import platform.Foundation.NSClassFromString
import kotlin.reflect.KClass

fun<T:Any> createType(clazz: String): KClass<*>? {
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