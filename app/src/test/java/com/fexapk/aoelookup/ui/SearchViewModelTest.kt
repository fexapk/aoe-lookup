package com.fexapk.aoelookup.ui

import com.fexapk.aoelookup.data.PlayerRepository
import com.fexapk.aoelookup.fake.FakeDataSource
import com.fexapk.aoelookup.fake.FakeNetworkPlayerRepository
import com.fexapk.aoelookup.rules.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SearchViewModel
    @Mock
    private lateinit var mockPlayerRepository: PlayerRepository

    @Before
    fun setUp() {
        viewModel = SearchViewModel(
            playerRepository = mockPlayerRepository
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun searchViewModel_searchPlayers_successfulStateWhenPlayerResponseIsReceived() =
        runTest {
            val viewModel = SearchViewModel(
                playerRepository = FakeNetworkPlayerRepository()
            )
            val username = "Player"

            viewModel.searchPlayers(username)
            advanceTimeBy(300L) // Debounce time
            advanceUntilIdle()

            assertEquals(UiState.Success(FakeDataSource.playerList), viewModel.uiState)
        }


}