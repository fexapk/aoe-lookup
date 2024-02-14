package com.fexapk.aoelookup.model

import com.google.gson.annotations.SerializedName

data class RmSolo(
    val rating: Int = 0,
    val rank: Int = 0,
   @SerializedName("rank_level") val rankLevel: String = "no_rank",
    val streak: Int = 0,
    val gamesCount: Int = 0,
    val winsCount: Int = 0,
    val lossesCount: Int = 0,
    val disputesCount: Int = 0,
    val dropsCount: Int = 0,
    val lastGameAt: String = "never",
    val winRate: Double = 0.0,
    val season: Int = 0,
)
