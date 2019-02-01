package ca.hoogit.whalesay.coreview.bindingadapters

import androidx.databinding.BindingAdapter
import ca.hoogit.whalesay.core.Constants
import ca.hoogit.whalesay.coreview.R
import ca.hoogit.whalesay.ktx.animateOffscreenBottomDown
import ca.hoogit.whalesay.ktx.animateOnscreenBottomUp
import ca.hoogit.whalesay.ktx.dimen
import com.google.android.material.bottomappbar.BottomAppBar

@BindingAdapter("animateVisibilityBottom")
fun BottomAppBar.animateVisibilityBottom(visible: Boolean) {
    if (visible) animateOnscreenBottomUp(Constants.NAV_HOST_ANIMATION_DURATION)
    else animateOffscreenBottomDown(
        duration = Constants.NAV_HOST_ANIMATION_DURATION,
        defaultHeight = context.dimen(R.dimen.actionBarSize).toInt()
    )
}