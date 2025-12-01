package com.sample.bookpedia.feat_book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.bookpedia.core.domain.onError
import com.sample.bookpedia.core.domain.onSuccess
import com.sample.bookpedia.core.utils.toUiText
import com.sample.bookpedia.feat_book.domain.model.Book
import com.sample.bookpedia.feat_book.domain.repository.BookRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BookListState())
    val state = _state
        .onStart {
            if (cachedBookList.isEmpty()) {
                observeSearchQuery()
            }
            observeFavouriteBook()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5000),
            initialValue = _state.value
        )

    private var cachedBookList: List<Book> = listOf()
    private var searchJob: Job? = null
    private var favouriteBookJob: Job? = null

    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnSearchedQueryChanged -> {
                _state.update {
                    it.copy(searchedQuery = action.query)
                }
            }

            is BookListAction.OnBookClicked -> {

            }

            is BookListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }
        }
    }

    private fun observeFavouriteBook(){
        favouriteBookJob?.cancel()
        favouriteBookJob = bookRepository.getFavouriteBooks().onEach {favouriteBooks ->
            _state.update{
                it.copy(favouriteBooks = favouriteBooks)
            }
        }.launchIn(viewModelScope)
    }

    private fun observeSearchQuery() {
        state
            .map { it.searchedQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                errorMessage = null,
                                searchedResults = cachedBookList
                            )
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchBooks(query)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        bookRepository.searchBooks(query).onSuccess { searchedResponse ->
            _state.update {
                it.copy(
                    isLoading = false,
                    errorMessage = null,
                    searchedResults = searchedResponse
                )
            }
        }.onError { error ->
            _state.update {
                it.copy(
                    searchedResults = emptyList(),
                    isLoading = false,
                    errorMessage = error.toUiText()
                )
            }
        }
    }
}