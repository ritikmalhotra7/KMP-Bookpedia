package com.sample.bookpedia.feat_book.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity("language_table")
data class LanguageEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name:String,
)
