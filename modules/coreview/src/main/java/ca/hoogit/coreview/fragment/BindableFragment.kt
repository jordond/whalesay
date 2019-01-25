package ca.hoogit.coreview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ca.hoogit.coreview.viewmodel.BindableViewModel

abstract class BindableFragment<T : ViewDataBinding> : BaseFragment() {

    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)

        bindData()

        if (this is BindableViewModel) {
            bindViewModel()
            binding.setLifecycleOwner(owner)
        }

        return binding.root
    }

    open fun bindData() {}
}