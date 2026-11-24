package com.artconn.ea.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "comments",
    foreignKeys = [
        ForeignKey(
            entity = Artwork::class,
            parentColumns = ["id"],
            childColumns = ["artworkId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("artworkId")]
)
data class Comment(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val artworkId: Long,
    val authorName: String,
    val text: String,
    val rating: Int,
    val createdAt: Long = System.currentTimeMillis()
)
