package com.sample.bookpedia.feat_book.data.repository

import androidx.sqlite.SQLiteException
import com.sample.bookpedia.core.domain.DataError
import com.sample.bookpedia.core.domain.EmptyResult
import com.sample.bookpedia.core.domain.Result
import com.sample.bookpedia.core.domain.map
import com.sample.bookpedia.core.utils.printWith
import com.sample.bookpedia.feat_book.data.database.FavouriteBookDao
import com.sample.bookpedia.feat_book.data.mappers.toAuthorEntity
import com.sample.bookpedia.feat_book.data.mappers.toBook
import com.sample.bookpedia.feat_book.data.mappers.toBookEntity
import com.sample.bookpedia.feat_book.data.mappers.toLanguageEntity
import com.sample.bookpedia.feat_book.data.remote.RemoteBookDataSource
import com.sample.bookpedia.feat_book.domain.model.Book
import com.sample.bookpedia.feat_book.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favouriteBookDao: FavouriteBookDao
) : BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query).map { dto ->
            dto.results.map {
                it.toBook()
            }
        }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        val localResult = favouriteBookDao.getFavouriteBook(bookId)
        favouriteBookDao.getFavouriteBooks().printWith("favBooks")
        favouriteBookDao.getLanguages().printWith("languages")
        favouriteBookDao.getAuthors().printWith("authors")
        return if (localResult == null) {
            remoteBookDataSource.getDescription(bookId).map {
                it.description
            }
        } else {
            Result.Success(localResult.description)
        }
    }

    override fun getFavouriteBooks(): Flow<List<Book>> {
        return favouriteBookDao.getFavouriteBooks().map { entities ->
            entities.map {
                it.toBook()
            }
        }
    }

    override fun isBookFavourite(id: String): Flow<Boolean> {
        return favouriteBookDao.getFavouriteBooks().map {
            it.any { it.book.id == id }
        }
    }

    override suspend fun addToFavourite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favouriteBookDao.upsertBookWithRelations(
                book.toBookEntity(),
                book.toLanguageEntity(),
                book.toAuthorEntity()

            )
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun removeFromFavourite(id: String) {
        favouriteBookDao.deleteFavouriteBook(id)
    }
}