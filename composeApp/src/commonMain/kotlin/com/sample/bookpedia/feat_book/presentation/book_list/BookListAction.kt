package com.sample.bookpedia.feat_book.presentation.book_list

import com.sample.bookpedia.feat_book.domain.model.Book

sealed interface BookListAction {
    data class OnSearchedQueryChanged(val query:String): BookListAction
    data class OnBookClicked(val book: Book): BookListAction
    data class OnTabSelected(val index:Int): BookListAction
}