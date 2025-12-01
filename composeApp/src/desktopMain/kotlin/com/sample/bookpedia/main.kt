package com.sample.bookpedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sample.bookpedia.core.di.initKoin
import com.sample.bookpedia.core.presentation.App

fun main(){
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KMP-Bookpedia",
        ) {
            App()
        }

    }
}