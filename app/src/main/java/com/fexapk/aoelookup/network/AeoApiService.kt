package com.fexapk.aoelookup.network

import com.fexapk.aoelookup.model.PlayerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AoeApiService {
    @GET("players/search")
    suspend fun searchPlayers(@Query("query") query: String): PlayerResponse
}
