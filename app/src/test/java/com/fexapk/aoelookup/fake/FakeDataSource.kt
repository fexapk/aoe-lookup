package com.fexapk.aoelookup.fake

import com.fexapk.aoelookup.model.Leaderboards
import com.fexapk.aoelookup.model.MatchData
import com.fexapk.aoelookup.model.Player
import com.fexapk.aoelookup.model.PlayerResponse

object FakeDataSource {

    private val leaderboards = Leaderboards(
        rmSolo = MatchData(
            rating = 900,
            rank = 123,
            rankLevel = "conqueror_3",
            streak = 3,
            gamesCount = 50,
            winsCount = 30,
            lossesCount = 20,
            lastGameAt = "2024-01-31T18:30:00.000Z",
            winRate = 60.0,
            season = 6
        )
    )

    private val player =  Player(
        name = "John Doe",
        profileId = 123456,
        steamId = "765611987654321",
        country = "us",
        lastGameAt = "2024-02-03T12:45:00.000Z",
        leaderboards = leaderboards
    )

    val playerList = listOf(player)
    val emptyPlayerList = listOf<Player>()

    fun getResponse(): PlayerResponse {
        return PlayerResponse(playerList)
    }


}