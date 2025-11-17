package com.lcars.launcher.di

import android.content.Context
import com.lcars.launcher.data.preferences.LcarsPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLcarsPreferences(
        @ApplicationContext context: Context
    ): LcarsPreferences {
        return LcarsPreferences(context)
    }
}
