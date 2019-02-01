package ca.hoogit.whalesay.features.translate.ui.error

import ca.hoogit.whalesay.features.translate.ui.error.model.ErrorType
import ca.hoogit.whalesay.features.translate.ui.error.state.ErrorNavEvents
import ca.hoogit.whalesay.features.translate.ui.error.state.ErrorState
import com.etiennelenhart.eiffel.state.Action
import com.etiennelenhart.eiffel.state.update
import com.etiennelenhart.eiffel.viewmodel.EiffelViewModel
import javax.inject.Inject

sealed class ErrorAction : Action {
    data class Init(val type: ErrorType) : ErrorAction()
    data class Navigate(val type: ErrorNavEvents) : ErrorAction()
}

private val updater = update<ErrorState, ErrorAction> { state, action ->
    when (action) {
        is ErrorAction.Init -> state.copy(type = action.type)
        is ErrorAction.Navigate -> state.copy(navEvent = action.type)
    }
}

class ErrorViewModel @Inject constructor() : EiffelViewModel<ErrorState, ErrorAction>(
    initialState = ErrorState(),
    update = updater
) {

    fun onTryAgainClicked() {
        dispatch(ErrorAction.Navigate(ErrorNavEvents.Back()))
    }
}
