package com.takehomechallenge.aliftrd.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.takehomechallenge.aliftrd.data.character.model.Location
import com.takehomechallenge.aliftrd.data.character.model.Origin
import com.takehomechallenge.aliftrd.data.lib.ApiResponse
import com.takehomechallenge.aliftrd.domain.character.CharacterUseCase
import com.takehomechallenge.aliftrd.domain.character.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.junit.Assert.assertEquals
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {
    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var cuc: CharacterUseCase

    @Mock
    private lateinit var observer: Observer<ApiResponse<List<Character>>>

    private lateinit var vm: SearchViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        vm = SearchViewModel(cuc)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        vm.characters.removeObserver(observer)
    }

    @Test
    fun `searchCharacter should return list of characters`() = runTest {
        val characters = listOf(
            Character(
                id = 1,
                name = "Alien Rick",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                origin = Origin(name = "Earth", url = ""),
                location = Location(name = "Earth", url = ""),
                image = "",
                episode = emptyList(),
                url = ""
            )
        )
        val expectedResult = ApiResponse.Success(characters)

        whenever(cuc.searchCharacters("Alien")).thenReturn(flow {
            emit(expectedResult)
        })

        vm.characters.observeForever(observer)

        // Call the function to trigger data fetching
        vm.searchCharacters("Alien")

        advanceUntilIdle()

        verify(observer).onChanged(expectedResult)
        assertEquals(expectedResult, vm.characters.value)
    }

    @Test
    fun `searchCharacter should return error when request fails`() = runTest {
        val errorResponse = ApiResponse.Error("Network Error")

        whenever(cuc.searchCharacters("Alien")).thenReturn(flow {
            emit(errorResponse)
        })

        vm.characters.observeForever(observer)

        // Call the function to trigger data fetching
        vm.searchCharacters("Alien")

        advanceUntilIdle()

        verify(observer).onChanged(errorResponse)
        assertEquals(errorResponse, vm.characters.value)
    }
}
