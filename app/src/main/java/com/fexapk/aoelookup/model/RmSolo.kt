package com.fexapk.aoelookup.model

import com.google.gson.annotations.SerializedName
data class RmSolo(
    val rating: Int,
    val rank: Int,
    @SerializedName("rank_level") val rankLevel: String,
    val streak: Int,
    @SerializedName("games_count") val gamesCount: Int,
    @SerializedName("wins_count") val winsCount: Int,
    @SerializedName("losses_count") val lossesCount: Int,
    @SerializedName("disputes_count") val disputesCount: Int,
    @SerializedName("drops_count") val dropsCount: Int,
    @SerializedName("last_game_at") val lastGameAt: String,
    @SerializedName("win_rate") val winRate: Double,
    val season: Int
)
