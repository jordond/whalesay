package ca.hoogit.features.translate.ui.error.state

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import ca.hoogit.features.translate.R
import ca.hoogit.features.translate.ui.error.model.ErrorType
import com.etiennelenhart.eiffel.binding.BindingState

class ErrorBindingState : BaseObservable(), BindingState<ErrorState> {

    var titleID = ObservableInt(R.string.error_generic)

    var subTitleID = ObservableInt(0)

    var showSubTitle = ObservableBoolean(true)

    override fun refresh(state: ErrorState) {
        titleID.set(R.string.error_generic)

        val resID = when (state.type) {
            ErrorType.Network -> R.string.error_network
            ErrorType.Google -> R.string.error_google
            ErrorType.SpeechToText -> R.string.error_speech_to_text
            ErrorType.TextToSpeech -> R.string.error_text_to_speech
            else -> 0
        }

        subTitleID.set(resID)
        showSubTitle.set(resID != 0)
    }
}