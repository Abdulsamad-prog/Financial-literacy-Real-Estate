package com.artconn.ea.data.repository

import com.artconn.ea.data.local.dao.CommentDao
import com.artconn.ea.data.local.entity.Comment
import kotlinx.coroutines.flow.Flow

class CommentRepository(private val dao: CommentDao) {
    fun comments(artworkId: Long): Flow<List<Comment>> = dao.getForArtwork(artworkId)

    fun averageRating(artworkId: Long): Flow<Double?> = dao.averageRating(artworkId)

    suspend fun add(comment: Comment): Long = dao.insert(comment)

    suspend fun delete(comment: Comment) = dao.delete(comment)
}
