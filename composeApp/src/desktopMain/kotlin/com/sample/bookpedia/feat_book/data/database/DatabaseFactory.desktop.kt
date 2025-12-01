package com.sample.bookpedia.feat_book.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<FavouriteBookDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val username = System.getProperty("user.home")
        val appDataDir = when{
            os.contains("win") -> File(System.getenv("APPDATA"),"Bookpedia")
            os.contains("mac") -> File(username, "Library/Application Support/Bookpedia")
            else -> File(username, ".local/share/Bookpedia")
        }

        if(!appDataDir.exists()){
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, FavouriteBookDatabase.DATABASE_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}