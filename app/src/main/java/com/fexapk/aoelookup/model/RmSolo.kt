package com.fexapk.aoelookup.model

data class RmSolo(
    val rating: Int,
    val rank: Int,
    val rankLevel: String,
    val streak: Int,
    val gamesCount: Int,
    val winsCount: Int,
    val lossesCount: Int,
    val disputesCount: Int,
    val dropsCount: Int,
    val lastGameAt: String,
    val winRate: Double,
    val season: Int,
)
