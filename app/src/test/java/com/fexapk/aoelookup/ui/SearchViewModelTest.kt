package com.fexapk.aoelookup.ui

import com.fexapk.aoelookup.data.PlayerRepository
import com.fexapk.aoelookup.fake.FakeDataSource
import com.fexapk.aoelookup.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var mockPlayerRepository: PlayerRepository
    private val usernameQuery = "Player"

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
            coEvery { mockPlayerRepository.searchPlayers(usernameQuery) } returns FakeDataSource.playerList

            searchViewModel.searchPlayers(usernameQuery)
            advanceTimeBy(300L) // Debounce time

            assertEquals(UiState.Loading, searchViewModel.uiState)
            advanceUntilIdle()

            assertEquals(UiState.Success(FakeDataSource.playerList), searchViewModel.uiState)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun searchViewModel_searchPlayers_whenUserNameQueryIsEmptyUiStateIsHome() =
        runTest {
            val username = ""
            coEvery {
                mockPlayerRepository.searchPlayers(username)
            } returns FakeDataSource.emptyPlayerList

            searchViewModel.searchPlayers(username)
            advanceTimeBy(300L)
            advanceUntilIdle()

            assertEquals(UiState.Home, searchViewModel.uiState)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun searchViewModel_searchPlayers_whenIOExceptionIsThrownUiStateIsError() =
        runTest {
            coEvery {
                mockPlayerRepository.searchPlayers(usernameQuery)
            } throws IOException()

            searchViewModel.searchPlayers(usernameQuery)
            advanceTimeBy(300L)
            advanceUntilIdle()


            assertEquals(UiState.Error, searchViewModel.uiState)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun searchViewModel_searchPlayers_whenHttpExceptionIsThrownUiStateIsError() =
        runTest {
            val fakeHttpException = HttpException(
                Response.error<ResponseBody>(
                    404,
                    ResponseBody.create(MediaType.parse("plain/text"), "some content")
                )
            )
            coEvery {
                mockPlayerRepository.searchPlayers(usernameQuery)
            } throws fakeHttpException

            searchViewModel.searchPlayers(usernameQuery)
            advanceTimeBy(300L)
            advanceUntilIdle()

            assertEquals(UiState.Error, searchViewModel.uiState)
        }
}