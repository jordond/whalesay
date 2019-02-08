package ca.hoogit.whalesay.features.translate.ui.error.state

import androidx.annotation.StringRes
import ca.hoogit.whalesay.features.translate.R
import ca.hoogit.whalesay.features.translate.ui.error.model.ErrorType
import com.etiennelenhart.eiffel.binding.BindableMapping
import com.etiennelenhart.eiffel.binding.BindableState
import com.etiennelenhart.eiffel.binding.bindableMapping

data class ErrorBindingState(
    @StringRes val titleID: Int = R.string.error_generic,
    @StringRes val subTitleID: Int = R.string.empty,
    val showSubTitle: Boolean = true
) : BindableState {

    companion object {

        val mapping: BindableMapping<ErrorState, ErrorBindingState>
            get() = bindableMapping(ErrorBindingState()) { state ->
                val id = when (state.type) {
                    ErrorType.Network -> R.string.error_network
                    ErrorType.Google -> R.string.error_google
                    ErrorType.SpeechToText -> R.string.error_speech_to_text
                    ErrorType.TextToSpeech -> R.string.error_text_to_speech
                    else -> R.string.empty
                }

                copy(subTitleID = id, showSubTitle = id != R.string.empty)
            }
    }
}
