package com.lcars.launcher.utils

import java.util.Calendar
import java.util.Date
import kotlin.math.floor

/**
 * Stardate calculator for LCARS displays
 * Uses TNG-era stardate calculation
 */
object StardateCalculator {

    // Base date: January 1, 2323 (Star Trek TNG era)
    private const val BASE_YEAR = 2323
    private const val CURRENT_YEAR_OFFSET = 2024 - BASE_YEAR

    /**
     * Calculate current stardate
     * Format: XXXXX.X
     */
    fun getCurrentStardate(): String {
        val calendar = Calendar.getInstance()
        return calculateStardate(calendar.time)
    }

    /**
     * Calculate stardate for a specific date
     */
    fun calculateStardate(date: Date): String {
        val calendar = Calendar.getInstance().apply {
            time = date
        }

        val year = calendar.get(Calendar.YEAR)
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        // Calculate year portion (5 digits)
        val stardateYear = (year - BASE_YEAR) * 1000

        // Calculate day portion (within the year)
        val daysInYear = calendar.getActualMaximum(Calendar.DAY_OF_YEAR)
        val dayProgress = (dayOfYear.toFloat() / daysInYear.toFloat()) * 1000

        // Calculate time of day portion (decimal)
        val timeProgress = ((hour * 60 + minute).toFloat() / (24 * 60).toFloat()) * 10

        val stardate = stardateYear + dayProgress + timeProgress

        return String.format("%.1f", stardate)
    }

    /**
     * Get formatted stardate with prefix
     */
    fun getFormattedStardate(): String {
        return "SD ${getCurrentStardate()}"
    }

    /**
     * Get TNG-style stardate (for current era)
     */
    fun getTNGStyleStardate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)

        // TNG era stardates are in the 40000-50000 range
        // Map current dates to that range
        val baseStardate = 41000.0
        val yearsSince2020 = year - 2020

        val yearPortion = yearsSince2020 * 1000.0
        val dayPortion = (dayOfYear / 365.25) * 1000.0

        val stardate = baseStardate + yearPortion + dayPortion

        return String.format("%.1f", stardate)
    }

    /**
     * Get sector (random or based on location if available)
     */
    fun getCurrentSector(): String {
        val sectors = listOf(
            "Alpha Quadrant",
            "Beta Quadrant",
            "Sector 001",
            "Sol System",
            "Sector 030",
            "Neutral Zone"
        )

        // For now, return based on day of year for consistency
        val calendar = Calendar.getInstance()
        val index = calendar.get(Calendar.DAY_OF_YEAR) % sectors.size

        return sectors[index]
    }
}
