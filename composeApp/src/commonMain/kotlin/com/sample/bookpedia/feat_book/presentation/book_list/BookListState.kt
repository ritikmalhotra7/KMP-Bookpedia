package com.sample.bookpedia.feat_book.presentation.book_list

import com.sample.bookpedia.core.presentation.UiText
import com.sample.bookpedia.feat_book.domain.model.Book

data class BookListState(
    val searchedQuery:String = "kotlin",
    val searchedResults:List<Book> = emptyList(),
    val favouriteBooks:List<Book> = emptyList(),
    val isLoading:Boolean = true,
    val selectedTabIndex:Int = 0,
    val errorMessage: UiText? = null
)