package com.sample.bookpedia

import androidx.compose.ui.window.ComposeUIViewController
import com.sample.bookpedia.core.di.initKoin
import com.sample.bookpedia.core.presentation.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }