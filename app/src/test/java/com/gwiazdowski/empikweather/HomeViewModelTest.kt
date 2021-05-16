package com.gwiazdowski.empikweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gwiazdowski.empikweather.tools.InstantExecutorExtensions
import com.gwiazdowski.empikweather.tools.TrampolineSchedulers
import com.gwiazdowski.empikweather.ui.home.HomeViewModel
import com.gwiazdowski.model.search.City
import com.gwiazdowski.network.INetworkService
import com.gwiazdowski.services.navigation.INavigationService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.amshove.kluent.shouldBe
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.rules.TestRule

@ExtendWith(InstantExecutorExtensions::class)
class HomeViewModelTest {

    @Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val networkService = mockk<INetworkService>(relaxed = true)
    private val navigationService = mockk<INavigationService>(relaxed = true)
    private val schedulers = TrampolineSchedulers()

    private lateinit var tested: HomeViewModel

    @BeforeEach
    fun setup() {
        tested = HomeViewModel(
            networkService,
            navigationService,
            schedulers
        )
    }

    @Test
    fun `should execute search when valid query submitted`() {
        //when
        tested.citySearchQuerySubmit("City")

        //then
        verify(exactly = 1) { networkService.getCityByName("City") }
    }

    @Test
    fun `should show search suggestions when query changed`() {
        //given
        val sampleSuggestions = listOf("City1", "City2")
        every { networkService.getCityNameAutocomplete(any()) } returns Single.just(sampleSuggestions)

        //when
        tested.citySearchQueryChanged("Query")

        //then
        tested.searchSuggestions.value?.size shouldBe 2
    }

    @Test
    fun `should fetch city details and navigate to main screen when search submitted`() {
        //given
        every { networkService.getCityByName(any()) } returns Single.just(listOf(City("City", 0.0, 0.0)))

        //when
        tested.citySearchQuerySubmit("City")

        //then
        verify(exactly = 1) { networkService.getCityByName("City") }
        verify(exactly = 1) { navigationService.navigateTo(any()) }
    }

    @Test
    fun `should show error when incorrect city name submitted`() {

        //when
        tested.citySearchQueryChanged("City!***")

        //then
        tested.searchErrorVisible.value shouldBe true
    }
}