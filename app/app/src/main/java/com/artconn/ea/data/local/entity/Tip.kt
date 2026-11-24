package com.artconn.ea.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

// No foreign key on purpose: tips already earned must survive deleting an artwork.
@Entity(tableName = "tips", indices = [Index("artistId")])
data class Tip(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val artistId: Long,
    val artworkId: Long,
    val artworkTitle: String,
    val amountKes: Int,
    val payerPhone: String,
    val mpesaRef: String,
    val createdAt: Long = System.currentTimeMillis()
)
