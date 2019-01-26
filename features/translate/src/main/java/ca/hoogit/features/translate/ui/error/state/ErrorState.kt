package ca.hoogit.features.translate.ui.error.state

import ca.hoogit.features.translate.ui.error.model.ErrorType
import com.etiennelenhart.eiffel.state.State
import com.etiennelenhart.eiffel.state.ViewEvent

sealed class ErrorNavEvents : ViewEvent() {
    class Back : ErrorNavEvents()
}

data class ErrorState(
    val type: ErrorType = ErrorType.None,
    val navEvent: ErrorNavEvents? = null
) : State