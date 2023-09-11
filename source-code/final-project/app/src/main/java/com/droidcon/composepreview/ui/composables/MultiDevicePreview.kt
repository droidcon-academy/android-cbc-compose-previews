package com.droidcon.composepreview.ui.composables

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    showSystemUi = true,
    group = "With system UI",
    device = Devices.NEXUS_5X
)
@Preview(
    showSystemUi = true,
    group = "With system UI",
    device = Devices.NEXUS_10
)
annotation class MultiDevicePreview