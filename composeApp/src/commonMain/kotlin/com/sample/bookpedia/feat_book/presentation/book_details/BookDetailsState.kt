package com.sample.bookpedia.feat_book.presentation.book_details

import com.sample.bookpedia.feat_book.domain.model.Book

data class BookDetailsState(
    val isLoading:Boolean = true,
    val isFavourite:Boolean = false,
    val book: Book? = null
)