package com.sample.bookpedia.feat_book.data.database

import androidx.room.Room
import androidx.room.RoomDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<FavouriteBookDatabase> {
        val dbFile = "/${FavouriteBookDatabase.DATABASE_NAME}"
        return Room.databaseBuilder<FavouriteBookDatabase>(
            name = dbFile
        )
    }
}