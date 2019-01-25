package ca.hoogit.coreview.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import ca.hoogit.coreview.activity.BaseActivity
import ca.hoogit.coreview.fragment.BaseFragment
import ca.hoogit.ktx.getSharedViewModel
import ca.hoogit.ktx.getViewModel

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
 * Like [BaseActivity.getViewModel] for [BaseFragment]s
 */
inline fun <reified VM : ViewModel> BaseFragment.getViewModel(): VM = getViewModel(viewModelFactory)

/**
 * Like [BaseFragment.getViewModel] for [BaseFragment]'s that want a [ViewModel] scoped to the activity.
 */
inline fun <reified VM : ViewModel> BaseFragment.getSharedViewModel(parent: Fragment? = null): VM =
    getSharedViewModel(viewModelFactory, parent)