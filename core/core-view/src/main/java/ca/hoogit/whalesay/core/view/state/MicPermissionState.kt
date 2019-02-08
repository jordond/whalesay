package ca.hoogit.whalesay.core.view.state

import android.content.Context
import ca.hoogit.whalesay.core.util.Permissions
import ca.hoogit.whalesay.core.util.hasPermission
import ca.hoogit.whalesay.core.util.permissionIsDenied
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsOptions

sealed class MicPermissionState {
    object Pending : MicPermissionState()
    object Granted : MicPermissionState()
    object Denied : MicPermissionState()
    object PermanentlyDenied : MicPermissionState()
}

val MicPermissionState.canUseMic: Boolean
    get() = this is MicPermissionState.Granted

val Context.microphonePermissionState: MicPermissionState
    get() = when {
        hasPermission(Permissions.MICROPHONE) -> MicPermissionState.Granted
        permissionIsDenied(Permissions.MICROPHONE) -> MicPermissionState.Denied
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
