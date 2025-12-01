package com.sample.bookpedia.feat_book.presentation.book_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sample.bookpedia.core.domain.onSuccess
import com.sample.bookpedia.core.presentation.navigation.Screen
import com.sample.bookpedia.feat_book.domain.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailsViewModel(
    private val bookRepository: BookRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val bookId = savedStateHandle.toRoute<Screen.BookDetail>().id

    private val mState = MutableStateFlow(BookDetailsState())
    val state = mState.onStart {
        fetchBookDescription()
        observeFavouriteStatus()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = mState.value
    )

    fun onAction(action: BookDetailsAction) {
        when (action) {
            BookDetailsAction.OnFavouriteClick -> {
                viewModelScope.launch {
                    if(state.value.isFavourite){
                        bookRepository.removeFromFavourite(bookId)
                    }else{
                        state.value.book?.let{book ->
                            bookRepository.addToFavourite(book)
                        }
                    }
                }
            }
            is BookDetailsAction.OnSelectedBookChange -> {
                mState.update {
                    it.copy(book = action.book)
                }
            }

            else -> Unit
        }
    }

    private fun observeFavouriteStatus(){
        bookRepository.isBookFavourite(bookId).onEach {isFavourite ->
            mState.update{
                it.copy(isFavourite = isFavourite)
            }
        }.launchIn(viewModelScope)
    }

    fun fetchBookDescription() = viewModelScope.launch {
        bookRepository.getBookDescription(bookId = bookId).onSuccess { description ->
            mState.update {
                it.copy(
                    book = it.book?.copy(
                        description = description
                    ),
                    isLoading = false
                )
            }
        }
    }
}