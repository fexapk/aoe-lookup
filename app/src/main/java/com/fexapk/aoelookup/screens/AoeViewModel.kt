package com.fexapk.aoelookup.screens

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

enum class UiState {
    SUCCESS,
    ERROR,
    LOADING,
    HOME
}

class AoeViewModel : ViewModel() {

    private val playerRepository: PlayerRepository = NetworkPlayerRepository
    private var searchJob: Job? = null

    var uiState: UiState by mutableStateOf(UiState.HOME)

    var players: List<Player> = emptyList()
        private set


     fun searchPlayers(username: String) {
         searchJob?.cancel()
         searchJob = viewModelScope.launch {
            uiState = UiState.LOADING
             try {
                 delay(300) // Debounce time
                 val players = playerRepository.searchPlayers(username)
                 if (isActive) { // Ensures coroutine is still active
                     this@AoeViewModel.players = players
                     uiState = UiState.SUCCESS
                 }
             } catch (e: IOException) {
                 uiState = UiState.ERROR
             } catch (e: HttpException) {
                 uiState = UiState.ERROR
             }
         }
    }
}