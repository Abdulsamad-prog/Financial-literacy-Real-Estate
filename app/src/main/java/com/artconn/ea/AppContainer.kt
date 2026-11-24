package com.artconn.ea

import android.content.Context
import com.artconn.ea.data.local.AppDatabase
import com.artconn.ea.data.repository.ArtistRepository
import com.artconn.ea.data.repository.ArtworkRepository
import com.artconn.ea.data.repository.CommentRepository
import com.artconn.ea.data.repository.TipRepository
import com.artconn.ea.data.session.SessionManager

/** Builds the database and repositories once, so every screen shares the same ones. */
class AppContainer(context: Context) {
    private val database = AppDatabase.getInstance(context)

    val sessionManager = SessionManager(context)
    val artistRepository = ArtistRepository(database.artistDao())
    val artworkRepository = ArtworkRepository(database.artworkDao())
    val commentRepository = CommentRepository(database.commentDao())
    val tipRepository = TipRepository(database.tipDao())
}
