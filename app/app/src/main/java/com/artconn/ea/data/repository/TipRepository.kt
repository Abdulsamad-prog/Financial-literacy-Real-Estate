package com.artconn.ea.data.repository

import com.artconn.ea.data.local.dao.TipDao
import com.artconn.ea.data.local.entity.Tip
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TipRepository(private val dao: TipDao) {
    fun tips(artistId: Long): Flow<List<Tip>> = dao.getForArtist(artistId)

    fun total(artistId: Long): Flow<Int> = dao.totalForArtist(artistId).map { it ?: 0 }

    suspend fun record(tip: Tip): Long = dao.insert(tip)

    suspend fun totalSince(artistId: Long, sinceMillis: Long): Int =
        dao.totalSince(artistId, sinceMillis) ?: 0

    suspend fun countSince(artistId: Long, sinceMillis: Long): Int =
        dao.countSince(artistId, sinceMillis)
}
