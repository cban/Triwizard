package com.potter.triwizard.characters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.potter.triwizard.data.Character
import com.potter.triwizard.data.House
import com.potter.triwizard.data.HouseResponse
import com.potter.triwizard.network.TwizardApi
import com.potter.triwizard.repository.CharacterRepositoryImpl
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

class CharacterRepositoryImplTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: TwizardApi
    private lateinit var repository: CharacterRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = CharacterRepositoryImpl(api)
    }

    @Test
    fun test_houses_repo_retrieves_data() {
        val response = mock<Response<List<Character>>>()
        runBlocking {
            whenever(api.getCharacters()).thenReturn(response)
            val dataReceived = repository.getStudents()
            assertNotNull(dataReceived)
        }
    }

    @Test
    fun test_houses_repo_retrieves_student() {
        val response = mock<Response<Character>>()
        runBlocking {
            whenever(api.getCharacterById("1234")).thenReturn(response)
            val returnedStudent = repository.getCharacterById("1234")
            assertThat(response, IsEqual(returnedStudent))
        }
    }
}