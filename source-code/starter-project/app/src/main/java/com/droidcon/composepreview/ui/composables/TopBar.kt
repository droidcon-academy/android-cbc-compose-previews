package com.droidcon.composepreview.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.droidcon.composepreview.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
  TopAppBar(
    title = { Text(title) },
    actions = {
      var showPopup by remember { mutableStateOf(false) }

      Icon(
        painter = painterResource(id = R.drawable.ic_dark_mode),
        contentDescription = "Toggle theme",
        modifier = Modifier.clickable {
          showPopup = true
        }
      )

      val onPopupDismissed = { showPopup = false }
      if (showPopup) {
        ThemeDialog(onPopupDismissed)
      }
    }
  )
}
