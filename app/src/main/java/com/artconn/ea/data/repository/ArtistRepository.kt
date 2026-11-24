package com.artconn.ea.data.repository

import com.artconn.ea.data.local.dao.ArtistDao
import com.artconn.ea.data.local.entity.Artist
import kotlinx.coroutines.flow.Flow

class ArtistRepository(private val dao: ArtistDao) {
    suspend fun create(artist: Artist): Long = dao.insert(artist)

    fun artist(id: Long): Flow<Artist?> = dao.getById(id)

    suspend fun update(artist: Artist) = dao.update(artist)
}
