package ca.hoogit.coreview.activity

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ca.hoogit.coreview.viewmodel.BindableViewModel

abstract class BindableActivity<T : ViewDataBinding> : BaseActivity() {

    lateinit var binding: T

    override fun createView(@LayoutRes layoutRes: Int) {
        binding = DataBindingUtil.setContentView(this, layoutRes)

        if (this is BindableViewModel) {
            bindViewModel()
            binding.setLifecycleOwner(this)
        }
    }

    abstract fun bindData()
}