package com.lcars.launcher.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.lcars.launcher.ui.theme.LcarsShapes
import com.lcars.launcher.ui.theme.LcarsTheme
import com.lcars.launcher.ui.theme.LcarsTypography

/**
 * LCARS-styled app icon with label
 */
@Composable
fun LcarsAppIcon(
    appName: String,
    appIcon: android.graphics.drawable.Drawable,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true
) {
    Column(
        modifier = modifier
            .width(80.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icon container with LCARS styling
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(LcarsShapes.Medium)
                .background(LcarsTheme.palette.panelPrimary)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                bitmap = appIcon.toBitmap().asImageBitmap(),
                contentDescription = appName,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }

        if (showLabel) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = appName.uppercase(),
                style = LcarsTypography.LabelSmall,
                color = LcarsTheme.palette.textPrimary,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * LCARS app panel (alternative horizontal layout)
 */
@Composable
fun LcarsAppPanel(
    appName: String,
    appIcon: android.graphics.drawable.Drawable,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    category: String? = null
) {
    LcarsPanel(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp),
        color = LcarsTheme.palette.panelPrimary,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // App icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(LcarsShapes.Small)
                    .background(Color.White.copy(alpha = 0.1f))
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    bitmap = appIcon.toBitmap().asImageBitmap(),
                    contentDescription = appName,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // App info
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = appName.uppercase(),
                    style = LcarsTypography.BodyMedium,
                    color = LcarsTheme.palette.textOnPanel,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (category != null) {
                    Text(
                        text = category.uppercase(),
                        style = LcarsTypography.LabelSmall,
                        color = LcarsTheme.palette.textOnPanel.copy(alpha = 0.7f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Optional category indicator
            if (category != null) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(LcarsShapes.Circle)
                        .background(LcarsTheme.palette.accentCyan)
                )
            }
        }
    }
}
