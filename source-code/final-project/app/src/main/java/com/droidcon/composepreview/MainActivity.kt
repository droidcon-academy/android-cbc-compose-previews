package com.droidcon.composepreview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.droidcon.composepreview.navigation.AppNavigation
import com.droidcon.composepreview.ui.theme.BorrowTheme
import com.droidcon.composepreview.ui.theme.LocalTheme
import com.droidcon.composepreview.ui.theme.Theme
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      val borrowViewModel: BorrowViewModel by viewModel()
      val theme = remember {
        mutableStateOf(borrowViewModel.theme)
      }

      CompositionLocalProvider(LocalTheme provides theme) {
        borrowViewModel.theme = theme.value
        BorrowTheme(theme.value) {
          val darkTheme by LocalTheme.current
          val color = when(darkTheme) {
            Theme.LIGHT -> R.color.statusBar
            Theme.DARK -> R.color.statusBarDark
            Theme.FOLLOW_SYSTEM -> if (isSystemInDarkTheme()) {
              R.color.statusBarDark
            } else {
              R.color.statusBar
            }
          }
          window.statusBarColor = ContextCompat.getColor(this, color)
          AppNavigation(navController = navController, viewModel = borrowViewModel)

        }

      }
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  BorrowTheme {
    Greeting("Android")
  }
}