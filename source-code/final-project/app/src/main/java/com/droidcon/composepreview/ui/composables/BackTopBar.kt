package com.droidcon.composepreview.ui.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackTopBar(title: String, onBackPressed: () -> Unit) {
  TopAppBar(
    title = { Text(title) },
    modifier = Modifier,
    navigationIcon = {
      IconButton(onClick = {
        onBackPressed()
      }) {
        Icon(
          imageVector = Icons.Default.ArrowBack,
          contentDescription = "Back Icon",
        )
      }
    }
  )
}
