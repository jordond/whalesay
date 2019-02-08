package com.worldturtlemedia.whalesay.features.translate.ui.error

import com.worldturtlemedia.whalesay.features.translate.ui.error.model.ErrorType
import com.worldturtlemedia.whalesay.features.translate.ui.error.state.ErrorNavEvents
import com.worldturtlemedia.whalesay.features.translate.ui.error.state.ErrorState
import com.etiennelenhart.eiffel.state.Action
import com.etiennelenhart.eiffel.state.update
import com.etiennelenhart.eiffel.viewmodel.EiffelViewModel
import javax.inject.Inject

sealed class ErrorAction : Action {
    data class Init(val type: ErrorType) : ErrorAction()
    data class Navigate(val type: ErrorNavEvents) : ErrorAction()
}

private val updater = update<ErrorState, ErrorAction> { action ->
    when (action) {
        is ErrorAction.Init -> copy(type = action.type)
        is ErrorAction.Navigate -> copy(navEvent = action.type)
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
