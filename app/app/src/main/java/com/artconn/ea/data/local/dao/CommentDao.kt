package com.artconn.ea.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.artconn.ea.data.local.entity.Comment
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Insert
    suspend fun insert(comment: Comment): Long

    @Delete
    suspend fun delete(comment: Comment)

    @Query("SELECT * FROM comments WHERE artworkId = :artworkId ORDER BY createdAt DESC")
    fun getForArtwork(artworkId: Long): Flow<List<Comment>>

    @Query("SELECT AVG(rating) FROM comments WHERE artworkId = :artworkId")
    fun averageRating(artworkId: Long): Flow<Double?>
}
