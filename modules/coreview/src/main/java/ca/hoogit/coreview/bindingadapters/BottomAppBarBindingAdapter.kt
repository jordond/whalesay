package ca.hoogit.coreview.bindingadapters

import androidx.databinding.BindingAdapter
import ca.hoogit.core.Constants
import ca.hoogit.ktx.animateOffscreenBottomDown
import ca.hoogit.ktx.animateOnscreenBottomUp
import com.google.android.material.bottomappbar.BottomAppBar

@BindingAdapter("animateVisibilityBottom")
fun BottomAppBar.animateVisibilityBottom(visible: Boolean) {
    if (visible) animateOnscreenBottomUp(Constants.NAV_HOST_ANIMATION_DURATION)
    else animateOffscreenBottomDown(Constants.NAV_HOST_ANIMATION_DURATION)
}