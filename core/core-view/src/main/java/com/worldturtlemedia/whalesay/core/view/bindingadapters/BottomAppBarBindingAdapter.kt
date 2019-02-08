package com.worldturtlemedia.whalesay.core.view.bindingadapters

import androidx.databinding.BindingAdapter
import com.worldturtlemedia.whalesay.core.Constants
import com.worldturtlemedia.whalesay.core.view.R
import com.worldturtlemedia.whalesay.core.ktx.animateOffscreenBottomDown
import com.worldturtlemedia.whalesay.core.ktx.animateOnscreenBottomUp
import com.worldturtlemedia.whalesay.core.ktx.dimen
import com.google.android.material.bottomappbar.BottomAppBar

@BindingAdapter("animateVisibilityBottom")
fun BottomAppBar.animateVisibilityBottom(visible: Boolean) {
    if (visible) animateOnscreenBottomUp(Constants.NAV_HOST_ANIMATION_DURATION)
    else animateOffscreenBottomDown(
        duration = Constants.NAV_HOST_ANIMATION_DURATION,
        defaultHeight = context.dimen(R.dimen.actionBarSize).toInt()
    )
}
