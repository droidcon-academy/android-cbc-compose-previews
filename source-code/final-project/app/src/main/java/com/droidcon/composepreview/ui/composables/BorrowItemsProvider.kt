package com.droidcon.composepreview.ui.composables

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.droidcon.composepreview.data.models.BorrowItem

class BorrowItemsProvider : PreviewParameterProvider<BorrowItem> {
    override val values = sequenceOf(
        BorrowItem(
            id = 0,
            itemName = "PlayStation",
            borrowerName = "Steve",
            borrowDate = "2-11-2022"
        ),
        BorrowItem(
            id = 0,
            itemName = "XBox",
            borrowerName = "Steve",
            borrowDate = "2-11-2022"
        )
    )
}