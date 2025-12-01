package com.sample.bookpedia.core.utils

import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.error_disk_full
import cmp_bookpedia.composeapp.generated.resources.error_no_internet
import cmp_bookpedia.composeapp.generated.resources.error_request_timeout
import cmp_bookpedia.composeapp.generated.resources.error_serialization
import cmp_bookpedia.composeapp.generated.resources.error_too_many_requests
import cmp_bookpedia.composeapp.generated.resources.error_unknown
import com.sample.bookpedia.core.domain.DataError
import com.sample.bookpedia.core.presentation.UiText

fun DataError.toUiText(): UiText{
    val stringResource = when(this){
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.UNKNOWN_ERROR -> Res.string.error_unknown
        DataError.Remote.REQUEST_TIME_OUT -> Res.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.SERVER_ERROR -> Res.string.error_unknown
        DataError.Remote.SERIALIZATION_ERROR -> Res.string.error_serialization
        DataError.Remote.UNKNOWN_ERROR -> Res.string.error_unknown
    }
    return UiText.StringResourceId(stringResource)
}