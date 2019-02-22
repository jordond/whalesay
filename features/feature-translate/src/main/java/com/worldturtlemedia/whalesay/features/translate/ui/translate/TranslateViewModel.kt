package com.worldturtlemedia.whalesay.features.translate.ui.translate

import com.etiennelenhart.eiffel.state.Action
import com.etiennelenhart.eiffel.state.State
import com.etiennelenhart.eiffel.state.ViewEvent
import com.etiennelenhart.eiffel.state.update
import com.etiennelenhart.eiffel.viewmodel.EiffelViewModel
import com.github.ajalt.timberkt.d
import com.worldturtlemedia.whalesay.core.util.Fail
import com.worldturtlemedia.whalesay.core.view.lib.eiffel.buildInterceptions
import com.worldturtlemedia.whalesay.core.view.state.MicPermissionState
import com.worldturtlemedia.whalesay.core.view.state.canUseMic
import com.worldturtlemedia.whalesay.core.view.util.ktx.currentState
import com.worldturtlemedia.whalesay.features.translate.audio.PlayerState
import com.worldturtlemedia.whalesay.features.translate.ui.error.model.ErrorType
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.Error
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.Loading
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.StopAudioPlayback
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.TextToSpeech
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.TextToSpeechAudio
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.UpdatePlayerState
import com.worldturtlemedia.whalesay.features.translate.ui.translate.domain.AudioPlayerUseCase
import com.worldturtlemedia.whalesay.features.translate.ui.translate.domain.TextToSpeechException
import com.worldturtlemedia.whalesay.features.translate.ui.translate.domain.TextToSpeechUseCase
import com.worldturtlemedia.whalesay.features.translate.util.toWhalese
import java.io.File
import javax.inject.Inject

data class TranslateError(val type: ErrorType) : ViewEvent()

data class TranslateState(
    val initialized: Boolean = false,
    val micPermissionState: MicPermissionState = MicPermissionState.Pending,
    val humanText: String = "",
    val whaleText: String = "",
    // TODO - Convert to an Async containing the File object
    val audioFile: File? = null,
    val errorEvent: TranslateError? = null,
    val audioPlayerState: PlayerState = PlayerState.Idle,
    val isLoading: Boolean = false,
    val isRecording: Boolean = false
) : State

sealed class TranslateAction : Action {
    data class Init(val state: MicPermissionState) : TranslateAction()
    data class TextEntered(val text: String) : TranslateAction()
    data class Error(val type: ErrorType) : TranslateAction()
    data class TextToSpeechAudio(val file: File) : TranslateAction()
    data class UpdatePlayerState(val state: PlayerState) : TranslateAction()
    object StopAudioPlayback : TranslateAction()
    object TextToSpeech : TranslateAction()
    object Loading : TranslateAction()
}

class TranslateViewModel @Inject constructor(
    textToSpeechUseCase: TextToSpeechUseCase,
    private val audioPlayerUseCase: AudioPlayerUseCase
) : EiffelViewModel<TranslateState, TranslateAction>(TranslateState()) {

    override val update = update<TranslateState, TranslateAction> { action ->
        when (action) {
            is TranslateAction.Init -> copy(
                initialized = true,
                micPermissionState = action.state
            )
            is TranslateAction.TextEntered -> copy(
                humanText = action.text,
                whaleText = action.text.toWhalese()
            )
            is UpdatePlayerState -> copy(audioPlayerState = action.state)
            is TextToSpeechAudio -> copy(isLoading = false, audioFile = action.file)
            is Loading -> copy(isLoading = true)
            is Error -> copy(isLoading = false, errorEvent = TranslateError(action.type))
            else -> this
        }
    }

    override val interceptions = buildInterceptions<TranslateState, TranslateAction> {
        addConsumingCommandOn<TextToSpeech>(Loading) { state, _, dispatch ->
            try {
                val result = textToSpeechUseCase.translateText(state.whaleText)

                dispatch(TextToSpeechAudio(result))
            } catch (error: Throwable) {
                val type =
                    if (error is TextToSpeechException) error.type
                    else ErrorType.Generic

                dispatch(Error(type))
            }
        }

        addConsumingCommandOn<TextToSpeechAudio>(Loading) { _, action, dispatch ->
            when (audioPlayerUseCase.play(action.file.absolutePath)) {
                is Fail -> dispatch(Error(ErrorType.AudioPlayer))
            }
        }

        addBeforePipeOn<StopAudioPlayback> { _, _ ->
            audioPlayerUseCase.stop()
        }
    }

    init {
        addStateSource(audioPlayerUseCase.playerState) { playerState ->
            when (playerState) {
                is PlayerState.Error -> Error(ErrorType.AudioPlayer)
                else -> UpdatePlayerState(playerState)
            }
        }
    }

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
            audioPlayerState is PlayerState.Playing -> dispatch(StopAudioPlayback)
            isRecording -> d { "STOP RECORDING" }
            audioFile != null -> d { "PLAY SOME WHALE TEXT" }
            whaleText.isNotEmpty() -> dispatch(TranslateAction.TextToSpeech)
            micPermissionState.canUseMic && !isRecording -> d { "START RECORDING" }
        }
    }

    override fun onCleared() {
        audioPlayerUseCase.release()
        super.onCleared()
    }
}
