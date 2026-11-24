package com.artconn.ea.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.artconn.ea.data.local.entity.Tip
import kotlinx.coroutines.flow.Flow

@Dao
interface TipDao {
    @Insert
    suspend fun insert(tip: Tip): Long

    @Query("SELECT * FROM tips WHERE artistId = :artistId ORDER BY createdAt DESC")
    fun getForArtist(artistId: Long): Flow<List<Tip>>

    @Query("SELECT SUM(amountKes) FROM tips WHERE artistId = :artistId")
    fun totalForArtist(artistId: Long): Flow<Int?>

    @Query("SELECT SUM(amountKes) FROM tips WHERE artistId = :artistId AND createdAt >= :since")
    suspend fun totalSince(artistId: Long, since: Long): Int?

    @Query("SELECT COUNT(*) FROM tips WHERE artistId = :artistId AND createdAt >= :since")
    suspend fun countSince(artistId: Long, since: Long): Int
}
