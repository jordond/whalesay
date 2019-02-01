package ca.hoogit.whalesay.coreview.util

import android.content.Context
import android.net.Uri
import androidx.annotation.AnimRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsSession
import ca.hoogit.whalesay.coreview.R
import ca.hoogit.whalesay.ktx.color
import ca.hoogit.whalesay.ktx.vectorDrawableToBitmap

data class ChromeTabOptions(
    @ColorRes var toolbarColor: Int = R.color.colorPrimary,
    @DrawableRes var closeButtonIcon: Int = R.drawable.ic_arrow_back,
    @AnimRes var startEnterAnim: Int = R.anim.slide_in_right,
    @AnimRes var startExitAnim: Int = R.anim.slide_out_left,
    @AnimRes var exitEnterAnim: Int = R.anim.slide_in_left,
    @AnimRes var exitExitAnim: Int = R.anim.slide_out_right
)

fun createChromeTab(
    context: Context,
    chromeTabSession: CustomTabsSession? = null,
    block: ChromeTabOptions.() -> Unit
) = with(ChromeTabOptions().apply(block)) {
    CustomTabsIntent.Builder(chromeTabSession)
        .setToolbarColor(context.color(toolbarColor))
        .setStartAnimations(context, startEnterAnim, startExitAnim)
        .setExitAnimations(context, exitEnterAnim, exitExitAnim)
        .setCloseButtonIcon(context.vectorDrawableToBitmap(closeButtonIcon))
        .build()
}

fun launchChromeTab(
    context: Context,
    uri: Uri,
    chromeTabSession: CustomTabsSession? = null,
    block: (ChromeTabOptions.() -> Unit) = {}
) {
    createChromeTab(context, chromeTabSession, block).launchUrl(context, uri)
}
