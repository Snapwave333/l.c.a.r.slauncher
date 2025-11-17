package com.lcars.launcher.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lcars.launcher.ui.theme.LcarsShapes
import com.lcars.launcher.ui.theme.LcarsTheme
import com.lcars.launcher.ui.theme.LcarsTypography

/**
 * LCARS Vertical Rail
 * Side rails that contain status information and quick actions
 */
@Composable
fun LcarsVerticalRail(
    modifier: Modifier = Modifier,
    position: RailPosition = RailPosition.START,
    backgroundColor: Color = LcarsTheme.palette.backgroundSecondary,
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = when (position) {
        RailPosition.START -> LcarsShapes.RailEnd
        RailPosition.END -> LcarsShapes.RailStart
    }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(100.dp)
            .clip(shape)
            .background(backgroundColor)
            .padding(vertical = 16.dp, horizontal = 8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = if (position == RailPosition.START)
            Alignment.Start else Alignment.End,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        content()
    }
}

/**
 * LCARS Rail Button/Panel Item
 */
@Composable
fun LcarsRailItem(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = LcarsTheme.palette.accentOrange,
    icon: @Composable (() -> Unit)? = null
) {
    LcarsPanel(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp),
        label = label,
        color = color,
        textColor = LcarsTheme.palette.textOnPanel,
        textStyle = LcarsTypography.LabelMedium,
        labelPosition = LabelPosition.BOTTOM,
        onClick = onClick
    ) {
        if (icon != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 28.dp),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }
        }
    }
}

/**
 * LCARS Rail Info Display (for time, date, status)
 */
@Composable
fun LcarsRailInfo(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    color: Color = LcarsTheme.palette.panelPrimary
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(LcarsShapes.Small)
            .background(color)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label.uppercase(),
            style = LcarsTypography.LabelSmall,
            color = LcarsTheme.palette.textOnPanel
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value.uppercase(),
            style = LcarsTypography.BodyMedium,
            color = LcarsTheme.palette.textOnPanel
        )
    }
}

enum class RailPosition {
    START, END
}
