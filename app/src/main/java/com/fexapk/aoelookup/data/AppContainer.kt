package com.fexapk.aoelookup.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.fexapk.aoelookup.network.AoeApiService

interface AppContainer {
    val playerRepository: PlayerRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUrl = "https://aoe4world.com/api/v0/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService: AoeApiService by lazy {
        retrofit.create(AoeApiService::class.java)
    }

    override val playerRepository: PlayerRepository by lazy {
        NetworkPlayerRepository(retrofitService)
    }
}