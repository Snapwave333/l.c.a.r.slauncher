package com.lcars.launcher.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lcars.launcher.services.MediaState
import com.lcars.launcher.ui.theme.LcarsTheme

/**
 * Media controls panel for playback
 */
@Composable
fun LcarsMediaControls(
    mediaState: MediaState,
    onPlayPause: () -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (!mediaState.hasActiveSession) {
        LcarsPanel(
            label = "NO ACTIVE MEDIA",
            color = LcarsTheme.palette.backgroundSecondary,
            modifier = modifier.height(120.dp)
        )
        return
    }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Track info
        Column {
            Text(
                text = mediaState.trackTitle,
                style = LcarsTheme.typography.body,
                color = LcarsTheme.palette.textPrimary,
                maxLines = 1
            )
            Text(
                text = mediaState.artist,
                style = LcarsTheme.typography.label,
                color = LcarsTheme.palette.textSecondary,
                maxLines = 1
            )
        }

        // Control buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LcarsButton(
                label = "◄◄",
                onClick = onPrevious,
                color = LcarsTheme.palette.accentCyan,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            LcarsButton(
                label = if (mediaState.isPlaying) "❚❚" else "►",
                onClick = onPlayPause,
                color = LcarsTheme.palette.accentOrange,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            LcarsButton(
                label = "►►",
                onClick = onNext,
                color = LcarsTheme.palette.accentCyan,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
