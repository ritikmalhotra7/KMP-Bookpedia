package com.sample.bookpedia.feat_book.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.sample.bookpedia.feat_book.data.database.entities.AuthorEntity
import com.sample.bookpedia.feat_book.data.database.entities.BookAuthorCrossRef
import com.sample.bookpedia.feat_book.data.database.entities.BookEntity
import com.sample.bookpedia.feat_book.data.database.entities.BookLanguageCrossRef
import com.sample.bookpedia.feat_book.data.database.entities.LanguageEntity
import com.sample.bookpedia.feat_book.data.database.relations.FullBookDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteBookDao {

    //Book Queries
    @Upsert
    suspend fun insertBook(book: BookEntity)

    @Transaction
    @Query("SELECT * FROM book_table")
    fun getFavouriteBooks(): Flow<List<FullBookDetails>>

    @Query("SELECT * from book_table WHERE id = :bookId")
    suspend fun getFavouriteBook(bookId: String): BookEntity?

    @Query("DELETE from book_table WHERE id = :bookId")
    suspend fun deleteFavouriteBook(bookId: String): Int

    //Language Queries
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLanguages(languages: List<LanguageEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookLanguageCrossRefs(crossRefs: List<BookLanguageCrossRef>)

    @Query("SELECT * FROM language_table")
    suspend fun getLanguages(): List<LanguageEntity>

    //Author Queries
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAuthors(authors: List<AuthorEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookAuthorCrossRefs(crossRefs: List<BookAuthorCrossRef>)

    @Query("SELECT * FROM author_table")
    suspend fun getAuthors(): List<AuthorEntity>

    //Relational Queries
    @Transaction
    suspend fun upsertBookWithRelations(
        book: BookEntity, languages: List<LanguageEntity>, authors: List<AuthorEntity>
    ) {
        val bookId = book.id
        val newLanguageIds = insertLanguages(languages).map { it.toInt() }
        val newAuthorIds = insertAuthors(authors).map { it.toInt() }
        val languageCrossRefs = newLanguageIds.map { languageId ->
            BookLanguageCrossRef(bookId = bookId, languageId = languageId)
        }
        val authorCrossRefs = newAuthorIds.map { authorId ->
            BookAuthorCrossRef(bookId = bookId, authorId = authorId)
        }
        insertBook(book)
        insertBookLanguageCrossRefs(languageCrossRefs)
        insertBookAuthorCrossRefs(authorCrossRefs)
    }
}