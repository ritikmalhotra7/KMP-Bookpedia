package com.sample.bookpedia.feat_book.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity("author_table")
data class AuthorEntity(
    @PrimaryKey
    val id:Int = 0,
    val authorName:String
)
