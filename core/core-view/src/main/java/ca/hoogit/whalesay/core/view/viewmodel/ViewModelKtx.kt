package ca.hoogit.whalesay.core.view.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import ca.hoogit.whalesay.core.view.activity.BaseActivity
import ca.hoogit.whalesay.core.view.fragment.BaseFragment
import ca.hoogit.whalesay.core.view.util.injectedSharedViewModel
import ca.hoogit.whalesay.core.view.util.injectedViewModel
import ca.hoogit.whalesay.core.ktx.getSharedViewModel
import ca.hoogit.whalesay.core.ktx.getViewModel

/**
 * Like [FragmentActivity.getViewModel] using the injected [BaseActivity.viewModelFactory]
 * Usage
 * ```
 * val viewModel: MyViewModel = getViewModel()
 *
 * // Or
 *
 * val viewModel by lazy { getViewModel<MyViewModel>() }
 * ```
 */
inline fun <reified VM : ViewModel> BaseActivity.getViewModel(): VM = getViewModel(viewModelFactory)

/**
 * Like [FragmentActivity.getViewModel] using the injected [BaseActivity.viewModelFactory]
 *
 * Similar to [BaseActivity.getViewModel] except it removes the need for using the lazy delegate
 *
 * @sample
 * private val viewModel by injectedViewModel<MyViewModel>()
 */
inline fun <reified VM : ViewModel> BaseActivity.injectedViewModel() =
    injectedViewModel<VM> { viewModelFactory }

/**
 * Like [BaseActivity.getViewModel] for [BaseFragment]s
 */
inline fun <reified VM : ViewModel> BaseFragment.getViewModel(): VM = getViewModel(viewModelFactory)

/**
 * Like [BaseFragment.getViewModel] for [BaseFragment]'s that want a [ViewModel] scoped to the activity.
 */
inline fun <reified VM : ViewModel> BaseFragment.getSharedViewModel(parent: Fragment? = null): VM =
    getSharedViewModel(viewModelFactory, parent)

/**
 * Like [BaseActivity.injectedViewModel] for [BaseFragment]
 *
 * @sample
 * private val viewModel by injectedViewModel<MyViewModel>()
 */
inline fun <reified VM : ViewModel> BaseFragment.injectedViewModel() =
    injectedViewModel<VM> { viewModelFactory }

/**
 * Like [BaseFragment.injectedViewModel] for [BaseFragment]'s that want a [ViewModel] scoped to the activity.
 *
 * @sample
 * private val viewModel by injectedSharedViewModel<MyViewModel>()
 */
inline fun <reified VM : ViewModel> BaseFragment.injectedSharedViewModel() =
    injectedSharedViewModel<VM> { viewModelFactory }
