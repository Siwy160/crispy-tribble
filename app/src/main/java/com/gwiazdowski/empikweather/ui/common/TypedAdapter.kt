package com.gwiazdowski.empikweather.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class TypedAdapter<I, B : ViewDataBinding> : RecyclerView.Adapter<TypedAdapter.ViewHolder<I, B>>() {

    @get:LayoutRes
    abstract val layoutId: Int
    private var items: List<I> = emptyList()
    var itemClicked: (I) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<I, B> =
        onCreateViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false))

    abstract fun onCreateViewHolder(binding: B): ViewHolder<I, B>

    override fun onBindViewHolder(holder: ViewHolder<I, B>, position: Int) {
        holder.bind(items[position], itemClicked)
    }

    override fun onViewRecycled(holder: ViewHolder<I, B>) {
        holder.unbind()
    }

    override fun getItemCount(): Int = items.size

    open fun getDiffCallback(oldItems: List<I>, newItems: List<I>): DiffUtil.Callback? {
        return null
    }

    fun showItems(newItems: List<I>) {
        val diffCallback = getDiffCallback(items, newItems)
        if (diffCallback != null) {
            val result = DiffUtil.calculateDiff(diffCallback)
            items = newItems
            result.dispatchUpdatesTo(this)
        } else {
            items = newItems
            notifyDataSetChanged()
        }
    }

    abstract class ViewHolder<I, B : ViewDataBinding>(protected val binding: B) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: I, itemClicked: (I) -> Unit) {
            setupBinding(binding, item)
            binding.root.setOnClickListener { itemClicked(item) }
        }

        abstract fun setupBinding(binding: B, item: I)

        @CallSuper
        open fun unbind() {
            binding.root.setOnClickListener(null)
        }
    }
}