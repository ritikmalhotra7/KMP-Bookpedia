package com.sample.bookpedia.feat_book.presentation.book_graph

import androidx.lifecycle.ViewModel
import com.sample.bookpedia.feat_book.domain.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//This is shared to Book Graph Navigation
class SharedBookViewModel: ViewModel() {
    private val mSelectedBook = MutableStateFlow<Book?>(null)
    val selectedBook = mSelectedBook.asStateFlow()

    fun onSelectBook(book: Book?){
        mSelectedBook.update {
            book
        }
    }
}