package com.fexapk.aoelookup.data

import com.fexapk.aoelookup.fake.FakeAoeService
import com.fexapk.aoelookup.fake.FakeDataSource
import com.fexapk.aoelookup.model.Player
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkPlayerRepositoryTest {
    @Test
    fun networkPlayerRepository_getPlayers_verifyPlayerList() = runTest {
        val repository = NetworkPlayerRepository(
            service = FakeAoeService()
        )
        assertEquals(FakeDataSource.getResponse().players, repository.searchPlayers(""))
    }
}