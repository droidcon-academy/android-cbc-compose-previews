package com.droidcon.composepreview.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.droidcon.composepreview.BorrowViewModel
import com.droidcon.composepreview.data.models.BorrowItem
import com.droidcon.composepreview.navigation.Screens
import com.droidcon.composepreview.ui.composables.BorrowedItemRow
import com.droidcon.composepreview.ui.composables.EmptyStateScreen
import com.droidcon.composepreview.ui.composables.TopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListScreen(navController: NavController, borrowViewModel: BorrowViewModel) {
  val keyboardController = LocalSoftwareKeyboardController.current
  keyboardController?.hide()

  borrowViewModel.getAllItems()
  val items by borrowViewModel.items.observeAsState()
  ListScreenContent(
    fabClicked = {
      navController.navigate(Screens.ItemInputScreen.route)
    },
    items = items
  )
}


@Composable
fun ListScreenContent(
  fabClicked: () -> Unit,
  items: List<BorrowItem>?
) {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      TopBar(title = "Borrowed Items")
    },
    content = { padding ->
      if (items.isNullOrEmpty()) {
        EmptyStateScreen(
          modifier = Modifier.padding(padding)
        )
      } else {
        ItemList(
          modifier = Modifier.padding(padding),
          borrowedItems = items
        )
      }
    },
    floatingActionButton = {
      ExtendedFloatingActionButton(
        modifier = Modifier
          .padding(16.dp),
        onClick = fabClicked,
        icon = {
          Icon(
            Icons.Filled.Add,
            contentDescription = "Create"
          )
        },
        text = { Text("Create") }
      )
    }
  )
}

@Composable
fun ItemList(
  modifier: Modifier = Modifier,
  borrowedItems: List<BorrowItem>
) {
  /**
   * Since the LazyColumn is the main container of the UI, set
   * its background to the `background` color of the theme
   */
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    items(borrowedItems) { borrowedItem ->
      BorrowedItemRow(borrowedItem)
    }
  }
}

@Preview
@Composable
fun ListScreenContentEmptyPreview() {
  ListScreenContent(fabClicked = {  }, items = emptyList())
}

@Preview
@Composable
fun ListScreenContentListPreview() {
  ListScreenContent(
    fabClicked = {  },
    items = listOf(
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
  )
}
