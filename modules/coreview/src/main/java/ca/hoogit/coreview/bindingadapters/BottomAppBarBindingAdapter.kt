package ca.hoogit.coreview.bindingadapters

import androidx.databinding.BindingAdapter
import ca.hoogit.core.Constants
import ca.hoogit.coreview.R
import ca.hoogit.ktx.animateOffscreenBottomDown
import ca.hoogit.ktx.animateOnscreenBottomUp
import ca.hoogit.ktx.dimen
import com.google.android.material.bottomappbar.BottomAppBar

@BindingAdapter("animateVisibilityBottom")
fun BottomAppBar.animateVisibilityBottom(visible: Boolean) {
    if (visible) animateOnscreenBottomUp(Constants.NAV_HOST_ANIMATION_DURATION)
    else animateOffscreenBottomDown(
        duration = Constants.NAV_HOST_ANIMATION_DURATION,
        defaultHeight = context.dimen(R.dimen.actionBarSize).toInt()
    )
}