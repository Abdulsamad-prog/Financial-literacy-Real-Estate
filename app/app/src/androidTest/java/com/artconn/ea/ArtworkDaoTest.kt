package com.artconn.ea

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.artconn.ea.data.local.AppDatabase
import com.artconn.ea.data.local.entity.Artist
import com.artconn.ea.data.local.entity.Artwork
import com.artconn.ea.data.local.entity.Comment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArtworkDaoTest {
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
    }

    @After
    fun tearDown() {
        db.close()
    }

    private suspend fun newArtist(): Long =
        db.artistDao().insert(Artist(name = "Amina", phone = "0712345678"))

    private fun artwork(artistId: Long, title: String) =
        Artwork(artistId = artistId, title = title, description = "Oil on canvas", medium = "Painting")

    @Test
    fun insertThenRead() {
        runBlocking {
            val artistId = newArtist()
            val id = db.artworkDao().insert(artwork(artistId, "Sunset"))
            val list = db.artworkDao().getAll().first()
            assertEquals(1, list.size)
            assertEquals(id, list[0].id)
            assertEquals("Sunset", list[0].title)
        }
    }

    @Test
    fun updateChangesTheTitle() {
        runBlocking {
            val artistId = newArtist()
            val id = db.artworkDao().insert(artwork(artistId, "Sunset"))
            val saved = db.artworkDao().getById(id).first()!!
            db.artworkDao().update(saved.copy(title = "Sunrise"))
            assertEquals("Sunrise", db.artworkDao().getById(id).first()!!.title)
        }
    }

    @Test
    fun deleteRemovesTheArtworkAndItsComments() {
        runBlocking {
            val artistId = newArtist()
            val id = db.artworkDao().insert(artwork(artistId, "Sunset"))
            db.commentDao().insert(Comment(artworkId = id, authorName = "Joe", text = "Lovely", rating = 5))
            val saved = db.artworkDao().getById(id).first()!!
            db.artworkDao().delete(saved)
            assertTrue(db.artworkDao().getAll().first().isEmpty())
            assertTrue(db.commentDao().getForArtwork(id).first().isEmpty())
        }
    }

    @Test
    fun duplicateTitleIgnoresCapitalLetters() {
        runBlocking {
            val artistId = newArtist()
            val id = db.artworkDao().insert(artwork(artistId, "Sunset"))
            assertTrue(db.artworkDao().countSameTitle(artistId, "SUNSET", 0L) > 0)
            assertFalse(db.artworkDao().countSameTitle(artistId, "SUNSET", id) > 0)
        }
    }
}
