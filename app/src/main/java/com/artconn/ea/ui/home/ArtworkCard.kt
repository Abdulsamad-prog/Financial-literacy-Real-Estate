package com.artconn.ea.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artconn.ea.data.local.entity.Artwork
import com.artconn.ea.ui.components.ArtImage

@Composable
fun ArtworkCard(artwork: Artwork, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column {
            ArtImage(
                path = artwork.imagePath,
                contentDescription = artwork.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(artwork.title, style = MaterialTheme.typography.titleMedium)
                Text(artwork.medium, style = MaterialTheme.typography.bodyMedium)
                val event = artwork.eventName
                if (event != null) {
                    Text("Event: $event", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
