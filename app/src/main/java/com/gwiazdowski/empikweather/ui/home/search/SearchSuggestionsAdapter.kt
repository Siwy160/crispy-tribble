package com.gwiazdowski.empikweather.ui.home.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gwiazdowski.empikweather.R
import com.gwiazdowski.empikweather.databinding.HomeSerachSuggestionBinding
import com.gwiazdowski.model.search.SearchSuggestion

class SearchSuggestionsAdapter : RecyclerView.Adapter<SearchSuggestionsAdapter.ViewHolder>() {

    private var suggestions: List<SearchSuggestion> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.home_serach_suggestion, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(suggestions[position])
    }

    override fun getItemCount(): Int = suggestions.size

    fun showSuggestions(newSuggestions: List<SearchSuggestion>) {
        suggestions = newSuggestions
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: HomeSerachSuggestionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(suggestion: SearchSuggestion) {
            binding.suggestion = suggestion
        }
    }
}