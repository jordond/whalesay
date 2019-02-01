package ca.hoogit.whalesay.coreview.activity

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindableActivity<T : ViewDataBinding> : BaseActivity() {

    lateinit var binding: T

    override fun createView(@LayoutRes layoutRes: Int) {
        binding = DataBindingUtil.setContentView(this, layoutRes)

        bindData()
        binding.setLifecycleOwner(this)
    }

    open fun bindData() {}
}