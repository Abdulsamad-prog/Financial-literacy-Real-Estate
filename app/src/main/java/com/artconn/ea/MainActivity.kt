package com.artconn.ea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artconn.ea.ui.theme.ArtConnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtConnTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Text(text = "ArtConn EA base project is running", modifier = Modifier.padding(24.dp))
                }
            }
        }
    }
}
