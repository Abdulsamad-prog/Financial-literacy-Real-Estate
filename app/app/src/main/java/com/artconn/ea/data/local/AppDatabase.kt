package com.artconn.ea.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.artconn.ea.data.local.dao.ArtistDao
import com.artconn.ea.data.local.dao.ArtworkDao
import com.artconn.ea.data.local.dao.CommentDao
import com.artconn.ea.data.local.dao.TipDao
import com.artconn.ea.data.local.entity.Artist
import com.artconn.ea.data.local.entity.Artwork
import com.artconn.ea.data.local.entity.Comment
import com.artconn.ea.data.local.entity.Tip

@Database(
    entities = [Artist::class, Artwork::class, Comment::class, Tip::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
    abstract fun artworkDao(): ArtworkDao
    abstract fun commentDao(): CommentDao
    abstract fun tipDao(): TipDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "artconn.db"
                ).fallbackToDestructiveMigration().build().also { instance = it }
            }
        }
    }
}
