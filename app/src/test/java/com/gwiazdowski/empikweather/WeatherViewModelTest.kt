package com.gwiazdowski.empikweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gwiazdowski.empikweather.tools.InstantExecutorExtensions
import com.gwiazdowski.empikweather.tools.TrampolineSchedulers
import com.gwiazdowski.empikweather.ui.weather.WeatherArguments
import com.gwiazdowski.empikweather.ui.weather.WeatherViewModel
import com.gwiazdowski.model.search.City
import com.gwiazdowski.model.weather.Forecast
import com.gwiazdowski.network.INetworkService
import com.gwiazdowski.services.searchhistory.ILocalStorage
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.reactivex.Single
import org.amshove.kluent.shouldBe
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.rules.TestRule
import java.util.*

@ExtendWith(InstantExecutorExtensions::class)
class WeatherViewModelTest {

    @Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val networkService = mockk<INetworkService>(relaxed = true)
    private val localStorage = mockk<ILocalStorage>(relaxed = true)
    private val schedulers = TrampolineSchedulers()

    private lateinit var tested: WeatherViewModel

    @BeforeEach
    fun setup() {
        tested = WeatherViewModel(
            networkService,
            localStorage,
            schedulers
        )
    }

    @Test
    fun `should fetch weather on start`() {
        //given
        val arguments = WeatherArguments(City("City", 2.0, 5.0))

        //when
        tested.onArgumentsReceived(arguments)

        //then
        verify(exactly = 1) {
            networkService.getForecast(2.0, 5.0)
        }
    }

    @Test
    fun `should show and hide loading on start`() {
        //given
        val arguments = WeatherArguments(City("City", 2.0, 5.0))
        val observer = mockk<Observer<Boolean>>()
        val slot = slot<Boolean>()
        val loadingStates = mutableListOf<Boolean>()
        every { observer.onChanged(capture(slot)) } answers {
            loadingStates.add(slot.captured)
        }
        tested.isLoadingVisible.observeForever(observer)
        every {
            networkService.getForecast(any(), any())
        } returns Single.just(Forecast(Date(0L), 0f, "", "", emptyList(), 0, 0f, 0f))

        //when
        tested.onArgumentsReceived(arguments)

        //then
        loadingStates.size shouldBe 3
        loadingStates[0] shouldBe false
        loadingStates[1] shouldBe true
        loadingStates[2] shouldBe false

    }
}