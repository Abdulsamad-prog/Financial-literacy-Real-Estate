package com.artconn.ea.data.repository

import com.artconn.ea.data.local.dao.ArtworkDao
import com.artconn.ea.data.local.entity.Artwork
import kotlinx.coroutines.flow.Flow

class ArtworkRepository(private val dao: ArtworkDao) {
    val artworks: Flow<List<Artwork>> = dao.getAll()

    fun artwork(id: Long): Flow<Artwork?> = dao.getById(id)

    suspend fun add(artwork: Artwork): Long = dao.insert(artwork)

    suspend fun update(artwork: Artwork) = dao.update(artwork)

    suspend fun delete(artwork: Artwork) = dao.delete(artwork)

    suspend fun titleExists(artistId: Long, title: String, excludeId: Long): Boolean =
        dao.countSameTitle(artistId, title.trim(), excludeId) > 0
}
