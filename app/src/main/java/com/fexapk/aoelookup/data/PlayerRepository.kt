package com.fexapk.aoelookup.data

interface PlayerRepository {
    suspend fun getPlayers(name: String): List<Player>
}

class NetworkPlayerRepository(): PlayerRepository {
    override suspend fun getPlayers(name: String): List<Player> {
        return AoeApi.retrofitService.getPlayers()
    }
}