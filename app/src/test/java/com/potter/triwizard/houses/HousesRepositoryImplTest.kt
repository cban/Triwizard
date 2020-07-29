package com.potter.triwizard.houses

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.potter.triwizard.data.House
import com.potter.triwizard.data.HouseResponse
import com.potter.triwizard.network.TwizardApi
import com.potter.triwizard.repository.HouseRepositoryImpI
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

class HousesRepositoryImplTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: TwizardApi
    private lateinit var repository: HouseRepositoryImpI

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = HouseRepositoryImpI(api)
    }

    @Test
    fun test_houses_repo_retrieves_data() {
        val response = mock<Response<List<House>>>()
        runBlocking {
            whenever(api.getHouses()).thenReturn(response)
            val dataReceived = repository.getHouses()
            assertNotNull(dataReceived)
        }
    }

    @Test
    fun test_houses_repo_retrieves_house() {
        val response = mock<Response<List<HouseResponse>>>()
        runBlocking {
            whenever(api.getHouse("12233")).thenReturn(response)
            val returnedHouse = repository.getHouseById("12233")
            assertThat(response, IsEqual(returnedHouse))
        }
    }
}