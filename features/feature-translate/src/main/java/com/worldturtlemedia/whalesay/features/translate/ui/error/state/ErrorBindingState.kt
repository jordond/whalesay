package com.worldturtlemedia.whalesay.features.translate.ui.error.state

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import com.etiennelenhart.eiffel.binding.BindableMapping1
import com.etiennelenhart.eiffel.binding.BindableState
import com.etiennelenhart.eiffel.binding.bindableMapping
import com.worldturtlemedia.whalesay.features.translate.R
import com.worldturtlemedia.whalesay.features.translate.ui.error.model.ErrorType

typealias LiveErrorBindingState = LiveData<ErrorBindingState>

data class ErrorBindingState(
    @StringRes val titleID: Int = R.string.error_generic,
    @StringRes val subTitleID: Int = R.string.empty,
    val showSubTitle: Boolean = true
) : BindableState {

    companion object {

        val mapping: BindableMapping1<ErrorState, ErrorBindingState>
            get() = bindableMapping { state ->
                val id = when (state.type) {
                    ErrorType.Network -> R.string.error_network
                    ErrorType.Google -> R.string.error_google
                    ErrorType.SpeechToText -> R.string.error_speech_to_text
                    ErrorType.TextToSpeech -> R.string.error_text_to_speech
                    ErrorType.AudioPlayer -> R.string.error_audio_player
                    else -> R.string.empty
                }

                copy(subTitleID = id, showSubTitle = id != R.string.empty)
            }
    }
}
