package com.fexapk.aoelookup.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fexapk.aoelookup.R
import com.fexapk.aoelookup.model.MatchData
import com.fexapk.aoelookup.ui.theme.AoeLookUpTheme

@Composable
fun MatchDataCard(
    matchTypeName: String,
    matchData: MatchData,
    modifier: Modifier = Modifier
) {
    OutlinedCard {

        val firstColumnMap = mapOf(
            R.string.rating to matchData.rating.toString(),
            R.string.rank to matchData.rank.toString(),
            R.string.streak to matchData.streak.toString(),
            R.string.season to matchData.season.toString(),
        )
        val secondColumnMap = mapOf(
            R.string.games to matchData.gamesCount.toString(),
            R.string.wins to matchData.winsCount.toString(),
            R.string.loses to matchData.lossesCount.toString(),
            R.string.win_rate to matchData.winRate.toString(),
        )

        Column(
            modifier = modifier
        ) {
            Text(
                text = matchTypeName,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Divider(modifier = Modifier.padding(bottom = 4.dp, top = 2.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
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
                modifier = Modifier.width(120.dp)
            )
            Spacer(Modifier.height(2.dp))
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
            text = stringResource(nameResource),
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = data,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview
@Composable
fun MatchDataCardPreview() {
    AoeLookUpTheme {
        val rmSolo = MatchData(
            rating = 900,
            rank = 123,
            rankLevel = "conqueror_3",
            streak = 3,
            gamesCount = 50,
            winsCount = 30,
            lossesCount = 20,
            lastGameAt = "2024-01-31T18:30:00.000Z",
            winRate = 60.0,
            season = 6,
            previousSeasons = null
        )

        MatchDataCard(
            matchData = rmSolo,
            matchTypeName = "Ranked Solo",
            modifier = Modifier.padding(dimensionResource(R.dimen.medium_card_padding))
        )
    }
}
