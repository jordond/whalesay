package ca.hoogit.whalesay.core.view.util

import androidx.annotation.MainThread
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

class ActivityViewModelLazy<VM : ViewModel>(
    private val vmClass: KClass<VM>,
    private val activity: FragmentActivity,
    private val factoryProducer: () -> ViewModelProvider.Factory
) : Lazy<VM> {

    private var cached: VM? = null

    override val value: VM
        get() = cached
            ?: ViewModelProviders.of(activity, factoryProducer())
                .get(vmClass.java)
                .also { cached = it }

    override fun isInitialized() = cached != null
}

@MainThread
inline fun <reified VM : ViewModel> FragmentActivity.injectedViewModel(
    noinline factoryProvider: () -> ViewModelProvider.Factory
) = ActivityViewModelLazy(VM::class, this, factoryProvider)