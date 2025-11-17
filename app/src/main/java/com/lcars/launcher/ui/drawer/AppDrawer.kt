package com.lcars.launcher.ui.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lcars.launcher.data.models.AppInfo
import com.lcars.launcher.ui.components.LcarsAppPanel
import com.lcars.launcher.ui.components.LcarsPanel
import com.lcars.launcher.ui.theme.LcarsShapes
import com.lcars.launcher.ui.theme.LcarsTheme
import com.lcars.launcher.ui.theme.LcarsTypography

/**
 * LCARS-styled app drawer
 */
@Composable
fun AppDrawer(
    apps: List<AppInfo>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onAppClick: (AppInfo) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val filteredApps = remember(apps, searchQuery) {
        if (searchQuery.isBlank()) {
            apps
        } else {
            apps.filter { app ->
                app.appName.contains(searchQuery, ignoreCase = true) ||
                        app.packageName.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LcarsTheme.palette.background)
            .padding(16.dp)
    ) {
        // Header with close button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LcarsPanel(
                label = "APPLICATIONS",
                color = LcarsTheme.palette.accentOrange,
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            LcarsPanel(
                label = "CLOSE",
                color = LcarsTheme.palette.alertRed,
                onClick = onClose,
                modifier = Modifier
                    .width(100.dp)
                    .height(64.dp)
            )
        }

        // Search bar
        SearchBar(
            query = searchQuery,
            onQueryChange = onSearchQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // App count
        Text(
            text = "${filteredApps.size} APPS",
            style = LcarsTypography.LabelMedium,
            color = LcarsTheme.palette.textSecondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Apps list
        if (filteredApps.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LcarsPanel(
                    label = "NO APPS FOUND",
                    color = LcarsTheme.palette.backgroundSecondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredApps, key = { it.packageName }) { app ->
                    LcarsAppPanel(
                        appName = app.appName,
                        appIcon = app.icon,
                        onClick = { onAppClick(app) },
                        category = app.category
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .clip(LcarsShapes.Medium)
            .background(LcarsTheme.palette.panelPrimary)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = LcarsTheme.palette.textOnPanel,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.weight(1f),
            textStyle = LcarsTypography.BodyMedium.copy(
                color = LcarsTheme.palette.textOnPanel
            ),
            cursorBrush = SolidColor(LcarsTheme.palette.textOnPanel),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (query.isEmpty()) {
                    Text(
                        text = "SEARCH APPLICATIONS...",
                        style = LcarsTypography.BodyMedium,
                        color = LcarsTheme.palette.textOnPanel.copy(alpha = 0.5f)
                    )
                }
                innerTextField()
            }
        )

        if (query.isNotEmpty()) {
            IconButton(
                onClick = { onQueryChange("") },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Clear",
                    tint = LcarsTheme.palette.textOnPanel
                )
            }
        }
    }
}
