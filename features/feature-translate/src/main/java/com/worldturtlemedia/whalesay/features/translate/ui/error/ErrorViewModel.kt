package com.worldturtlemedia.whalesay.features.translate.ui.error

import androidx.lifecycle.LiveData
import com.etiennelenhart.eiffel.binding.extension.toBindable
import com.etiennelenhart.eiffel.state.Action
import com.etiennelenhart.eiffel.state.update
import com.etiennelenhart.eiffel.viewmodel.EiffelViewModel
import com.worldturtlemedia.whalesay.features.translate.ui.error.model.ErrorType
import com.worldturtlemedia.whalesay.features.translate.ui.error.state.ErrorBindingState
import com.worldturtlemedia.whalesay.features.translate.ui.error.state.ErrorNavEvents
import com.worldturtlemedia.whalesay.features.translate.ui.error.state.ErrorState
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

    val errorBindingState: LiveData<ErrorBindingState>
        get() = state.toBindable(ErrorBindingState.mapping)

    fun onTryAgainClicked() {
        dispatch(ErrorAction.Navigate(ErrorNavEvents.Back()))
    }
}
