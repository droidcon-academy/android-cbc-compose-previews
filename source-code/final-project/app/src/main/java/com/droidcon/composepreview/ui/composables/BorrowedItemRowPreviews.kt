package com.droidcon.composepreview.ui.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.droidcon.composepreview.data.models.BorrowItem
import com.droidcon.composepreview.screens.ListScreen
import com.droidcon.composepreview.ui.theme.BorrowTheme

@LightDarkThemePreview
@Composable
fun BorrowedItemRowPreview(
	@PreviewParameter(BorrowItemsProvider::class) item: BorrowItem
) {

	BorrowedItemRow(
		item
	)
}

@MultiDevicePreview
@Composable
fun BorrowedItemRowDevicePreview() {

	BorrowedItemRow(
		item = BorrowItem(
			id = 0,
			itemName = "XBoz Series X",
			borrowerName = "Steve",
			borrowDate = "2-11-2023"
		)
	)
}

@Preview(
	uiMode = UI_MODE_NIGHT_YES,
	group = "Individual rows"
)
@Composable
fun BorrowedItemRowDeviceDarkThemePreview() {

	BorrowTheme {
		BorrowedItemRow(
			item = BorrowItem(
				id = 0,
				itemName = "XBoz Series X",
				borrowerName = "Steve",
				borrowDate = "2-11-2023"
			)
		)
	}
}

@Preview
@Composable
fun ListScreenPreview() {
	ListScreen(
		navController = rememberNavController(),
		borrowViewModel = viewModel()
	)
}
