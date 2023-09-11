package com.droidcon.composepreview.ui.composables

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.droidcon.composepreview.data.models.BorrowItem
import com.droidcon.composepreview.ui.theme.BorrowTheme

@Preview(
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
annotation class LightDarkThemePreview