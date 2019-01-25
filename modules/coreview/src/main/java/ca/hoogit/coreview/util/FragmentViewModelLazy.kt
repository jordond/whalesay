package ca.hoogit.coreview.util

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

class FragmentViewModelLazy<VM : ViewModel>(
    private val vmClass: KClass<VM>,
    private val fragment: Fragment,
    private val factory: ViewModelProvider.Factory
) : Lazy<VM> {

    private var cached: VM? = null

    override val value: VM
        get() = cached
            ?: ViewModelProviders.of(fragment, factory)
                .get(vmClass.java)
                .also { cached = it }

    override fun isInitialized() = cached != null
}

@MainThread
inline fun <reified VM : ViewModel> Fragment.injectedViewModel(
    factory: ViewModelProvider.Factory
) = FragmentViewModelLazy(VM::class, this, factory)

@MainThread
inline fun <reified VM : ViewModel> Fragment.injectedSharedViewModel(
    factory: ViewModelProvider.Factory
) = requireActivity().injectedViewModel<VM>(factory)