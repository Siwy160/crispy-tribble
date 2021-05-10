package com.gwiazdowski.empikweather.ui.home

import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter

@BindingAdapter("onTextChanged")
fun AppCompatEditText.onTextChanged(listener: TextChangedListener) {
    addTextChangedListener {
        it?.toString()?.let(listener::onTextChanged)
    }
}

interface TextChangedListener {
    fun onTextChanged(text: String)
}

@BindingAdapter("onTextSubmit")
fun AppCompatEditText.onTextSubmit(listener: TextSubmitListener) {
    setOnEditorActionListener { v, actionId, event ->
        text?.toString()?.let(listener::onTextSubmit)
        true
    }
}

interface TextSubmitListener {
    fun onTextSubmit(text: String)
}