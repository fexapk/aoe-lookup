package com.fexapk.aoelookup.fake

import com.fexapk.aoelookup.model.PlayerResponse
import com.fexapk.aoelookup.network.AoeApiService

class FakeAoeService: AoeApiService {
    override suspend fun searchPlayers(query: String): PlayerResponse {
        return FakeDataSource.getResponse()
    }
}