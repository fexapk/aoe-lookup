package com.fexapk.aoelookup.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fexapk.aoelookup.data.NetworkPlayerRepository
import com.fexapk.aoelookup.data.PlayerRepository
import com.fexapk.aoelookup.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

enum class UiState {
    SUCCESS,
    ERROR,
    LOADING,
    HOME
}

class AoeViewModel() : ViewModel() {

    private val playerRepository: PlayerRepository = NetworkPlayerRepository

    var uiState: UiState by mutableStateOf(UiState.HOME)

    private val _players: MutableStateFlow<List<Player>> = MutableStateFlow(emptyList())
    val players: StateFlow<List<Player>> get() = _players.asStateFlow()

     fun searchPlayers(username: String) {
        viewModelScope.launch {
            uiState = UiState.LOADING
            try {
                _players.value = playerRepository.searchPlayers(username)
                uiState = UiState.SUCCESS
                Log.d("Aoe", "player searched")
            } catch (e: IOException) {
                uiState = UiState.ERROR
                Log.e("Aoe", "error")
            } catch (e: HttpException) {
                uiState = UiState.ERROR
                Log.e("Aoe", "error")
            }
        }

    }
}