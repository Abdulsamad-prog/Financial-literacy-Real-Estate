package com.artconn.ea.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.SwitchAccount
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.artconn.ea.ui.components.NotificationPermissionRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onArtworkClick: (Long) -> Unit,
    onAddClick: () -> Unit,
    onScanClick: () -> Unit,
    onEarningsClick: () -> Unit,
    onSwitchRole: () -> Unit,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (viewModel.isArtist) {
        NotificationPermissionRequest()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ArtConn EA") },
                actions = {
                    IconButton(onClick = onScanClick) {
                        Icon(Icons.Default.QrCodeScanner, contentDescription = "Scan QR")
                    }
                    if (viewModel.isArtist) {
                        IconButton(onClick = onEarningsClick) {
                            Icon(Icons.Default.AccountBalanceWallet, contentDescription = "Earnings")
                        }
                    }
                    IconButton(onClick = onSwitchRole) {
                        Icon(Icons.Default.SwitchAccount, contentDescription = "Switch role")
                    }
                }
            )
        },
        floatingActionButton = {
            if (viewModel.isArtist) {
                FloatingActionButton(onClick = onAddClick) {
                    Icon(Icons.Default.Add, contentDescription = "Add artwork")
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (val current = state) {
                HomeUiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
                HomeUiState.Empty -> Text(
                    text = if (viewModel.isArtist) {
                        "No artworks yet. Tap + to add your first one."
                    } else {
                        "No artworks yet."
                    },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(24.dp)
                )
                is HomeUiState.Error -> Text(
                    text = "Something went wrong: ${current.message}",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(24.dp)
                )
                is HomeUiState.Success -> LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(current.artworks, key = { it.id }) { artwork ->
                        ArtworkCard(artwork = artwork, onClick = { onArtworkClick(artwork.id) })
                    }
                }
            }
        }
    }
}
