package com.artconn.ea.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "artworks",
    foreignKeys = [
        ForeignKey(
            entity = Artist::class,
            parentColumns = ["id"],
            childColumns = ["artistId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("artistId")]
)
data class Artwork(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val artistId: Long,
    val title: String,
    val description: String,
    val medium: String,
    val eventName: String? = null,
    val imagePath: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
