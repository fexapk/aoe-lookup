package com.fexapk.aoelookup.fake

import com.fexapk.aoelookup.data.PlayerRepository
import com.fexapk.aoelookup.model.Player

class FakeNetworkPlayerRepository(): PlayerRepository {
    override suspend fun searchPlayers(name: String): List<Player> {
        return FakeDataSource.getResponse().players
    }
}