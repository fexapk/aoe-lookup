package com.fexapk.aoelookup.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fexapk.aoelookup.R
import com.fexapk.aoelookup.data.RankDrawable
import com.fexapk.aoelookup.model.Leaderboards
import com.fexapk.aoelookup.model.MatchInfo
import com.fexapk.aoelookup.model.Player
import com.fexapk.aoelookup.ui.theme.AoeLookUpTheme

@Composable
fun PlayerCard(
    player: Player,
    modifier: Modifier = Modifier
) {

    var isTouched by remember { mutableStateOf(false) }

    val alphaValue by animateFloatAsState(
        targetValue = if (isTouched) 0.5f else 1f,
        animationSpec = tween(durationMillis = 300),
        label = "Player card touched"
    )

    OutlinedCard(
        modifier = Modifier
            .alpha(alphaValue)
            .height(80.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            RankedIcon(player)
            PlayerInformation(
                player = player
            )
        }
    }
}

@Composable
fun RankedIcon(player: Player) {

    val isSystemDark = isSystemInDarkTheme()
    val rmSoloInfo: MatchInfo? = player.leaderboards.rmSolo
    val rankLevel: String? = rmSoloInfo?.rankLevel
    val unrankedRes = if (isSystemDark) R.drawable.no_rank_white else R.drawable.no_rank

    val drawableRes: Int = RankDrawable.getRankDrawable(rankLevel) ?: unrankedRes

    val imageModifier = if (drawableRes == unrankedRes) Modifier
        .size(60.dp)
        .padding(8.dp)
        else Modifier.size(70.dp)

    Image(
        painter = painterResource(drawableRes),
        contentDescription = null,
        modifier = imageModifier
    )
}


@Composable
fun PlayerInformation(
    player: Player,
    modifier: Modifier = Modifier
) {

    fun getEmojiFromIsoCountryCode(isoCode: String?): String {

        if (isoCode == null) return "\u2753"

        val upperCaseCode = isoCode.uppercase()
        val firstLetter = Character.codePointAt(upperCaseCode, 0) - 0x41 + 0x1F1E6
        val secondLetter = Character.codePointAt(upperCaseCode, 1) - 0x41 + 0x1F1E6
        return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
    }

    val rank = player.leaderboards.rmSolo?.rank ?: 0

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = player.name,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_spacer_height)))
        Text(
            text = stringResource(id = R.string.player_rank, rank),
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_spacer_height)))
        Text(
            text = getEmojiFromIsoCountryCode(player.country),
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
fun PlayerCardPreview() {
    AoeLookUpTheme {
        val leaderboards = Leaderboards(
            rmSolo = MatchInfo(
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
        val player =  Player(
            name = "John Doe",
            profileId = 123456,
            steamId = "765611987654321",
            country = "us",
            lastGameAt = "2024-02-03T12:45:00.000Z",
            leaderboards = leaderboards
        )

        PlayerCard(
            player = player,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun PlayerNoRankPreview() {

    val leaderboards = Leaderboards()
    val player = Player(
        name = "John Doe",
        profileId = 123456,
        steamId = "765611987654321",
        country = "us",
        lastGameAt = "2024-02-03T12:45:00.000Z",
        leaderboards = leaderboards
    )
    PlayerCard(
        player = player,
        modifier = Modifier.padding(8.dp)
    )
}

