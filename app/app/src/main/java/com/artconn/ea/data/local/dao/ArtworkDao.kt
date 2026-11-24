package com.artconn.ea.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.artconn.ea.data.local.entity.Artwork
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtworkDao {
    @Insert
    suspend fun insert(artwork: Artwork): Long

    @Update
    suspend fun update(artwork: Artwork)

    @Delete
    suspend fun delete(artwork: Artwork)

    @Query("SELECT * FROM artworks ORDER BY createdAt DESC")
    fun getAll(): Flow<List<Artwork>>

    @Query("SELECT * FROM artworks WHERE id = :id")
    fun getById(id: Long): Flow<Artwork?>

    @Query(
        "SELECT COUNT(*) FROM artworks " +
            "WHERE artistId = :artistId AND LOWER(title) = LOWER(:title) AND id != :excludeId"
    )
    suspend fun countSameTitle(artistId: Long, title: String, excludeId: Long): Int
}
