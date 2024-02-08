package com.fexapk.aoelookup.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fexapk.aoelookup.model.Player
import com.fexapk.aoelookup.R
import com.fexapk.aoelookup.model.Leaderboards
import com.fexapk.aoelookup.model.RmSolo


@Composable
fun PlayerCard(player: Player,modifier: Modifier = Modifier) {
    Card {
        Row(
            modifier = modifier
        ) {
            Image(
                painter = painterResource(id = R.drawable.gold_two),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
            PlayerInformation(
                player = player
            )
        }
    }
}

@Composable
fun PlayerInformation(
    player: Player,
    modifier: Modifier = Modifier) {

    val playerRank = player.leaderboards.rmSolo.rank

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = isoCoutryCodeToEmojiFlag(player.country)
            )
        }
        Text(
            text = player.name,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "# $playerRank",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
fun PlayerCardPreview() {
    PlayerCard(
        player = getDefaultPlayer(),
        modifier = Modifier.padding(8.dp)
    )
}

fun isoCoutryCodeToEmojiFlag(isoCode: String): String {
    val firstLetter = Character.codePointAt(isoCode, 0) - 0x41 + 0x1F1E6
    val secondLetter = Character.codePointAt(isoCode, 1) - 0x41 + 0x1F1E6
    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}

fun getDefaultPlayer(): Player {
    val leaderboards = Leaderboards(
        rmSolo = RmSolo(
            rating = 900,
            rank = 123,
            rankLevel = "gold",
            streak = 3,
            gamesCount = 50,
            winsCount = 30,
            lossesCount = 20,
            disputesCount = 0,
            dropsCount = 1,
            lastGameAt = "2024-01-31T18:30:00.000Z",
            winRate = 60.0,
            season = 6
        )
    )

    return Player(
        name = "John Doe",
        profileId = 123456,
        steamId = "765611987654321",
        country = "US",
        lastGameAt = "2024-02-03T12:45:00.000Z",
        leaderboards = leaderboards
    )
}