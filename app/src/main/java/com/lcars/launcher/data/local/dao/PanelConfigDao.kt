package com.lcars.launcher.data.local.dao

import androidx.room.*
import com.lcars.launcher.data.local.entities.PanelConfigEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PanelConfigDao {
    @Query("SELECT * FROM panel_configs WHERE deckId = :deckId ORDER BY position ASC")
    fun getPanelsByDeckId(deckId: String): Flow<List<PanelConfigEntity>>

    @Query("SELECT * FROM panel_configs WHERE id = :panelId")
    suspend fun getPanelById(panelId: String): PanelConfigEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPanel(panel: PanelConfigEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPanels(panels: List<PanelConfigEntity>)

    @Update
    suspend fun updatePanel(panel: PanelConfigEntity)

    @Delete
    suspend fun deletePanel(panel: PanelConfigEntity)

    @Query("DELETE FROM panel_configs WHERE deckId = :deckId")
    suspend fun deletePanelsByDeckId(deckId: String)

    @Query("DELETE FROM panel_configs WHERE id = :panelId")
    suspend fun deletePanelById(panelId: String)
}
