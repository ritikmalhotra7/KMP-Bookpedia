package com.sample.bookpedia.feat_book.`data`.database

import androidx.room.RoomDatabaseConstructor

public actual object BookDatabaseConstructor : RoomDatabaseConstructor<FavouriteBookDatabase> {
  actual override fun initialize(): FavouriteBookDatabase =
      com.sample.bookpedia.feat_book.`data`.database.FavouriteBookDatabase_Impl()
}
