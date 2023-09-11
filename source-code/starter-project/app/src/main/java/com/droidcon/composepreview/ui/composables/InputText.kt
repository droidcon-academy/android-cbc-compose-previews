package com.droidcon.composepreview.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputText(label: String, requestFocus: Boolean = false, onTextChange: (String) -> Unit) {
  var userInput by remember { mutableStateOf("") }
  val focusRequester = remember { FocusRequester() }
  val keyboardController = LocalSoftwareKeyboardController.current
  val focus = remember { mutableStateOf(false) }
  Column(
    modifier = Modifier
      .fillMaxWidth()
  ) {
    Text(
      text = label,
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    )

    /**
     * Set the box color to `surface` since it's a surface on top of the UI background.
     * Since the border is drawn on top of the box (surface), use the `onSurface` color.
     *
     * The text and the cursor are both drawn on the surface, hence their color is set
     * to `onSurface`.
     */
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
        .border(width = 2.dp, color = MaterialTheme.colorScheme.onSurface, shape = RoundedCornerShape(10.dp))
    ) {
      BasicTextField(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
          .focusRequester(focusRequester)
          .onFocusChanged {
            if (focus.value != it.isFocused) {
              focus.value = it.isFocused
              if (!it.isFocused) {
                keyboardController?.hide()
              }
            }
          },
        textStyle = TextStyle(
          color = MaterialTheme.colorScheme.onSurface
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
        keyboardActions = KeyboardActions(
          onNext = {
            focusRequester.requestFocus()
          }
        ),

        onValueChange = {
          userInput = it
          onTextChange(userInput)
        },
        value = userInput,
      )

      if (requestFocus) {
        LaunchedEffect("") {
          delay(300)
          keyboardController?.show()
          focusRequester.requestFocus()
        }
      }
    }
  }
}
