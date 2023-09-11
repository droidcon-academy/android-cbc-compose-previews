package com.droidcon.composepreview.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.droidcon.composepreview.BorrowViewModel
import com.droidcon.composepreview.ui.composables.BackTopBar
import com.droidcon.composepreview.ui.composables.DatePicker
import com.droidcon.composepreview.ui.composables.InputText
import com.droidcon.composepreview.ui.composables.SaveButton

@Composable
fun AddItemScreen(navController: NavController, borrowViewModel: BorrowViewModel) {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      BackTopBar(title = "Add Borrowed Item") {
        navController.popBackStack()
      }
    },
    content = {
      AddItemForm(borrowViewModel, navController, it)
    }
  )
}

@Composable
fun AddItemForm(borrowViewModel: BorrowViewModel, navController: NavController, paddingValues: PaddingValues) {
  val activity = LocalContext.current as ComponentActivity
  var dateSelected by remember { mutableStateOf("") }
  val updateDate = { date: String? ->
    dateSelected = date ?: ""
  }

  /**
   * Since the Column is the main container of the UI, set
   * its background to the `background` color of the theme
   */
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = MaterialTheme.colorScheme.background)
      .padding(paddingValues)
  ) {
    InputText(
      label = "Enter item name",
      requestFocus = true,
      onTextChange = { borrowViewModel.itemName = it })
    InputText(
      label = "Enter borrower name",
      onTextChange = { borrowViewModel.borrowerName = it }
    )
    DatePicker(datePicked = dateSelected, updatedDate = updateDate, activity = activity , viewModel = borrowViewModel)
    SaveButton(viewModel = borrowViewModel, navController = navController)
  }
}