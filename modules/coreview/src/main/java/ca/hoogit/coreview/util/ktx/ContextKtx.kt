package ca.hoogit.coreview.util.ktx

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsSession
import ca.hoogit.coreview.util.ChromeTabOptions
import ca.hoogit.coreview.util.launchChromeTab
import ca.hoogit.ktx.toURI
import com.github.ajalt.timberkt.Timber.e

fun Context.launchChromeTab(
    uri: Uri,
    chromeTabsSession: CustomTabsSession? = null,
    block: ChromeTabOptions.() -> Unit = {}
) = launchChromeTab(this, uri, chromeTabsSession, block)

fun Context.launchChromeTab(
    url: String,
    chromeTabsSession: CustomTabsSession? = null,
    block: ChromeTabOptions.() -> Unit = {}
) = url.toURI()?.let { uri ->
    launchChromeTab(this, uri, chromeTabsSession, block)
} ?: e { "Unable to create URI: $url" }