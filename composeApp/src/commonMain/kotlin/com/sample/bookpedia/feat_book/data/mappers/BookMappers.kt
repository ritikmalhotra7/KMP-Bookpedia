package com.sample.bookpedia.feat_book.data.mappers

import com.sample.bookpedia.core.utils.printWith
import com.sample.bookpedia.feat_book.data.database.entities.AuthorEntity
import com.sample.bookpedia.feat_book.data.database.entities.BookEntity
import com.sample.bookpedia.feat_book.data.database.entities.LanguageEntity
import com.sample.bookpedia.feat_book.data.database.relations.FullBookDetails
import com.sample.bookpedia.feat_book.data.dto.search.SearchedBookDto
import com.sample.bookpedia.feat_book.domain.model.Book

fun SearchedBookDto.toBook(): Book {
    return Book(
        id = id.substringAfterLast("/"),
        title = title,
        imageUrl = if (coverKey != null) "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        else "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg",
        authors = authorNames?: emptyList(),
        description = null,
        languages = languages?: emptyList(),
        firstPublishYear = firstPublishYear.toString(),
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numPages = numPagesMedian,
        numEditions = numEditions
    )
}


fun FullBookDetails.toBook(): Book {
    this.printWith("full")
    return Book(
        id = book.id,
        title = book.title,
        description = book.description,
        imageUrl = book.imageUrl,
        firstPublishYear = book.firstPublishYear,
        languages = languages.map { it.name },
        authors = authors.map { it.authorName },
        ratingCount = book.ratingCount,
        averageRating = book.ratingAverage,
        numPages = book.numPagesMedian,
        numEditions = book.numEditions
    )
}
fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        firstPublishYear = firstPublishYear,
        ratingAverage = averageRating,
        ratingCount = ratingCount,
        numPagesMedian = numPages,
        numEditions = numEditions
    )
}

fun Book.toLanguageEntity(): List<LanguageEntity> {
    return languages.map { languageName ->
        LanguageEntity(
            name = languageName
        )
    }
}

fun Book.toAuthorEntity(): List<AuthorEntity> {
    return authors.map { authorName ->
        AuthorEntity(
            authorName = authorName
        )
    }
}