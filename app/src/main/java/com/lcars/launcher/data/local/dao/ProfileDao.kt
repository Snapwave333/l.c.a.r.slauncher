package com.lcars.launcher.data.local.dao

import androidx.room.*
import com.lcars.launcher.data.local.entities.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profiles")
    fun getAllProfiles(): Flow<List<ProfileEntity>>

    @Query("SELECT * FROM profiles WHERE id = :profileId")
    suspend fun getProfileById(profileId: String): ProfileEntity?

    @Query("SELECT * FROM profiles WHERE id = :profileId")
    fun getProfileByIdFlow(profileId: String): Flow<ProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfiles(profiles: List<ProfileEntity>)

    @Update
    suspend fun updateProfile(profile: ProfileEntity)

    @Delete
    suspend fun deleteProfile(profile: ProfileEntity)

    @Query("DELETE FROM profiles WHERE id = :profileId")
    suspend fun deleteProfileById(profileId: String)

    @Query("SELECT COUNT(*) FROM profiles")
    suspend fun getProfilesCount(): Int
}
