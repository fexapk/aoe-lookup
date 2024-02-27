package com.fexapk.aoelookup.model

import com.google.gson.annotations.SerializedName
data class MatchInfo(
    val rating: Int,
    val rank: Int,
    val streak: Int,
    val season: Int,
    @SerializedName("rank_level")     val rankLevel: String,
    @SerializedName("games_count")    val gamesCount: Int,
    @SerializedName("wins_count")     val winsCount: Int,
    @SerializedName("losses_count")   val lossesCount: Int,
    @SerializedName("last_game_at")   val lastGameAt: String,
    @SerializedName("win_rate")       val winRate: Double
)
