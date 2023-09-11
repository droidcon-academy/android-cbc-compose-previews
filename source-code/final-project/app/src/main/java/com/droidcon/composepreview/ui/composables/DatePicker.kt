package com.droidcon.composepreview.ui.composables

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension.Companion.fillToConstraints
import com.droidcon.composepreview.BorrowViewModel
import java.util.Calendar

@Composable
fun DatePicker(
  datePicked: String?,
  updatedDate: (date: String?) -> Unit,
  activity: ComponentActivity,
  viewModel: BorrowViewModel
) {
  val focusManager = LocalFocusManager.current

  /**
   * Since the date picker box is a surface on top of the background,
   * set the box's background to the `surface` color from
   * the theme.
   *
   * We also draw a border on top of the box. Hence the border color
   * to the `onSurface` color of the theme.
   */
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentSize(Alignment.TopStart)
      .padding(16.dp)
      .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
      .border(width = 2.dp, color = MaterialTheme.colorScheme.onSurface, shape = RoundedCornerShape(10.dp))
      .clickable(
        indication = rememberRipple(bounded = true),
        interactionSource = remember {
          MutableInteractionSource()
        }
      ) {
        focusManager.clearFocus()
        showDatePicker(
          activity,
          viewModel.date,
          updatedDate
        )
      }
  ) {
    viewModel.date = datePicked.toString()

    ConstraintLayout(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {

      val (label, iconView) = createRefs()

      val showLabel = if (datePicked.isNullOrEmpty()) "Choose a Date" else datePicked

      /**
       * Set the text color to `onSurface` color from the theme
       * since the text is drawn on the surface of the box
       */
      Text(
        text = showLabel,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
          .fillMaxWidth()
          .constrainAs(label) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(iconView.start)
            width = fillToConstraints
          }
      )

      /**
       * Set the icon color to `onSurface` color from the theme
       * since the icon is drawn on the surface of the box
       */
      Icon(
        imageVector = Icons.Default.DateRange,
        contentDescription = null,
        modifier = Modifier
          .size(20.dp, 20.dp)
          .constrainAs(iconView) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
          },
        tint = MaterialTheme.colorScheme.onSurface
      )
    }
  }
}

fun showDatePicker(
  activity: ComponentActivity,
  previouslySelectedDate: String = "",
  updatedDate: (String) -> Unit
) {
  val c = Calendar.getInstance()
  val calendarYear = c.get(Calendar.YEAR)
  val calendarMonth = c.get(Calendar.MONTH)
  val calendarDay = c.get(Calendar.DAY_OF_MONTH)
  val datePickerDialog = DatePickerDialog(
    activity, { _: DatePicker, year: Int, month: Int, day: Int ->
      updatedDate("$day-${month + 1}-$year")
    }, calendarYear, calendarMonth, calendarDay
  )
  if (previouslySelectedDate.isNotEmpty()) {
    val prevYear = previouslySelectedDate.split("-")[2].toInt()
    val prevMonth = previouslySelectedDate.split("-")[1].toInt()
    val prevDay = previouslySelectedDate.split("-")[0].toInt()
    datePickerDialog.updateDate(prevYear, prevMonth - 1, prevDay)
  }
  datePickerDialog.show()
}