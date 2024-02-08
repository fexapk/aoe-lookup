package com.fexapk.aoelookup.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fexapk.aoelookup.data.PlayerRepository
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.fexapk.aoelookup.AoeLookupApplication

class AoeViewModel(private val playerRepository: PlayerRepository) : ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AoeLookupApplication)
                val playerRepository = application.container.playerRepository
                AoeViewModel(playerRepository = playerRepository)
            }
        }
    }
}