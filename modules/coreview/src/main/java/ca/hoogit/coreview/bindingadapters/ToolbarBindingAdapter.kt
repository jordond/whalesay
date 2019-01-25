package ca.hoogit.coreview.bindingadapters

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import ca.hoogit.core.Constants
import ca.hoogit.coreview.R
import ca.hoogit.ktx.animateY
import ca.hoogit.ktx.dimen

@BindingAdapter("animateVisibility")
fun Toolbar.animateVisibility(visible: Boolean) {
    val needsToAnimate = (visible && isHidden) || (!visible && !isHidden)
    if (!needsToAnimate) return

    val animateTo = if (visible && isHidden) 0 else -heightDefault
    animateY(animateTo.toFloat(), duration = Constants.NAV_HOST_ANIMATION_DURATION)
}

private val Toolbar.isHidden: Boolean
    get() = y < 0f

private val Toolbar.heightDefault
    get() = if (height == 0) context.dimen(R.dimen.actionBarSize).toInt() else height