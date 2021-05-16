package com.gwiazdowski.empikweather.ui.home.search

import com.gwiazdowski.empikweather.R
import com.gwiazdowski.empikweather.databinding.HomeSearchSuggestionBinding
import com.gwiazdowski.empikweather.ui.common.TypedAdapter
import com.gwiazdowski.model.search.SearchSuggestion

class SearchSuggestionsAdapter : TypedAdapter<SearchSuggestion, HomeSearchSuggestionBinding>() {

    override val layoutId: Int = R.layout.home_search_suggestion

    override fun onCreateViewHolder(
        binding: HomeSearchSuggestionBinding
    ): TypedAdapter.ViewHolder<SearchSuggestion, HomeSearchSuggestionBinding> = ViewHolder(binding)

    class ViewHolder(
        binding: HomeSearchSuggestionBinding
    ) : TypedAdapter.ViewHolder<SearchSuggestion, HomeSearchSuggestionBinding>(binding) {
        override fun setupBinding(binding: HomeSearchSuggestionBinding, item: SearchSuggestion) {
            binding.suggestion = item
        }
    }
}
