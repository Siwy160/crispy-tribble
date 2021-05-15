package com.gwiazdowski.empikweather.ui.home

import androidx.lifecycle.MutableLiveData
import com.gwiazdowski.empikweather.ui.NavigationAwareViewModel
import com.gwiazdowski.model.search.SearchSuggestion
import com.gwiazdowski.model.search.SearchSuggestionOrigin
import com.gwiazdowski.network.INetworkService
import com.gwiazdowski.services.navigation.INavigationService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class HomeViewModel(
    private val networkService: INetworkService,
    private val navigationService: INavigationService
) : NavigationAwareViewModel<HomeArguments>() {

    val searchSuggestions: MutableLiveData<List<SearchSuggestion>> = MutableLiveData(emptyList())
    val currentQuery: MutableLiveData<String> = MutableLiveData()
    private val disposables: CompositeDisposable = CompositeDisposable()
    private val querySubject = PublishSubject.create<String>()

    init {
        disposables.add(querySubject
            .doOnNext {
                currentQuery.value = it
            }
            .debounce(500, TimeUnit.MILLISECONDS)
            .flatMap { networkService.getCityByName(it).toObservable().onErrorReturnItem(emptyList()) }
            .map {
                it.map { SearchSuggestion(it, SearchSuggestionOrigin.NETWORK) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                searchSuggestions.value = it
            }, {
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

    fun citySearchQuerySubmit(newQuery: String) {
        querySubject.onNext(newQuery)
        /*      navigationService.navigateTo(
                  NavigationTarget(
                      WeatherFragment::class,
                      WeatherArguments(city)
                  )
              )*/
    }

    override fun onCleared() {
        disposables.dispose()
    }

    private companion object {
        private const val TAG = "HomeViewModel"
    }
}