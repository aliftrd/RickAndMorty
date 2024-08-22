package com.takehomechallenge.aliftrd.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.takehomechallenge.aliftrd.data.character.model.Location
import com.takehomechallenge.aliftrd.data.character.model.Origin
import com.takehomechallenge.aliftrd.data.lib.ApiResponse
import com.takehomechallenge.aliftrd.domain.character.CharacterUseCase
import com.takehomechallenge.aliftrd.domain.character.model.Character
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
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
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    private lateinit var cuc: CharacterUseCase
    private lateinit var vm: HomeViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        cuc = mock {
            onBlocking { getCharacters() } doReturn flow {
                emit(ApiResponse.Success(emptyList()))
            }
        }

        vm = HomeViewModel(cuc)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCharacters should return list of characters`() = runTest {
        val characters = listOf(
            Character(
                id = 1,
                name = "Rick",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                origin = Origin(name = "Earth", url = ""),
                location = Location(name = "Earth", url = ""),
                image = "",
                episode = emptyList(),
                url = "",
            )
        )
        val flow: Flow<ApiResponse<List<Character>>> = flow {
            emit(ApiResponse.Success(characters))
        }
        whenever(cuc.getCharacters()).thenReturn(flow)

        vm = HomeViewModel(cuc)

        val observer = mock<Observer<ApiResponse<List<Character>>>>()
        vm.characters.observeForever(observer)

        // Call the ViewModel method to trigger data fetching
        vm.getCharacters()

        advanceUntilIdle() // Advances the coroutine dispatcher to execute pending coroutines

        verify(observer).onChanged(ApiResponse.Success(characters))
        assertEquals(ApiResponse.Success(characters), vm.characters.value)

        vm.characters.removeObserver(observer)
    }

    @Test
    fun `getCharacters should return error when request fails`() = runTest {
        val errorResponse = ApiResponse.Error("Network Error")
        val flow: Flow<ApiResponse<List<Character>>> = flow {
            emit(errorResponse)
        }
        whenever(cuc.getCharacters()).thenReturn(flow)

        vm = HomeViewModel(cuc)

        val observer = mock<Observer<ApiResponse<List<Character>>>>()
        vm.characters.observeForever(observer)

        // Call the ViewModel method to trigger data fetching
        vm.getCharacters()

        advanceUntilIdle() // Advances the coroutine dispatcher to execute pending coroutines

        verify(observer).onChanged(errorResponse)
        assertEquals(errorResponse, vm.characters.value)

        vm.characters.removeObserver(observer)
    }
}
