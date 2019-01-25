package ca.hoogit.whalesay.ui

import ca.hoogit.coreview.activity.BaseActivity
import ca.hoogit.coreview.viewmodel.getViewModel

class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int = TODO()

    private val viewModel by lazy { getViewModel<MainViewModel>() }
}
