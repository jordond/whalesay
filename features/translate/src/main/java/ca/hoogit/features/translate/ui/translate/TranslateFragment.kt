package ca.hoogit.features.translate.ui.translate

import ca.hoogit.coreview.bindingadapters.visible
import ca.hoogit.coreview.bindingadapters.visibleOrGone
import ca.hoogit.coreview.fragment.BindableFragment
import ca.hoogit.coreview.state.canUseMic
import ca.hoogit.coreview.state.microphonePermissionState
import ca.hoogit.coreview.util.ktx.getProvidedFAB
import ca.hoogit.coreview.viewmodel.injectedViewModel
import ca.hoogit.features.translate.R
import ca.hoogit.features.translate.databinding.FragmentTranslateBinding
import ca.hoogit.features.translate.ui.error.ErrorNavArgs
import ca.hoogit.features.translate.ui.error.model.ErrorType
import ca.hoogit.ktx.doAfterTextChanged
import ca.hoogit.ktx.navigateTo
import com.etiennelenhart.eiffel.state.extension.observe
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

    override fun setupViews() = with(binding) {
        txtTextInput.doAfterTextChanged { editable ->
            viewModel.updateHumanText(editable?.toString())
        }

        Unit
    }

    // TODO - Hide Whale logo if keyboard is open
    override fun subscribeViewModel() = with(viewModel) {
        initialize(requireContext().microphonePermissionState)

        state.observe(owner) { state ->
            renderWhaleTextBox(state)
            getProvidedFAB()?.let { renderFAB(it, state) }
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

    private fun showErrorScreen(error: ErrorType) {
        navigateTo {
            TranslateFragmentDirections.showErrorScreen(ErrorNavArgs(error))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}