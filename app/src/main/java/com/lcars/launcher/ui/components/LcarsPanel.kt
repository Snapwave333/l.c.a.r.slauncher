package com.lcars.launcher.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lcars.launcher.ui.theme.LcarsShapes
import com.lcars.launcher.ui.theme.LcarsTheme
import com.lcars.launcher.ui.theme.LcarsTypography

/**
 * Core LCARS Panel Component
 * The fundamental building block of the LCARS UI
 */
@Composable
fun LcarsPanel(
    modifier: Modifier = Modifier,
    label: String? = null,
    labelPosition: LabelPosition = LabelPosition.CENTER,
    color: Color = LcarsTheme.palette.panelPrimary,
    textColor: Color = LcarsTheme.palette.textOnPanel,
    textStyle: TextStyle = LcarsTypography.TitleMedium,
    asymmetric: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit = {}
) {
    val shape = if (asymmetric) LcarsShapes.MediumAsymmetric else LcarsShapes.Medium
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .clip(shape)
            .background(color)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = rememberRipple(color = Color.White.copy(alpha = 0.3f)),
                        onClick = onClick
                    )
                } else Modifier
            )
            .padding(16.dp),
        contentAlignment = when (labelPosition) {
            LabelPosition.START -> Alignment.CenterStart
            LabelPosition.CENTER -> Alignment.Center
            LabelPosition.END -> Alignment.CenterEnd
            LabelPosition.TOP -> Alignment.TopCenter
            LabelPosition.BOTTOM -> Alignment.BottomCenter
        }
    ) {
        if (label != null) {
            Text(
                text = label.uppercase(),
                style = textStyle,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        content()
    }
}

/**
 * Label positioning within a panel
 */
enum class LabelPosition {
    START, CENTER, END, TOP, BOTTOM
}

/**
 * Small LCARS Button Panel
 */
@Composable
fun LcarsButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = LcarsTheme.palette.panelPrimary,
    textColor: Color = LcarsTheme.palette.textOnPanel,
    enabled: Boolean = true
) {
    LcarsPanel(
        modifier = modifier
            .height(56.dp),
        label = label,
        color = if (enabled) color else color.copy(alpha = 0.5f),
        textColor = if (enabled) textColor else textColor.copy(alpha = 0.5f),
        textStyle = LcarsTypography.TitleSmall,
        onClick = if (enabled) onClick else null
    )
}

/**
 * Large LCARS Action Panel (for main actions)
 */
@Composable
fun LcarsActionPanel(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = LcarsTheme.palette.accentOrange,
    asymmetric: Boolean = true
) {
    LcarsPanel(
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth(),
        label = label,
        color = color,
        textColor = LcarsTheme.palette.textOnPanel,
        textStyle = LcarsTypography.TitleLarge,
        asymmetric = asymmetric,
        onClick = onClick
    )
}

/**
 * LCARS Status Strip (thin horizontal or vertical bars)
 */
@Composable
fun LcarsStatusStrip(
    modifier: Modifier = Modifier,
    color: Color = LcarsTheme.palette.statusDeepBlue,
    horizontal: Boolean = true
) {
    val shape = if (horizontal) LcarsShapes.Small else LcarsShapes.Small

    Box(
        modifier = modifier
            .then(
                if (horizontal) Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                else Modifier
                    .fillMaxHeight()
                    .width(8.dp)
            )
            .clip(shape)
            .background(color)
    )
}

/**
 * LCARS Divider with rounded ends
 */
@Composable
fun LcarsDivider(
    modifier: Modifier = Modifier,
    color: Color = LcarsTheme.palette.panelSecondary,
    thickness: androidx.compose.ui.unit.Dp = 4.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
            .clip(LcarsShapes.Small)
            .background(color)
    )
}

/**
 * LCARS Corner Element (decorative rounded corner pieces)
 */
@Composable
fun LcarsCorner(
    modifier: Modifier = Modifier,
    color: Color = LcarsTheme.palette.accentOrange,
    position: CornerPosition = CornerPosition.TOP_START
) {
    val shape = when (position) {
        CornerPosition.TOP_START -> LcarsShapes.RailTop
        CornerPosition.TOP_END -> LcarsShapes.RailTop
        CornerPosition.BOTTOM_START -> LcarsShapes.RailBottom
        CornerPosition.BOTTOM_END -> LcarsShapes.RailBottom
    }

    Box(
        modifier = modifier
            .size(40.dp)
            .clip(shape)
            .background(color)
    )
}

enum class CornerPosition {
    TOP_START, TOP_END, BOTTOM_START, BOTTOM_END
}
