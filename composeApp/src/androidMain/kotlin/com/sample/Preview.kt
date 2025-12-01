package com.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sample.bookpedia.feat_book.domain.model.Book
import com.sample.bookpedia.feat_book.presentation.book_list.BookListScreen
import com.sample.bookpedia.feat_book.presentation.book_list.BookListState

private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://test.com",
        authors = listOf("Ritik Malhotra"),
        description = "Description $it",
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.67854,
        ratingCount = 5,
        numPages = 100,
        numEditions = 3
    )
}

@Preview
@Composable
private fun BookListScreenPreview() {
    BookListScreen(
        state = BookListState(
            searchedResults = books
        ),
        onAction = {}
    )
}