package com.droidcon.composepreview.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.droidcon.composepreview.ui.theme.LocalTheme
import com.droidcon.composepreview.ui.theme.Theme

@Composable
fun ThemeDialog(onPopupDismissed: () -> Unit) {
  var theme by LocalTheme.current
  Dialog(onDismissRequest = onPopupDismissed) {
    Column(
      modifier = Modifier
        .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(6.dp))
        .padding(16.dp)
    ) {
      Text(text = "Choose theme", color = MaterialTheme.colorScheme.onSurface)
      Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = theme == Theme.LIGHT, onCheckedChange = {
          theme = Theme.LIGHT
        })
        Text(text = "Light", color = MaterialTheme.colorScheme.onSurface)
      }
      Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = theme == Theme.DARK, onCheckedChange = {
          theme = Theme.DARK
        })
        Text(text = "DARK", color = MaterialTheme.colorScheme.onSurface)
      }
      Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = theme == Theme.FOLLOW_SYSTEM, onCheckedChange = {
          theme = Theme.FOLLOW_SYSTEM
        })
        Text(text = "Follow System", color = MaterialTheme.colorScheme.onSurface)
      }
    }
  }
}
