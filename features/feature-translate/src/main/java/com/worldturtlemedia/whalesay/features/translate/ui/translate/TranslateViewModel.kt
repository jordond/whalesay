package com.worldturtlemedia.whalesay.features.translate.ui.translate

import com.etiennelenhart.eiffel.interception.interceptions
import com.etiennelenhart.eiffel.state.Action
import com.etiennelenhart.eiffel.state.State
import com.etiennelenhart.eiffel.state.ViewEvent
import com.etiennelenhart.eiffel.state.update
import com.etiennelenhart.eiffel.viewmodel.EiffelViewModel
import com.github.ajalt.timberkt.e
import com.worldturtlemedia.whalesay.core.util.Fail
import com.worldturtlemedia.whalesay.core.view.state.MicPermissionState
import com.worldturtlemedia.whalesay.core.view.state.canUseMic
import com.worldturtlemedia.whalesay.core.view.util.ktx.currentState
import com.worldturtlemedia.whalesay.features.translate.audio.PlayerState
import com.worldturtlemedia.whalesay.features.translate.ui.error.model.ErrorType
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.ButtonClicked
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.Error
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.Init
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.Loading
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.PlayAudio
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.StopAudioPlayback
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.TextEntered
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.TextToSpeech
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.TextToSpeechAudio
import com.worldturtlemedia.whalesay.features.translate.ui.translate.TranslateAction.UpdatePlayerState
import com.worldturtlemedia.whalesay.features.translate.ui.translate.domain.AudioPlayerUseCase
import com.worldturtlemedia.whalesay.features.translate.ui.translate.domain.TextToSpeechException
import com.worldturtlemedia.whalesay.features.translate.ui.translate.domain.TextToSpeechUseCase
import com.worldturtlemedia.whalesay.features.translate.util.toWhalese
import java.io.File
import javax.inject.Inject

data class TranslateErrorEvent(val type: ErrorType) : ViewEvent()

data class TranslateState(
    val initialized: Boolean = false,
    val micPermissionState: MicPermissionState = MicPermissionState.Pending,
    val humanText: String = "",
    val audioFile: File? = null,
    val errorEvent: TranslateErrorEvent? = null,
    val audioPlayerState: PlayerState = PlayerState.Idle,
    val isLoading: Boolean = false,
    val isRecording: Boolean = false
) : State {

    val whaleText: String
        get() = humanText.toWhalese()
}

sealed class TranslateAction : Action {
    data class Init(val state: MicPermissionState) : TranslateAction()
    data class TextEntered(val text: String) : TranslateAction()
    data class Error(val type: ErrorType, val message: String = "") : TranslateAction()
    data class TextToSpeechAudio(val file: File) : TranslateAction()
    data class UpdatePlayerState(val state: PlayerState) : TranslateAction()
    data class TODO(val message: String) : TranslateAction()
    object ButtonClicked : TranslateAction()
    object PlayAudio : TranslateAction()
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
            is Init -> copy(
                initialized = true,
                micPermissionState = action.state
            )
            is TextEntered -> copy(humanText = action.text)
            is UpdatePlayerState -> copy(audioPlayerState = action.state)
            is TextToSpeechAudio -> copy(isLoading = false, audioFile = action.file)
            is Loading -> copy(isLoading = true)
            is Error -> copy(isLoading = false, errorEvent = TranslateErrorEvent(action.type))
            else -> this
        }
    }

    override val interceptions = interceptions<TranslateState, TranslateAction> {
        on<TextToSpeech> {
            consumingCommand(immediateAction = Loading) { state, _, dispatch ->
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
        }

        on<PlayAudio> {
            forwardingCommand { state, _, dispatch ->
                if (state.audioFile == null) {
                    dispatch(Error(ErrorType.AudioPlayer, "Audio file was null!"))
                } else {
                    when (audioPlayerUseCase.play(state.audioFile.absolutePath)) {
                        is Fail -> dispatch(Error(ErrorType.AudioPlayer))
                    }
                }
            }
        }

        on<StopAudioPlayback> {
            forwardingCommand { _, _, _ ->
                audioPlayerUseCase.stop()
            }
        }

        on<Error> {
            pipe { _, action ->
                if (action is Error && action.message.isNotEmpty()) e { action.message }
            }
        }

        on<ButtonClicked> {
            adapter { state, action ->
                when {
                    state.audioPlayerState is PlayerState.Playing -> StopAudioPlayback
                    state.isRecording -> TranslateAction.TODO("STOP RECORDING")
                    state.audioFile != null -> PlayAudio
                    state.humanText.isNotEmpty() -> TextToSpeech
                    state.micPermissionState.canUseMic && !state.isRecording ->
                        TranslateAction.TODO("START RECORDING")
                    else -> null
                } ?: action
            }
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
            dispatch(Init(micPermissionState))
        }
    }

    fun updateHumanText(text: String?) {
        if (text == null) return

        dispatch(TextEntered(text.trim()))
    }

    fun onFabClicked() = dispatch(ButtonClicked)

    override fun onCleared() {
        audioPlayerUseCase.release()
        super.onCleared()
    }
}
