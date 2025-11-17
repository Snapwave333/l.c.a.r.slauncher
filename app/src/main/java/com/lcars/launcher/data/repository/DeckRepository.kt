package com.lcars.launcher.data.repository

import com.lcars.launcher.data.local.dao.DeckDao
import com.lcars.launcher.data.local.dao.PanelConfigDao
import com.lcars.launcher.data.local.entities.DeckEntity
import com.lcars.launcher.data.local.entities.PanelConfigEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing decks and panels
 */
@Singleton
class DeckRepository @Inject constructor(
    private val deckDao: DeckDao,
    private val panelConfigDao: PanelConfigDao
) {
    /**
     * Get all decks ordered by display order
     */
    fun getAllDecks(): Flow<List<DeckEntity>> = deckDao.getAllDecks()

    /**
     * Get a specific deck by ID
     */
    suspend fun getDeckById(deckId: String): DeckEntity? = deckDao.getDeckById(deckId)

    /**
     * Get deck by ID as Flow
     */
    fun getDeckByIdFlow(deckId: String): Flow<DeckEntity?> = deckDao.getDeckByIdFlow(deckId)

    /**
     * Create a new deck
     */
    suspend fun createDeck(name: String, layoutJson: String = "[]"): DeckEntity {
        val count = deckDao.getDecksCount()
        val deck = DeckEntity(
            id = UUID.randomUUID().toString(),
            name = name,
            displayOrder = count,
            layoutJson = layoutJson
        )
        deckDao.insertDeck(deck)
        return deck
    }

    /**
     * Update an existing deck
     */
    suspend fun updateDeck(deck: DeckEntity) {
        deckDao.updateDeck(deck.copy(updatedAt = System.currentTimeMillis()))
    }

    /**
     * Delete a deck
     */
    suspend fun deleteDeck(deckId: String) {
        deckDao.deleteDeckById(deckId)
        // Also delete associated panels
        panelConfigDao.deletePanelsByDeckId(deckId)
    }

    /**
     * Get total decks count
     */
    suspend fun getDecksCount(): Int = deckDao.getDecksCount()

    /**
     * Initialize default decks if none exist
     */
    suspend fun initializeDefaultDecks() {
        if (getDecksCount() == 0) {
            val defaultDecks = listOf(
                DeckEntity(
                    id = "deck_1",
                    name = "Main Deck",
                    displayOrder = 0,
                    layoutJson = "[]"
                ),
                DeckEntity(
                    id = "deck_2",
                    name = "Secondary Deck",
                    displayOrder = 1,
                    layoutJson = "[]"
                ),
                DeckEntity(
                    id = "deck_3",
                    name = "Utility Deck",
                    displayOrder = 2,
                    layoutJson = "[]"
                )
            )
            deckDao.insertDecks(defaultDecks)
        }
    }

    /**
     * Get panels for a deck
     */
    fun getPanelsForDeck(deckId: String): Flow<List<PanelConfigEntity>> {
        return panelConfigDao.getPanelsByDeckId(deckId)
    }

    /**
     * Add panel to deck
     */
    suspend fun addPanel(panel: PanelConfigEntity) {
        panelConfigDao.insertPanel(panel)
    }

    /**
     * Update panel
     */
    suspend fun updatePanel(panel: PanelConfigEntity) {
        panelConfigDao.updatePanel(panel)
    }

    /**
     * Delete panel
     */
    suspend fun deletePanel(panelId: String) {
        panelConfigDao.deletePanelById(panelId)
    }
}
