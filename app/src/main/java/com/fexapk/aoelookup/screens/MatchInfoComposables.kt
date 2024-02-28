package com.fexapk.aoelookup.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fexapk.aoelookup.R
import com.fexapk.aoelookup.model.MatchInfo
import com.fexapk.aoelookup.ui.theme.AoeLookUpTheme

@Composable
fun MatchInfoCard(
    matchInfo: MatchInfo,
    matchTypeName: String,
    modifier: Modifier = Modifier
) {
    OutlinedCard {

        val firstColumnMap = mapOf(
            R.string.rating to matchInfo.rating.toString(),
            R.string.rank to matchInfo.rank.toString(),
            R.string.streak to matchInfo.streak.toString(),
            R.string.season to matchInfo.season.toString(),
        )
        val secondColumnMap = mapOf(
            R.string.games to matchInfo.gamesCount.toString(),
            R.string.wins to matchInfo.winsCount.toString(),
            R.string.loses to matchInfo.lossesCount.toString(),
            R.string.win_rate to matchInfo.winRate.toString(),
        )

        Column(
            modifier = modifier
        ) {
            Text(
                text = matchTypeName,
                style = MaterialTheme.typography.titleMedium
            )
            Divider(
                color = Color.Black,
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 4.dp, bottom = 4.dp),
                thickness = 2.dp
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(4.dp)
            ) {
                InformationColumn(nameDataMap = firstColumnMap)
                Spacer(modifier = Modifier.width(14.dp))
                InformationColumn(nameDataMap = secondColumnMap)
            }
        }
    }
}

@Composable
fun InformationColumn(
    nameDataMap: Map<Int,String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        nameDataMap.forEach{
            (key,value) -> InformationRow(
                nameResource = key,
                data = value,
            )
        }
    }
}



@Composable
fun InformationRow(
    @StringRes nameResource: Int,
    data: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            stringResource(nameResource),
            modifier = Modifier.width(60.dp),
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = data,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
fun MatchInfoCardPreview() {
    AoeLookUpTheme {
        val rmSolo = MatchInfo(
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

        MatchInfoCard(
            matchInfo = rmSolo,
            matchTypeName = "Ranked Solo",
            modifier = Modifier.padding(dimensionResource(R.dimen.medium_card_padding))
        )
    }
}
