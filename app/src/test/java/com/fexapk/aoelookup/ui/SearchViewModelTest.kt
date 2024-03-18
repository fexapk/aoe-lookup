package com.fexapk.aoelookup.ui

import com.fexapk.aoelookup.data.PlayerRepository
import com.fexapk.aoelookup.fake.FakeDataSource
import com.fexapk.aoelookup.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var mockPlayerRepository: PlayerRepository

    @Before
    fun setUp() {
        mockPlayerRepository = mockk()
        searchViewModel = SearchViewModel(
            playerRepository = mockPlayerRepository
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun searchViewModel_searchPlayers_successfulStateWhenPlayerResponseIsReceivedAndLoadingStateBeforeResults() =
        runTest {
            coEvery { mockPlayerRepository.searchPlayers("Player") } returns FakeDataSource.playerList
            val username = "Player"

            searchViewModel.searchPlayers(username)
            advanceTimeBy(300L) // Debounce time

            assertEquals(UiState.Loading, searchViewModel.uiState)
            advanceUntilIdle()

            assertEquals(UiState.Success(FakeDataSource.playerList), searchViewModel.uiState)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun searchViewModel_searchPlayers_whenUserNameQueryIsEmptyUiStateIsHome() =
        runTest {
            coEvery { mockPlayerRepository.searchPlayers("") } returns FakeDataSource.emptyPlayerList
            val username = ""

            searchViewModel.searchPlayers(username)
            advanceTimeBy(300L)
            advanceUntilIdle()

            assertEquals(UiState.Home, searchViewModel.uiState)
        }

}