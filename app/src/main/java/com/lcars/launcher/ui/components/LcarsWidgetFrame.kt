package com.lcars.launcher.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.lcars.launcher.ui.theme.LcarsShapes
import com.lcars.launcher.ui.theme.LcarsTheme

/**
 * LCARS widget frame for displaying Android widgets
 */
@Composable
fun LcarsWidgetFrame(
    widgetView: android.appwidget.AppWidgetHostView?,
    label: String = "WIDGET",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = LcarsTheme.palette.backgroundSecondary,
                shape = LcarsShapes.Medium
            )
            .border(
                width = 2.dp,
                color = LcarsTheme.palette.accentCyan,
                shape = LcarsShapes.Medium
            )
            .padding(8.dp)
    ) {
        // Widget label header
        LcarsPanel(
            label = label,
            color = LcarsTheme.palette.accentCyan,
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Widget content
        if (widgetView != null) {
            AndroidView(
                factory = { widgetView },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "NO WIDGET CONFIGURED",
                    style = LcarsTheme.typography.label,
                    color = LcarsTheme.palette.textSecondary
                )
            }
        }
    }
}

/**
 * Widget selector/picker UI
 */
@Composable
fun LcarsWidgetPicker(
    availableWidgets: List<android.appwidget.AppWidgetProviderInfo>,
    onWidgetSelected: (android.appwidget.AppWidgetProviderInfo) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LcarsTheme.palette.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LcarsPanel(
            label = "SELECT WIDGET",
            color = LcarsTheme.palette.panelPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )

        // Widget list
        availableWidgets.forEach { widgetInfo ->
            LcarsButton(
                label = widgetInfo.loadLabel(context.packageManager).toString(),
                onClick = { onWidgetSelected(widgetInfo) },
                color = LcarsTheme.palette.accentCyan,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LcarsButton(
            label = "CANCEL",
            onClick = onDismiss,
            color = LcarsTheme.palette.statusRed,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
