package com.lcars.launcher.data.local.dao

import androidx.room.*
import com.lcars.launcher.data.local.entities.VoiceCommandEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VoiceCommandDao {
    @Query("SELECT * FROM voice_commands WHERE enabled = 1")
    fun getEnabledCommands(): Flow<List<VoiceCommandEntity>>

    @Query("SELECT * FROM voice_commands")
    fun getAllCommands(): Flow<List<VoiceCommandEntity>>

    @Query("SELECT * FROM voice_commands WHERE phrase LIKE '%' || :query || '%' AND enabled = 1")
    suspend fun searchCommands(query: String): List<VoiceCommandEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommand(command: VoiceCommandEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommands(commands: List<VoiceCommandEntity>)

    @Update
    suspend fun updateCommand(command: VoiceCommandEntity)

    @Delete
    suspend fun deleteCommand(command: VoiceCommandEntity)

    @Query("DELETE FROM voice_commands WHERE id = :commandId")
    suspend fun deleteCommandById(commandId: String)
}
