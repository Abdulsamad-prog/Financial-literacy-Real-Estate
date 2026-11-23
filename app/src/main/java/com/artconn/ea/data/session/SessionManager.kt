package com.artconn.ea.data.session

import android.content.Context

object Roles {
    const val ARTIST = "ARTIST"
    const val VIEWER = "VIEWER"
}

/** Remembers who is using the app (Artist or Viewer) even after the app is closed. */
class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    var role: String?
        get() = prefs.getString("role", null)
        set(value) {
            prefs.edit().putString("role", value).apply()
        }

    var artistId: Long
        get() = prefs.getLong("artistId", -1L)
        set(value) {
            prefs.edit().putLong("artistId", value).apply()
        }

    var displayName: String
        get() = prefs.getString("displayName", "") ?: ""
        set(value) {
            prefs.edit().putString("displayName", value).apply()
        }
}
