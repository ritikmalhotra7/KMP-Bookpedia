package com.sample.bookpedia.feat_book.presentation.book_details

import com.sample.bookpedia.feat_book.domain.model.Book

sealed interface BookDetailsAction{
    data object OnBackClicked: BookDetailsAction
    data object OnFavouriteClick: BookDetailsAction
    data class OnSelectedBookChange(val book: Book): BookDetailsAction
}