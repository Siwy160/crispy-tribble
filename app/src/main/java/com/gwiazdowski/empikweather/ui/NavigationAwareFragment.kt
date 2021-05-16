package com.gwiazdowski.empikweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.gwiazdowski.services.navigation.IArguments
import com.gwiazdowski.services.navigation.IView
import org.koin.androidx.scope.ScopeFragment

abstract class NavigationAwareFragment<A : IArguments, VM : NavigationAwareViewModel<A>, B : ViewDataBinding> : ScopeFragment(), IView {
    protected abstract val vm: VM
    private var savedArgs: IArguments? = null

    abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup?): B

    open fun B.setupBinding() {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflateView(inflater, container).apply {
            setupBinding()
            lifecycleOwner = this@NavigationAwareFragment
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedArgs?.let {
            vm.setArguments(it)
            savedArgs = null
        }
    }

    override fun onResume() {
        super.onResume()
        vm.onResume()
    }

    override fun setArguments(args: IArguments) {
        if (isAdded) {
            vm.setArguments(args)
        } else {
            savedArgs = args
        }
    }
}
