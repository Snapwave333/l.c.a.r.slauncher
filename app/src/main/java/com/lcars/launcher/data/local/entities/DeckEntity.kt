package com.lcars.launcher.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity for storing deck configurations
 */
@Entity(tableName = "decks")
data class DeckEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val displayOrder: Int,
    val layoutJson: String,  // JSON serialized list of panel configurations
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
