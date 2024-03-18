package com.fexapk.aoelookup.data

import com.fexapk.aoelookup.model.Player
import com.fexapk.aoelookup.network.AoeApiService

interface PlayerRepository {
    suspend fun searchPlayers(name: String): List<Player>
}

class NetworkPlayerRepository(
    val service: AoeApiService
) : PlayerRepository {

    override suspend fun searchPlayers(name: String): List<Player> {
        return service.searchPlayers(name).players
    }

}