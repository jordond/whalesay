package ca.hoogit.features.translate.ui.translate

import ca.hoogit.whalesay.coreview.state.MicPermissionState
import ca.hoogit.whalesay.coreview.util.ktx.currentState
import ca.hoogit.features.translate.util.toWhalese
import com.etiennelenhart.eiffel.state.Action
import com.etiennelenhart.eiffel.state.State
import com.etiennelenhart.eiffel.state.update
import com.etiennelenhart.eiffel.viewmodel.EiffelViewModel
import javax.inject.Inject

data class TranslateState(
    val initialized: Boolean = false,
    val micPermissionState: MicPermissionState = MicPermissionState.Pending,
    val humanText: String = "",
    val whaleText: String = "",
    val isPlaying: Boolean = false,
    val isLoading: Boolean = false,
    val isRecording: Boolean = false
) : State

sealed class TranslateAction : Action {
    data class Init(val state: MicPermissionState) : TranslateAction()
    data class TextEntered(val text: String) : TranslateAction()
    object Loading : TranslateAction()
}

class TranslateViewModel @Inject constructor() : EiffelViewModel<TranslateState, TranslateAction>(
    initialState = TranslateState(),
    update = update { state, action ->
        when (action) {
            is TranslateAction.Init -> state.copy(
                initialized = true,
                micPermissionState = action.state
            )
            is TranslateAction.TextEntered -> state.copy(
                humanText = action.text,
                whaleText = action.text.toWhalese()
            )
            is TranslateAction.Loading -> state.copy(isLoading = true)
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
}
