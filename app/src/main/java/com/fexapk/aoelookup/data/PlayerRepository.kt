package com.fexapk.aoelookup.data

import com.fexapk.aoelookup.model.Player
import com.fexapk.aoelookup.network.AoeApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface PlayerRepository {
    suspend fun searchPlayers(name: String): List<Player>
}

object NetworkPlayerRepository: PlayerRepository {

    private val baseUrl = "https://aoe4world.com/api/v0/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService: AoeApiService by lazy {
        retrofit.create(AoeApiService::class.java)
    }

    override suspend fun searchPlayers(name: String): List<Player> {
        return retrofitService.searchPlayers(name).players
    }

}