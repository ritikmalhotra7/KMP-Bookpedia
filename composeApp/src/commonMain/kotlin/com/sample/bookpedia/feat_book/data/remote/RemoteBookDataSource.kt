package com.sample.bookpedia.feat_book.data.remote

import com.sample.bookpedia.core.domain.DataError
import com.sample.bookpedia.core.domain.Result
import com.sample.bookpedia.feat_book.data.dto.details.BookWorkDto
import com.sample.bookpedia.feat_book.data.dto.search.SearchResponseDto

interface  RemoteBookDataSource {
    suspend fun searchBooks(
        query:String? = null,
        resultLimit:Int? = null
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getDescription(bookWorkId:String):Result<BookWorkDto, DataError.Remote>
}