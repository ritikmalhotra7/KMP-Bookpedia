package com.sample.bookpedia.feat_book.data.dto.details

import com.sample.bookpedia.feat_book.data.serializers.BookWorkSerializer
import kotlinx.serialization.Serializable

@Serializable(BookWorkSerializer::class)
data class BookWorkDto(
    val description:String? = null
)
