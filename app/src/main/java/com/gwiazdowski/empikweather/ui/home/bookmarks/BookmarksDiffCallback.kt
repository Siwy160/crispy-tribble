package com.gwiazdowski.empikweather.ui.home.bookmarks

import androidx.recyclerview.widget.DiffUtil
import com.gwiazdowski.model.bookmark.Bookmark

class BookmarksDiffCallback(
    private val oldList: List<Bookmark>,
    private val newList: List<Bookmark>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].city == newList[newItemPosition].city

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}