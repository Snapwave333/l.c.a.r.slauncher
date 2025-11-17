package com.lcars.launcher.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lcars.launcher.data.local.dao.*
import com.lcars.launcher.data.local.entities.*

/**
 * Main Room database for LCARS Launcher
 */
@Database(
    entities = [
        DeckEntity::class,
        ProfileEntity::class,
        PanelConfigEntity::class,
        GestureMappingEntity::class,
        VoiceCommandEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LcarsDatabase : RoomDatabase() {
    abstract fun deckDao(): DeckDao
    abstract fun profileDao(): ProfileDao
    abstract fun panelConfigDao(): PanelConfigDao
    abstract fun gestureMappingDao(): GestureMappingDao
    abstract fun voiceCommandDao(): VoiceCommandDao

    companion object {
        const val DATABASE_NAME = "lcars_launcher.db"
    }
}
