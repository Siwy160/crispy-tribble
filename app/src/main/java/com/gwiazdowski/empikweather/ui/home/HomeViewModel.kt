package com.gwiazdowski.empikweather.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gwiazdowski.empikweather.ui.NavigationAwareViewModel
import com.gwiazdowski.empikweather.ui.common.LiveEvent
import com.gwiazdowski.empikweather.ui.weather.WeatherArguments
import com.gwiazdowski.empikweather.ui.weather.WeatherFragment
import com.gwiazdowski.model.search.SearchSuggestion
import com.gwiazdowski.model.search.SearchSuggestionOrigin
import com.gwiazdowski.network.INetworkService
import com.gwiazdowski.services.navigation.INavigationService
import com.gwiazdowski.services.navigation.NavigationTarget
import com.gwiazdowski.services.schedulers.IRxSchedulers
import com.gwiazdowski.services.searchhistory.ILocalStorage
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class HomeViewModel(
    private val networkService: INetworkService,
    private val navigationService: INavigationService,
    private val localStorage: ILocalStorage,
    private val schedulers: IRxSchedulers
) : NavigationAwareViewModel<HomeArguments>() {

    val searchSuggestions: MutableLiveData<List<SearchSuggestion>> = MutableLiveData(emptyList())
    val currentQuery: MutableLiveData<String> = MutableLiveData()
    val currentSearchFocus: MutableLiveData<Boolean> = MutableLiveData()
    val searchClearFocusRequests: LiveEvent<Boolean> = LiveEvent()
    val searchErrorVisible: MutableLiveData<Boolean> = MutableLiveData()
    val loadingVisible: MutableLiveData<Boolean> = MutableLiveData()
    private val searchQueryValidator = """^([a-zA-Z\u0080-\u024F]+(?:. |-| |'))*[a-zA-Z\u0080-\u024F]*${'$'}""".toRegex()
    private val disposables: CompositeDisposable = CompositeDisposable()
    private val querySubject = PublishSubject.create<String>()

    init {
        disposables.add(
            querySubject
                .doOnNext {
                    localStorage.getSavedSearches(it)
                        .map { it.map { SearchSuggestion(it, SearchSuggestionOrigin.PREVIOUS_SEARCH) } }
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            if (it != searchSuggestions.value) {
                                searchSuggestions.postValue(it)
                            }
                        }, {
                            Log.e(TAG, "Error while fetching local suggestions", it)
                        })
                }
                .map { it.trim() }
                .doOnNext {
                    if (it.isBlank()) {
                        searchSuggestions.postValue(emptyList())
                    }
                }
                .filter(::validateQuery)
                .debounce(250, TimeUnit.MILLISECONDS, schedulers.io())
                .flatMap(::getSearchSuggestions)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribe({
                    searchSuggestions.value = it
                }, {
                    Log.e(TAG, "init: Error while fetching search suggestions", it)
                    searchSuggestions.value = emptyList()
                })
        )
    }

    fun citySearchQueryChanged(newQuery: String) {
        querySubject.onNext(newQuery)
    }

    fun clearClicked() {
        querySubject.onNext("")
    }

    fun searchFocusChanged(hasFocus: Boolean) {
        currentSearchFocus.value = hasFocus
        if (hasFocus) {
            querySubject.onNext("")
        }
    }

    fun backClicked() {
        querySubject.onNext("")
        searchClearFocusRequests.postValue(false)
    }

    fun searchSuggestionClicked(cityName: String) {
        navigateToWeatherScreen(cityName)
    }

    fun citySearchQuerySubmit(newQuery: String) {
        if (validateQuery(newQuery)) {
            navigateToWeatherScreen(newQuery)
        }
    }

    private fun navigateToWeatherScreen(cityName: String) {
        loadingVisible.postValue(true)
        disposables.add(
            networkService.getCityByName(cityName)
                .doOnSuccess {
                    if (it.isNotEmpty()) {
                        localStorage.saveSearch(cityName)
                            .subscribeOn(Schedulers.io())
                            .subscribe({}, { Log.e(TAG, "Error while saving recent search", it) })
                    }
                }
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribe(
                    {
                        loadingVisible.postValue(false)
                        if (it.isEmpty()) {
                            searchErrorVisible.postValue(true)
                        } else {
                            // TODO taking first city probably isn't best idea.
                            searchSuggestions.postValue(emptyList())
                            navigationService.navigateTo(
                                NavigationTarget(
                                    WeatherFragment::class,
                                    WeatherArguments(it.first())
                                )
                            )
                        }
                    },
                    {
                        loadingVisible.postValue(false)
                        Log.e(TAG, "navigateToWeatherScreen: error while fetching details for $cityName", it)
                    }
                )
        )

    }

    private fun validateQuery(query: String): Boolean {
        val isValid = query.isNotBlank() && searchQueryValidator.matches(query)
        if (query.isNotBlank() && isValid.not()) {
            searchErrorVisible.postValue(true)
        }
        return isValid
    }

    private fun getSearchSuggestions(query: String) = localStorage
        .getSavedSearches(query)
        .onErrorReturn { emptyList() }
        .zipWith(
            networkService.getCityNameAutocomplete(query)
                .onErrorReturn { emptyList() }, { local, network ->
                local.map { SearchSuggestion(it, SearchSuggestionOrigin.PREVIOUS_SEARCH) } +
                        network.map { SearchSuggestion(it, SearchSuggestionOrigin.NETWORK) }
            }
        )
        .toObservable()

    override fun onCleared() {
        disposables.dispose()
    }

    private companion object {
        private const val TAG = "HomeViewModel"
    }
}