package com.fexapk.aoelookup.model

data class Player(
    val name: String,
    val profileId: Long,
    val steamId: String,
    val country: String,
    val lastGameAt: String,
    val leaderboards: Leaderboards
)