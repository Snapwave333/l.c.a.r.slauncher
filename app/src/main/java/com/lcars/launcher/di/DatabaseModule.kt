package com.lcars.launcher.di

import android.content.Context
import androidx.room.Room
import com.lcars.launcher.data.local.LcarsDatabase
import com.lcars.launcher.data.local.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLcarsDatabase(
        @ApplicationContext context: Context
    ): LcarsDatabase {
        return Room.databaseBuilder(
            context,
            LcarsDatabase::class.java,
            LcarsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDeckDao(database: LcarsDatabase): DeckDao {
        return database.deckDao()
    }

    @Provides
    fun provideProfileDao(database: LcarsDatabase): ProfileDao {
        return database.profileDao()
    }

    @Provides
    fun providePanelConfigDao(database: LcarsDatabase): PanelConfigDao {
        return database.panelConfigDao()
    }

    @Provides
    fun provideGestureMappingDao(database: LcarsDatabase): GestureMappingDao {
        return database.gestureMappingDao()
    }

    @Provides
    fun provideVoiceCommandDao(database: LcarsDatabase): VoiceCommandDao {
        return database.voiceCommandDao()
    }
}
