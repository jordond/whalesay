package ca.hoogit.features.about.util

import ca.hoogit.features.about.BuildConfig
import org.joda.time.DateTime

class AppInfo {

    val appID: String = BuildConfig.APPLICATION_ID
    val version: String = BuildConfig.VERSION_NAME
    val buildDate: String = displayBuildDate()
    val gitBranch: String = BuildConfig.GIT_BRANCH
    val gitCommit: String = BuildConfig.GIT_HASH

    private fun displayBuildDate() = DateTime(BuildConfig.BUILD_DATE)
        .run { toString("MMMM d, yyyy - h:mm aa") }
}