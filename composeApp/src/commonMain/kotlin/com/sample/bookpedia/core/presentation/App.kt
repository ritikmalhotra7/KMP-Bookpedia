package com.sample.bookpedia.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.sample.bookpedia.core.presentation.navigation.AppNavGraph

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        AppNavGraph(navController)
    }
}