package com.potter.triwizard.spells

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.potter.triwizard.data.House
import com.potter.triwizard.data.HouseResponse
import com.potter.triwizard.data.Spell
import com.potter.triwizard.network.TwizardApi
import com.potter.triwizard.repository.SpellRepositoryImpl
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

class SpellsRepositoryImplTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: TwizardApi
    private lateinit var repository: SpellRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = SpellRepositoryImpl(api)
    }

    @Test
    fun test_houses_repo_spells_data() {
        val response = mock<Response<List<Spell>>>()
        runBlocking {
            whenever(api.getSpells()).thenReturn(response)
            val dataReceived = repository.getSpells()
            assertNotNull(dataReceived)

        }
    }

}