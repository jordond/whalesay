package ca.hoogit.whalesay.features.translate.ui.translate

import ca.hoogit.whalesay.core.ktx.doAfterTextChanged
import ca.hoogit.whalesay.core.ktx.navigateTo
import ca.hoogit.whalesay.core.ktx.onClick
import ca.hoogit.whalesay.core.view.bindingadapters.visible
import ca.hoogit.whalesay.core.view.bindingadapters.visibleOrGone
import ca.hoogit.whalesay.core.view.fragment.BindableFragment
import ca.hoogit.whalesay.core.view.state.canUseMic
import ca.hoogit.whalesay.core.view.state.microphonePermissionState
import ca.hoogit.whalesay.core.view.util.ktx.getProvidedFAB
import ca.hoogit.whalesay.core.view.viewmodel.injectedViewModel
import ca.hoogit.whalesay.features.translate.R
import ca.hoogit.whalesay.features.translate.databinding.FragmentTranslateBinding
import ca.hoogit.whalesay.features.translate.ui.error.ErrorNavArgs
import ca.hoogit.whalesay.features.translate.ui.error.model.ErrorType
import com.etiennelenhart.eiffel.state.extension.observe
import com.etiennelenhart.eiffel.state.peek
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class TranslateFragment : BindableFragment<FragmentTranslateBinding>(), CoroutineScope {

    override fun getLayoutRes(): Int = R.layout.fragment_translate

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val viewModel by injectedViewModel<TranslateViewModel>()

    private val fab by lazy { getProvidedFAB() }

    override fun setupViews() = with(binding) {
        txtTextInput.doAfterTextChanged { editable ->
            viewModel.updateHumanText(editable?.toString())
        }

        fab?.onClick { viewModel.onFabClicked() }

        Unit
    }

    // TODO - Hide Whale logo if keyboard is open
    override fun subscribeViewModel() = with(viewModel) {
        initialize(requireContext().microphonePermissionState)

        state.observe(owner) { state ->
            renderWhaleTextBox(state)
            fab?.let { renderFAB(it, state) }

            state.errorEvent?.peek { showErrorScreen(it.type) }
        }
    }

    private fun renderWhaleTextBox(state: TranslateState) {
        with(binding.txt2) {
            text = state.whaleText
            visibleOrGone = !state.whaleText.isEmpty()
            if (visible) binding.txtTextInput.requestFocus()
        }
        binding.imgLogo.visibleOrGone = state.whaleText.isEmpty()
    }

    private fun renderFAB(fab: FloatingActionButton, state: TranslateState) = with(state) {
        val imgRes = when {
            isRecording -> R.drawable.ic_stop
            whaleText.isNotEmpty() -> if (isPlaying) R.drawable.ic_stop else R.drawable.ic_play
            micPermissionState.canUseMic -> R.drawable.ic_mic_white
            else -> null
        }?.apply { fab.setImageResource(this) }

        if (imgRes != null) fab.show() else fab.hide()
    }

    private fun showErrorScreen(error: ErrorType): Boolean {
        navigateTo {
            TranslateFragmentDirections.showErrorScreen(ErrorNavArgs(error))
        }

        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}