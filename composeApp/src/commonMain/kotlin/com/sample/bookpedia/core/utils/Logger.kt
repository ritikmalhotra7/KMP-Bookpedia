package com.sample.bookpedia.core.utils

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

@OptIn(InternalSerializationApi::class)
fun <T : Any> T?.printWith(with:String = ""){
    val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    val tag = this!!::class.simpleName ?: "UnknownClass"

    return try {
        @Suppress("UNCHECKED_CAST")
        val serializer = (this::class as KClass<T>).serializer()
        val jsonString = json.encodeToString(serializer, this)

        println("Taget: $tag $with: $jsonString")
    } catch (e: Exception) {
        e.printStackTrace()
        println("Taget: with catch $tag $with: ${this.toString()}")
    }
}