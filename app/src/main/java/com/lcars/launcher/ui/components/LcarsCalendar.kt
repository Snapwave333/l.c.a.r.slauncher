package com.lcars.launcher.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lcars.launcher.ui.theme.LcarsTheme
import java.text.SimpleDateFormat
import java.util.*

/**
 * Mini calendar/agenda panel
 */
@Composable
fun LcarsCalendar(
    modifier: Modifier = Modifier
) {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault())
    val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.time)
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val month = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)
    val year = calendar.get(Calendar.YEAR)

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = dayOfWeek.uppercase(),
            style = LcarsTheme.typography.title,
            color = LcarsTheme.palette.accentOrange
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = dayOfMonth.toString(),
                style = LcarsTheme.typography.display,
                color = LcarsTheme.palette.textPrimary
            )

            Column {
                Text(
                    text = month.uppercase(),
                    style = LcarsTheme.typography.body,
                    color = LcarsTheme.palette.textPrimary
                )
                Text(
                    text = year.toString(),
                    style = LcarsTheme.typography.label,
                    color = LcarsTheme.palette.textSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Week view (simplified)
        val weekDays = listOf("M", "T", "W", "T", "F", "S", "S")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            weekDays.forEachIndexed { index, day ->
                val isToday = index == (calendar.get(Calendar.DAY_OF_WEEK) - 2 + 7) % 7
                LcarsPanel(
                    label = day,
                    color = if (isToday) LcarsTheme.palette.accentOrange else LcarsTheme.palette.backgroundSecondary,
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp)
                        .height(32.dp)
                )
            }
        }
    }
}
