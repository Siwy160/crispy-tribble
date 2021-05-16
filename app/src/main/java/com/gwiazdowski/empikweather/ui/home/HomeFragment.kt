package com.gwiazdowski.empikweather.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gwiazdowski.empikweather.databinding.HomeFragmentBinding
import com.gwiazdowski.empikweather.ui.NavigationAwareFragment
import com.gwiazdowski.empikweather.ui.common.hideKeyboard
import com.gwiazdowski.empikweather.ui.home.bookmarks.BookmarksAdapter
import com.gwiazdowski.empikweather.ui.home.search.SearchSuggestionsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : NavigationAwareFragment<HomeArguments, HomeViewModel, HomeFragmentBinding>() {

    override val vm: HomeViewModel by viewModel()

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        HomeFragmentBinding.inflate(inflater, container, false)

    override fun HomeFragmentBinding.setupBinding() {
        viewModel = vm
        val searchSuggestions = SearchSuggestionsAdapter()
        searchSuggestions.itemClicked = { vm.searchSuggestionClicked(it.city) }
        vm.searchSuggestions.observe(this@HomeFragment) {
            searchSuggestions.showItems(it)
        }
        searchFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            vm.searchFocusChanged(hasFocus)
            if (hasFocus.not()) {
                v.hideKeyboard()
            }
        }
        this.searchSuggestions.adapter = searchSuggestions
        this.searchSuggestions.layoutManager = LinearLayoutManager(context)

        val bookmarks = BookmarksAdapter()
        bookmarks.itemClicked = vm::bookmarksClicked
        bookmarks.removeClicked = vm::removeBookmarkClicked
        vm.bookmarks.observe(this@HomeFragment) {
            bookmarks.showItems(it)
        }
        this.bookmarks.adapter = bookmarks
        this.bookmarks.layoutManager = LinearLayoutManager(context)
    }
}
