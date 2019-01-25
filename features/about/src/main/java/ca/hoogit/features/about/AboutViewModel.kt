package ca.hoogit.features.about

import com.etiennelenhart.eiffel.state.Action
import com.etiennelenhart.eiffel.state.Update
import com.etiennelenhart.eiffel.viewmodel.EiffelViewModel
import javax.inject.Inject

sealed class AboutAction : Action

internal class AboutViewModel @Inject constructor() : EiffelViewModel<AboutState, AboutAction>(
    initialState = AboutState(),
    update = object : Update<AboutState, AboutAction> {

        override fun invoke(state: AboutState, action: AboutAction): AboutState {
            return state
        }
    }
)