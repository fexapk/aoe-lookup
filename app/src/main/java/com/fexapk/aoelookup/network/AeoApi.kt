package com.fexapk.aoelookup.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("players/search")
    suspend fun searchPlayers(@Query("query") query: String): List<Player>
}
