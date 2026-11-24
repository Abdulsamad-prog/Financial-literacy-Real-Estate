package com.artconn.ea.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.artconn.ea.ArtConnApp
import com.artconn.ea.data.local.entity.Artwork
import com.artconn.ea.data.repository.ArtworkRepository
import com.artconn.ea.data.session.Roles
import com.artconn.ea.data.session.SessionManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed interface HomeUiState {
    object Loading : HomeUiState
    object Empty : HomeUiState
    data class Success(val artworks: List<Artwork>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}

class HomeViewModel(
    artworkRepository: ArtworkRepository,
    session: SessionManager
) : ViewModel() {

    val isArtist: Boolean = session.role == Roles.ARTIST

    val uiState: StateFlow<HomeUiState> = artworkRepository.artworks
        .map<List<Artwork>, HomeUiState> { list ->
            if (list.isEmpty()) HomeUiState.Empty else HomeUiState.Success(list)
        }
        .catch { error -> emit(HomeUiState.Error(error.message ?: "Something went wrong")) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeUiState.Loading)

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ArtConnApp
                HomeViewModel(app.container.artworkRepository, app.container.sessionManager)
            }
        }
    }
}
