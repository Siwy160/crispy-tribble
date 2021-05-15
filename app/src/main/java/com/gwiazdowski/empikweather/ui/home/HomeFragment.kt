package com.gwiazdowski.empikweather.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gwiazdowski.empikweather.databinding.HomeFragmentBinding
import com.gwiazdowski.empikweather.ui.NavigationAwareFragment
import com.gwiazdowski.empikweather.ui.common.hideKeyboard
import com.gwiazdowski.empikweather.ui.home.search.SearchSuggestionsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : NavigationAwareFragment<HomeArguments, HomeViewModel, HomeFragmentBinding>() {

    override val vm: HomeViewModel by viewModel()

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        HomeFragmentBinding.inflate(inflater, container, false)

    override fun HomeFragmentBinding.setupBinding() {
        viewModel = vm
        val adapter = SearchSuggestionsAdapter()
        adapter.itemClicked = vm::searchSuggestionClicked
        vm.searchSuggestions.observe(this@HomeFragment) {
            adapter.showSuggestions(it)
        }
        searchFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            vm.searchFocusChanged(hasFocus)
            if (hasFocus.not()) {
                v.hideKeyboard()
            }
        }
        searchSuggestions.adapter = adapter
        searchSuggestions.layoutManager = LinearLayoutManager(context)
    }
}
