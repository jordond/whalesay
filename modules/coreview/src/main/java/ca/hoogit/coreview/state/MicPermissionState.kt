package ca.hoogit.coreview.state

import android.content.Context
import ca.hoogit.core.util.Permissions
import ca.hoogit.core.util.hasPermission
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsOptions

sealed class MicPermissionState {
    object Pending : MicPermissionState()
    object Granted : MicPermissionState()
    object Denied : MicPermissionState()
    object PermanentlyDenied : MicPermissionState()
}

val Context.microphonePermissionState: MicPermissionState
    get() = when {
        hasPermission(Permissions.MICROPHONE) -> MicPermissionState.Granted
        else -> MicPermissionState.Pending
    }

fun Context.requestMicrophonePermission(
    onGranted: () -> Unit,
    onDenied: ((permanent: Boolean) -> Unit) = {}
) {
    runWithPermissions(
        permissions = *arrayOf(Permissions.MICROPHONE),
        options = QuickPermissionsOptions(
            handleRationale = false,
            permissionsDeniedMethod = { onDenied(false) },
            permanentDeniedMethod = { onDenied(true) }
        ),
        callback = onGranted
    )
}