package com.sample.bookpedia.feat_book.presentation.book_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sample.bookpedia.core.presentation.LightBlue
import com.sample.bookpedia.feat_book.presentation.book_details.utils.ChipSize


@Composable
fun BookChip(
    modifier: Modifier = Modifier,
    chipSize: ChipSize = ChipSize.REGULAR,
    chipContent: @Composable () -> Unit
) {
    Row(
        modifier = modifier.widthIn(
            min = when (chipSize) {
                ChipSize.SMALL -> 48.dp
                ChipSize.REGULAR -> 72.dp
            }
        ).clip(RoundedCornerShape(16.dp))
            .background(LightBlue)
            .padding(vertical = 8.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        chipContent()
    }
}