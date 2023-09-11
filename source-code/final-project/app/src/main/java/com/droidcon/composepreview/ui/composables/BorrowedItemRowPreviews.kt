package com.droidcon.composepreview.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.droidcon.composepreview.data.models.BorrowItem
import com.droidcon.composepreview.ui.theme.BorrowTheme

/**
 * A regular preview using the default theme - which is a
 * light theme
 */
@Preview(
    group = "Individual rows"
)
@Composable
fun BorrowedItemRowPreview(
    @PreviewParameter(BorrowItemsProvider::class) item: BorrowItem
) {
    BorrowTheme {
        BorrowedItemRow(
            item
        )
    }
}

/**
 * Preview the Composable using the dark theme colors of the
 * BorrowTheme theme
 */
@Preview(
    group = "Individual rows",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun BorrowedItemRowDarkPreview() {
    BorrowTheme {
        BorrowedItemRow(
            BorrowItem(
                id = 0,
                itemName = "PlayStation",
                borrowerName = "Steve",
                borrowDate = "2-11-2022"
            )
        )
    }
}


@MultiDevicePreview
@Composable
fun BorrowedItemRowDevicePreview() {
    BorrowTheme {
        Column {
            BorrowedItemRow(
                BorrowItem(
                    id = 0,
                    itemName = "PlayStation 5 with a lot of storage and very expensive controllers",
                    borrowerName = "Steve",
                    borrowDate = "2-11-2022"
                )
            )
        }
    }
}