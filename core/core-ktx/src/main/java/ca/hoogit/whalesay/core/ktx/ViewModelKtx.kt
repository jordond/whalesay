package ca.hoogit.whalesay.core.ktx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * For Activities, allows declarations like
 * ```
 * val viewModel by lazy { getViewModel<MyViewModel>(viewModelFactory) }
 * ```
 */
inline fun <reified VM : ViewModel> FragmentActivity.getViewModel(
    provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this, provider).get(VM::class.java)

/**
 * For Fragments, allows declarations like
 * ```
 * val viewModel by lazy { getViewModel<MyViewModel>(viewModelFactory) }
 * ```
 */
inline fun <reified VM : ViewModel> Fragment.getViewModel(
    provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this, provider).get(VM::class.java)

/**
 * Like [Fragment.getViewModel] for Fragments that want a [ViewModel] scoped to the Activity.
 */
inline fun <reified VM : ViewModel> Fragment.getSharedViewModel(
    provider: ViewModelProvider.Factory,
    parent: Fragment? = null
): VM {
    val factory = parent?.let { ViewModelProviders.of(it, provider) }
        ?: ViewModelProviders.of(requireActivity(), provider)

    return factory.get(VM::class.java)
}
