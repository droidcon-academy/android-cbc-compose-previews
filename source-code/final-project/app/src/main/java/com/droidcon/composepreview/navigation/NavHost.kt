package com.droidcon.composepreview.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.droidcon.composepreview.BorrowViewModel
import com.droidcon.composepreview.screens.AddItemScreen
import com.droidcon.composepreview.screens.ListScreen

@Composable
fun AppNavigation(navController: NavHostController, viewModel: BorrowViewModel) {
  NavHost(
    navController = navController,
    startDestination = Screens.ListScreen.route,
    enterTransition = { expandIn(animationSpec = tween(800)) },
    exitTransition = { shrinkOut(animationSpec = tween(800)) }
  ) {
    composable(Screens.ListScreen.route,
      enterTransition = {
        if (initialState.destination.route == Screens.ItemInputScreen.route) slideIntoContainer(
          AnimatedContentTransitionScope.SlideDirection.Right,
          animationSpec = tween(600)
        )
        else null
      },
      exitTransition = {
        if (targetState.destination.route == Screens.ItemInputScreen.route) slideOutOfContainer(
          AnimatedContentTransitionScope.SlideDirection.Left,
          animationSpec = tween(600)
        )
        else null
      }
    ) {
      ListScreen(navController, viewModel)
    }

    composable(Screens.ItemInputScreen.route,
      enterTransition = {
        if (initialState.destination.route == Screens.ListScreen.route) slideIntoContainer(
          AnimatedContentTransitionScope.SlideDirection.Left,
          animationSpec = tween(600)
        )
        else null
      },
      exitTransition = {
        if (targetState.destination.route == Screens.ListScreen.route) slideOutOfContainer(
          AnimatedContentTransitionScope.SlideDirection.Right,
          animationSpec = tween(600)
        )
        else null
      }
    ) {
      AddItemScreen(navController, viewModel)
    }
  }
}