package ca.hoogit.coreview.viewmodel

import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber.d
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    protected val parentJob = SupervisorJob()

    protected var childJob: Job? = null

    override val coroutineContext: CoroutineContext = Dispatchers.Main + parentJob

    protected val scope by lazy { CoroutineScope(coroutineContext) }

    protected fun launchCoroutine(
        cancelExisting: Boolean = false,
        block: suspend () -> Unit
    ) = launchChildJob(cancelExisting) {
        d { "Starting new async job" }
        scope.launch {
            try {
                block()
            } catch (error: CancellationException) {
                d { "Async job was cancelled" }
            }
        }
    }

    protected fun launchChildJob(
        cancelExisting: Boolean = false,
        launchCoroutine: () -> Job
    ) = childJob.let { job ->
        if (job == null) {
            childJob = launchCoroutine()
            return
        }

        when {
            !job.isActive -> childJob = launchCoroutine()
            cancelExisting && job.isActive -> {
                job.cancel()
                childJob = launchCoroutine()
            }
            job.isActive -> d { "Child job already running, pass 'cancelExisting=true' to abort" }
        }
    }

    override fun onCleared() {
        super.onCleared()

        parentJob.cancel()
    }
}