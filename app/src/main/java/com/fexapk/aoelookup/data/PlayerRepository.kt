package com.fexapk.aoelookup.data

import com.fexapk.aoelookup.model.Player
import com.fexapk.aoelookup.network.AoeApiService

interface PlayerRepository {
    suspend fun searchPlayers(name: String): List<Player>
}

class NetworkPlayerRepository(
    val retrofitService: AoeApiService
) : PlayerRepository {

    override suspend fun searchPlayers(name: String): List<Player> {
        return retrofitService.searchPlayers(name).players
    }

}