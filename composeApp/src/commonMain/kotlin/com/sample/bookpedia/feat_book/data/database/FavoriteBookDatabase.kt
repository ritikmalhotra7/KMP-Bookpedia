package com.sample.bookpedia.feat_book.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.bookpedia.feat_book.data.database.entities.AuthorEntity
import com.sample.bookpedia.feat_book.data.database.entities.BookAuthorCrossRef
import com.sample.bookpedia.feat_book.data.database.entities.BookEntity
import com.sample.bookpedia.feat_book.data.database.entities.BookLanguageCrossRef
import com.sample.bookpedia.feat_book.data.database.entities.LanguageEntity
import androidx.room.RoomDatabaseConstructor


@ConstructedBy(BookDatabaseConstructor::class)
@Database(
    entities = [BookEntity::class, LanguageEntity::class, AuthorEntity::class, BookLanguageCrossRef::class, BookAuthorCrossRef::class],
    version = 1
)
abstract class FavouriteBookDatabase : RoomDatabase() {
    abstract fun bookDao(): FavouriteBookDao

    companion object {
        const val DATABASE_NAME = "favourite-book-db"
    }
}