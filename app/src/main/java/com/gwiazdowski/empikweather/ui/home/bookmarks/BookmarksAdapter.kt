package com.gwiazdowski.empikweather.ui.home.bookmarks

import androidx.recyclerview.widget.DiffUtil
import com.gwiazdowski.empikweather.R
import com.gwiazdowski.empikweather.databinding.HomeBookmarkBinding
import com.gwiazdowski.empikweather.ui.common.TypedAdapter
import com.gwiazdowski.model.bookmark.Bookmark

class BookmarksAdapter : TypedAdapter<Bookmark, HomeBookmarkBinding>() {
    override val layoutId: Int = R.layout.home_bookmark
    var removeClicked: (Bookmark) -> Unit = {}

    override fun onCreateViewHolder(binding: HomeBookmarkBinding): TypedAdapter.ViewHolder<Bookmark, HomeBookmarkBinding> =
        ViewHolder(binding, removeClicked)

    override fun getDiffCallback(oldItems: List<Bookmark>, newItems: List<Bookmark>): DiffUtil.Callback? =
        BookmarksDiffCallback(oldItems, newItems)

    class ViewHolder(binding: HomeBookmarkBinding, private val removeClicked: (Bookmark) -> Unit) :
        TypedAdapter.ViewHolder<Bookmark, HomeBookmarkBinding>(binding) {
        override fun setupBinding(binding: HomeBookmarkBinding, item: Bookmark) {
            binding.bookmark = item
            binding.removeBookmark.setOnClickListener {
                removeClicked(item)
            }
        }

        override fun unbind() {
            super.unbind()
            binding.removeBookmark.setOnClickListener(null)
        }
    }
}
