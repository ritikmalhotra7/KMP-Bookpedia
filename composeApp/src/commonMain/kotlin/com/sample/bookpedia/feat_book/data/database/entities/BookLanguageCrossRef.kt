package com.sample.bookpedia.feat_book.data.database.entities

import androidx.room.Entity

@Entity(primaryKeys =
["bookId", "languageId"])
data class BookLanguageCrossRef(
    val bookId:String,
    val languageId:Int
)
