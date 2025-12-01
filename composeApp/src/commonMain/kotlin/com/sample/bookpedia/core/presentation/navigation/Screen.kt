package com.sample.bookpedia.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable data object BookGraph: Screen
    @Serializable data object BookList : Screen
    @Serializable data class BookDetail(val id:String):Screen
}