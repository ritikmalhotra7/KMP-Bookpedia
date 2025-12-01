package com.sample.bookpedia.feat_book.data.dto.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
    @SerialName("docs") val results :List<SearchedBookDto>
)