package ca.hoogit.whalesay.features.translate.ui.translate

import ca.hoogit.whalesay.core.view.lib.eiffel.buildInterceptors
import ca.hoogit.whalesay.core.view.state.MicPermissionState
import ca.hoogit.whalesay.core.view.state.canUseMic
import ca.hoogit.whalesay.core.view.util.ktx.currentState
import ca.hoogit.whalesay.features.translate.ui.error.model.ErrorType
import ca.hoogit.whalesay.features.translate.ui.translate.TranslateAction.Error
import ca.hoogit.whalesay.features.translate.ui.translate.TranslateAction.Loading
import ca.hoogit.whalesay.features.translate.ui.translate.TranslateAction.TextToSpeech
import ca.hoogit.whalesay.features.translate.ui.translate.TranslateAction.TextToSpeechAudio
import ca.hoogit.whalesay.features.translate.ui.translate.domain.TextToSpeechException
import ca.hoogit.whalesay.features.translate.ui.translate.domain.TextToSpeechUseCase
import ca.hoogit.whalesay.features.translate.util.toWhalese
import com.etiennelenhart.eiffel.state.Action
import com.etiennelenhart.eiffel.state.State
import com.etiennelenhart.eiffel.state.ViewEvent
import com.etiennelenhart.eiffel.state.update
import com.etiennelenhart.eiffel.viewmodel.EiffelViewModel
import com.github.ajalt.timberkt.d
import javax.inject.Inject

data class TranslateError(val type: ErrorType) : ViewEvent()

data class TranslateState(
    val initialized: Boolean = false,
    val micPermissionState: MicPermissionState = MicPermissionState.Pending,
    val humanText: String = "",
    val whaleText: String = "",
    val audioData: String = "",
    val errorEvent: TranslateError? = null,
    val isPlaying: Boolean = false,
    val isLoading: Boolean = false,
    val isRecording: Boolean = false
) : State

sealed class TranslateAction : Action {
    data class Init(val state: MicPermissionState) : TranslateAction()
    data class TextEntered(val text: String) : TranslateAction()
    data class Error(val type: ErrorType) : TranslateAction()
    data class TextToSpeechAudio(val data: String) : TranslateAction()
    object TextToSpeech : TranslateAction()
    object Loading : TranslateAction()
}

class TranslateViewModel @Inject constructor(
    textToSpeechUseCase: TextToSpeechUseCase
) : EiffelViewModel<TranslateState, TranslateAction>(
    initialState = TranslateState(),
    update = update { action ->
        when (action) {
            is TranslateAction.Init -> copy(
                initialized = true,
                micPermissionState = action.state
            )
            is TranslateAction.TextEntered -> copy(
                humanText = action.text,
                whaleText = action.text.toWhalese()
            )
            is TextToSpeechAudio -> copy(isLoading = false, audioData = action.data)
            is Loading -> copy(isLoading = true)
            is Error -> copy(isLoading = false, errorEvent = TranslateError(action.type))
            else -> this
        }
    },
    interceptions = buildInterceptors {
        addCommandOn<TextToSpeech>(Loading) { state, _, dispatch ->
            try {
                val result = textToSpeechUseCase.translateText(state.whaleText)
                dispatch(TextToSpeechAudio(result))
            } catch (error: Throwable) {
                val type = if (error is TextToSpeechException) error.type
                else ErrorType.Generic

                dispatch(Error(type))
            }
        }
    }
) {

    fun initialize(micPermissionState: MicPermissionState) {
        if (!currentState.initialized || micPermissionState != currentState.micPermissionState) {
            dispatch(TranslateAction.Init(micPermissionState))
        }
    }

    fun updateHumanText(text: String?) {
        if (text == null) return

        dispatch(TranslateAction.TextEntered(text.trim()))
    }

    fun onFabClicked() = with(currentState) {
        when {
            isPlaying -> d { "STOP THE MUSIC" }
            isRecording -> d { "STOP RECORDING" }
            whaleText.isNotEmpty() -> dispatch(TranslateAction.TextToSpeech)
            audioData.isNotEmpty() -> d { "PLAY SOME WHALE TEXT" }
            micPermissionState.canUseMic && !isRecording -> d { "START RECORDING" }
        }
    }
}
