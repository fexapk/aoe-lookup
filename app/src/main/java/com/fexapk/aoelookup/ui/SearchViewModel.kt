package com.fexapk.aoelookup.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fexapk.aoelookup.data.NetworkPlayerRepository
import com.fexapk.aoelookup.data.PlayerRepository
import com.fexapk.aoelookup.model.Player
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
enum class UiState {
    SUCCESS,
    ERROR,
    LOADING,
    HOME
}
**/

sealed interface UiState {
    data class Success(val players: List<Player>) : UiState

    // data class PlayerFocus(val player: Player) : UiState
    object Error : UiState
    object Loading : UiState
    object Home : UiState
}

class SearchViewModel : ViewModel() {

    private val playerRepository: PlayerRepository = NetworkPlayerRepository
    private var searchJob: Job? = null

    var uiState: UiState by mutableStateOf(UiState.Home)
    lateinit var selectedPlayer: Player

     fun searchPlayers(username: String) {
         searchJob?.cancel()
         if (username.isBlank()) {
             uiState = UiState.Home
             return
         }
         searchJob = viewModelScope.launch {
            uiState = UiState.Loading
             try {
                 delay(300) // Debounce time
                 val players = playerRepository.searchPlayers(username)
                 if (isActive) { // Ensures coroutine is still active layers
                     uiState = UiState.Success(players)
                 }
             } catch (e: IOException) {
                 uiState = UiState.Error
             } catch (e: HttpException) {
                 uiState = UiState.Error
             }
         }
    }

    /**
    fun focusPlayer(player: Player) {
       uiState = UiState.PlayerFocus(player)
    }
    **/

}