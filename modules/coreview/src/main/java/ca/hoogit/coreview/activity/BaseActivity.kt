package ca.hoogit.coreview.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import ca.hoogit.core.di.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @LayoutRes abstract fun getLayoutRes(): Int

    private val layoutResID by lazy { getLayoutRes() }

    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createView(layoutResID)
        setup()
    }

    protected open fun createView(@LayoutRes layoutRes: Int) {
        setContentView(layoutRes)
    }

    private fun setup() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        setupViews()
        subscribeViewModel()
        afterCreate()
    }

    open fun setupViews() {}

    open fun subscribeViewModel() {}

    open fun afterCreate() {}
}