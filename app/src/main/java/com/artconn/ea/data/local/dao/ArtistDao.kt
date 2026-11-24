package com.artconn.ea.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.artconn.ea.data.local.entity.Artist
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {
    @Insert
    suspend fun insert(artist: Artist): Long

    @Update
    suspend fun update(artist: Artist)

    @Query("SELECT * FROM artists WHERE id = :id")
    fun getById(id: Long): Flow<Artist?>
}
