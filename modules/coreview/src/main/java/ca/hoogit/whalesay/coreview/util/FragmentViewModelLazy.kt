package ca.hoogit.whalesay.coreview.util

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

class FragmentViewModelLazy<VM : ViewModel>(
    private val vmClass: KClass<VM>,
    private val fragment: Fragment,
    private val factoryProvider: () -> ViewModelProvider.Factory
) : Lazy<VM> {

    private var cached: VM? = null

    override val value: VM
        get() = cached
            ?: ViewModelProviders.of(fragment, factoryProvider())
                .get(vmClass.java)
                .also { cached = it }

    override fun isInitialized() = cached != null
}

@MainThread
inline fun <reified VM : ViewModel> Fragment.injectedViewModel(
    noinline factoryProvider: () -> ViewModelProvider.Factory
) = FragmentViewModelLazy(VM::class, this, factoryProvider)

@MainThread
inline fun <reified VM : ViewModel> Fragment.injectedSharedViewModel(
    noinline factoryProvider: () -> ViewModelProvider.Factory
) = requireActivity().injectedViewModel<VM>(factoryProvider)