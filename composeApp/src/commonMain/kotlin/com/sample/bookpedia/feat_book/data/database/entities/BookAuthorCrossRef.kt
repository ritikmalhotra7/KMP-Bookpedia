package com.sample.bookpedia.feat_book.data.database.entities

import androidx.room.Entity

@Entity(primaryKeys = ["bookId","authorId"])
data class BookAuthorCrossRef(
    val bookId: String,
    val authorId: Int
)
