package com.sample.bookpedia.core.presentation.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.sample.bookpedia.feat_book.presentation.book_details.BookDetailsAction
import com.sample.bookpedia.feat_book.presentation.book_details.BookDetailsScreenRoot
import com.sample.bookpedia.feat_book.presentation.book_details.BookDetailsViewModel
import com.sample.bookpedia.feat_book.presentation.book_graph.SharedBookViewModel
import com.sample.bookpedia.feat_book.presentation.book_list.BookListScreenRoot
import com.sample.bookpedia.feat_book.presentation.book_list.BookListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavGraph(
    navController : NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = Screen.BookGraph,
    ) {
        navigation<Screen.BookGraph>(
            startDestination = Screen.BookList
        ) {
            composable<Screen.BookList>(
                exitTransition = { slideOutHorizontally{initialOffset -> initialOffset}},
                popEnterTransition = {slideInHorizontally()}
            ) {
                val viewModel = koinViewModel<BookListViewModel>()
                val sharedViewModel = it.sharedParentViewModel<SharedBookViewModel>(navController)
                LaunchedEffect(true){
                    sharedViewModel.onSelectBook(null)
                }
                BookListScreenRoot(
                    viewModel = viewModel,
                    onBookClicked = {book->
                        sharedViewModel.onSelectBook(book)
                        navController.navigate(Screen.BookDetail(book.id))
                    }
                )
            }
            composable<Screen.BookDetail>(
                enterTransition = {slideInHorizontally{initialOffset -> initialOffset}},
                exitTransition = { slideOutHorizontally{initialOffset -> initialOffset}}
            ){entry->
                val sharedViewModel = entry.sharedParentViewModel<SharedBookViewModel>(navController)
                val viewModel = koinViewModel<BookDetailsViewModel>()
                val selectedBook by sharedViewModel.selectedBook.collectAsStateWithLifecycle()

                LaunchedEffect(selectedBook){
                    selectedBook?.let{book->
                        viewModel.onAction(BookDetailsAction.OnSelectedBookChange(book))
                    }
                }
                BookDetailsScreenRoot(
                    viewModel = viewModel,
                    onBackClicked ={ navController.navigateUp()},

                )
            }
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedParentViewModel(
    navController: NavController
):T{
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this){
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel<T>(
        viewModelStoreOwner = parentEntry
    )
}