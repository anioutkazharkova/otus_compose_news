package com.azharkova.test_kmm.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Coder {
    val map = hashMapOf<String,String>()

    inline fun<reified T> save(key: String, data: T) {
        map[key] = encode(data)
    }

    inline fun<reified T> get(key: String):T? {
        return  decode(map[key].orEmpty())
    }

    inline fun<reified T> encode(data: T):String {
        return Json.encodeToString(data)
    }

    inline fun<reified T> decode(json: String):T? {
        return try {
            Json.decodeFromString(json)
        }catch (e: Exception) {
            null
        }
    }
}