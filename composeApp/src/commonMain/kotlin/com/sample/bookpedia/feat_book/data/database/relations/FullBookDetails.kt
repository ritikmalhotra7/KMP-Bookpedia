package com.sample.bookpedia.feat_book.data.database.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.sample.bookpedia.feat_book.data.database.entities.AuthorEntity
import com.sample.bookpedia.feat_book.data.database.entities.BookAuthorCrossRef
import com.sample.bookpedia.feat_book.data.database.entities.BookEntity
import com.sample.bookpedia.feat_book.data.database.entities.BookLanguageCrossRef
import com.sample.bookpedia.feat_book.data.database.entities.LanguageEntity
import kotlinx.serialization.Serializable

@Serializable
data class FullBookDetails(
    @Embedded
    val book: BookEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            BookLanguageCrossRef::class,
            parentColumn = "bookId",
            entityColumn = "languageId"
        )
    )
    val languages: List<LanguageEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            BookAuthorCrossRef::class,
            parentColumn = "bookId",
            entityColumn = "authorId"
        )
    )
    val authors: List<AuthorEntity>
)
