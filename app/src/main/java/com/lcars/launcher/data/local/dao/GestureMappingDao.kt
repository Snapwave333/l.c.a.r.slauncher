package com.lcars.launcher.data.local.dao

import androidx.room.*
import com.lcars.launcher.data.local.entities.GestureMappingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GestureMappingDao {
    @Query("SELECT * FROM gesture_mappings WHERE enabled = 1")
    fun getEnabledGestureMappings(): Flow<List<GestureMappingEntity>>

    @Query("SELECT * FROM gesture_mappings")
    fun getAllGestureMappings(): Flow<List<GestureMappingEntity>>

    @Query("SELECT * FROM gesture_mappings WHERE gestureType = :gestureType AND enabled = 1")
    suspend fun getMappingByGestureType(gestureType: String): GestureMappingEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMapping(mapping: GestureMappingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMappings(mappings: List<GestureMappingEntity>)

    @Update
    suspend fun updateMapping(mapping: GestureMappingEntity)

    @Delete
    suspend fun deleteMapping(mapping: GestureMappingEntity)
}
