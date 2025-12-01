package com.sample.bookpedia.feat_book.domain.repository

import com.sample.bookpedia.core.domain.DataError
import com.sample.bookpedia.core.domain.EmptyResult
import com.sample.bookpedia.core.domain.Result
import com.sample.bookpedia.feat_book.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query:String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId:String):Result<String?, DataError>

    fun getFavouriteBooks(): Flow<List<Book>>
    fun isBookFavourite(id:String): Flow<Boolean>

    suspend fun addToFavourite(book:Book): EmptyResult<DataError.Local>
    suspend fun removeFromFavourite(id:String)
}