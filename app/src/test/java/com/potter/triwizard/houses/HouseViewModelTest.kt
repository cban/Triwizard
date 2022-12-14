package com.potter.triwizard.houses

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.potter.triwizard.TestCoroutineRule
import com.potter.triwizard.data.House
import com.potter.triwizard.network.TwizardApi
import com.potter.triwizard.repository.HouseRepository
import com.potter.triwizard.ui.house.HouseViewModel
import com.potter.triwizard.util.Resource
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

class HouseViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var api: TwizardApi

    @Mock
    private lateinit var houseRepository: HouseRepository

    @Mock
    private lateinit var resource: Resource<List<House>>
    private lateinit var viewModel: HouseViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val response = mock<Response<List<House>>>()

        testCoroutineRule.runBlockingTest {
            whenever(houseRepository.getHouses()).thenReturn(response)
        }
        val savedState = SavedStateHandle(mapOf("id" to "testId"))
        viewModel = HouseViewModel(savedState, houseRepository)
    }
}

