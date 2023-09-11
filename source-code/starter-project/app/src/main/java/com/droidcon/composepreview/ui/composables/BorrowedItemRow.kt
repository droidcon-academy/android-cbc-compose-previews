package com.droidcon.composepreview.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidcon.composepreview.data.models.BorrowItem

@Composable
fun BorrowedItemRow(item: BorrowItem) {

  var expanded by remember { mutableStateOf(false) }

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .clickable {
        expanded = !expanded
      }
      .padding(horizontal = 16.dp),
    shape = RoundedCornerShape(4.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surface
    ),
    elevation = CardDefaults.cardElevation(
      defaultElevation = 4.dp
    )
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Text(
        text = item.itemName,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
      )
      AnimatedVisibility(expanded) {
        Column {
          Spacer(modifier = Modifier.padding(top = 10.dp))
          Text(text = item.borrowerName)
          Spacer(modifier = Modifier.padding(top = 10.dp))
          Text(text = item.borrowDate)
        }
      }
    }
  }
}
