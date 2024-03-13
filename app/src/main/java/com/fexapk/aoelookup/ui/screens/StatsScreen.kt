package com.fexapk.aoelookup.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.fexapk.aoelookup.R
import com.fexapk.aoelookup.model.Leaderboards
import com.fexapk.aoelookup.model.Player
import com.fexapk.aoelookup.ui.MatchDataCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsTopBar(
    title: String,
    onNavigateButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onNavigateButtonClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button_text)
                )
            }
        }
    )
}


@Composable
fun StatsScreen(
    player: Player,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            StatsTopBar(
                title = player.name,
                onNavigateButtonClick = navigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        MatchDataList(
            leaderboards = player.leaderboards,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun MatchDataList(
    leaderboards: Leaderboards,
    modifier: Modifier = Modifier
) {
    val matchDataMap = mapOf(
        "Ranked Solo" to leaderboards.rmSolo,
        "Ranked Team" to leaderboards.rmTeam,
        "Ranked Team 2v2" to leaderboards.rm2v2Elo,
        "Ranked Team 3v3" to leaderboards.rm3v3Elo,
        "Ranked Team 4v4" to leaderboards.rm4v4Elo,
        "Quick match Solo" to leaderboards.qm1v1,
        "Quick match 2v2" to leaderboards.qm2v2,
        "Quick match 3v3" to leaderboards.qm3v3,
        "Quick match 4v4" to leaderboards.qm4v4
    )

    val noData = matchDataMap.all { it.value == null }
    val dataList = matchDataMap.entries.toList()

    if (noData) {
        NoPlayersFoundBox(
            R.string.no_match_data,
            Modifier.fillMaxSize()
        )
    } else {
        LazyColumn(
            verticalArrangement = Arrangement
                .spacedBy(dimensionResource(id = R.dimen.list_spacing)),
            modifier = modifier
        ) {
            items(dataList) { entry ->
                entry.value?.let { matchData ->
                    MatchDataCard(matchTypeName = entry.key, matchData = matchData)
                }
            }
        }
    }
}