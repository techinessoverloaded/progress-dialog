package com.techiness.progressdialoglibrary

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.techiness.progressdialoglibrary.databinding.LayoutDeterminateProgressDialogBinding
import com.techiness.progressdialoglibrary.helpers.mainDispatcher
import com.techiness.progressdialoglibrary.helpers.setVisibility
import com.techiness.progressdialoglibrary.theme.ProgressDialogTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.seconds

class DeterminateProgressDialog(
    context: Context,
    theme: ProgressDialogTheme
): ProgressDialogNew(context, theme), DeterminateProgressDialogDslContract {

    private val progressDialogBinding = LayoutDeterminateProgressDialogBinding.bind(
        getProgressDialogView(R.layout.layout_determinate_progress_dialog)
    )

    private var isTimeTrackingEnabled: Boolean = false

    private var timeTrackingJob: Job? = null

    init {
        initAlertDialog()
    }

    private fun startTimeTrackingJobIfRequired() {
        cancelTimeTrackingJob()
        if (isTimeTrackingEnabled) {
            val startTime = Date()
            timeTrackingJob = CoroutineScope(mainDispatcher).launch {
                while (isActive && getProgress() <= getMaxValue()) {
                    val currentTime = Date()
                    val difference = currentTime.time - startTime.time

                    val secondsDifference = TimeUnit.MILLISECONDS.toSeconds(difference) % 60
                    val minutesDifference = TimeUnit.MILLISECONDS.toMinutes(difference) % 60
                    val hoursDifference = TimeUnit.MILLISECONDS.toHours(difference) % 60

                    val message =
                        "Time Elapsed: ${hoursDifference.toString().padStart(2, '0')}h: " +
                                "${minutesDifference.toString().padStart(2, '0')}m: " +
                                "${secondsDifference.toString().padStart(2, '0')}s"

                    progressDialogBinding.timeElapsedTextView.text = message

                    delay(1.seconds)
                }
            }
        }
    }

    private fun cancelTimeTrackingJob() {
        timeTrackingJob?.cancel()
        timeTrackingJob = null
    }

    override fun doWorkOnDialogAppearance() {
        startTimeTrackingJobIfRequired()
    }

    override fun cancelPendingJobs(reason: String) {
        super.cancelPendingJobs(reason)
        cancelTimeTrackingJob()
    }

    override fun getMessage(): CharSequence = progressDialogBinding.textViewDeterminate.text

    override fun setMessage(message: CharSequence) {
        progressDialogBinding.textViewDeterminate.text = message
    }

    override fun setProgress(progress: Int) {
        progressDialogBinding.progressbarDeterminate.setProgressCompat(progress, true)
    }

    fun getProgress() = progressDialogBinding.progressbarDeterminate.progress

    override fun setMaxValue(maxValue: Int) {
        progressDialogBinding.progressbarDeterminate.max = maxValue
    }

    fun getMaxValue() = progressDialogBinding.progressbarDeterminate.max

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun setMinValue(minValue: Int) {
        progressDialogBinding.progressbarDeterminate.min = minValue
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun getMinValue() = progressDialogBinding.progressbarDeterminate.min

    override fun setSecondaryProgress(secondaryProgress: Int) {
        progressDialogBinding.progressbarDeterminate.secondaryProgress = secondaryProgress
    }

    fun getSecondaryProgress() = progressDialogBinding.progressbarDeterminate.secondaryProgress

    override fun setIsTimeTrackingEnabled(isTimeTrackingEnabled: Boolean) {
        this.isTimeTrackingEnabled = isTimeTrackingEnabled
        progressDialogBinding.timeElapsedTextView.setVisibility(isTimeTrackingEnabled)
    }

    suspend fun showDialogUntil(
        flow: Flow<Int>
    ) {
        showDialogUntil(
            flow = flow.map { it to null }
        )
    }

    suspend fun showDialogUntil(
        flow: Flow<Pair<Int, Int?>>
    ) {
        cancelPendingJobs("showDialogUntil called again")
        job = CoroutineScope(mainDispatcher).launch {
            if (isActive) {
                show()

                flow
                    .takeWhile { it.first <= getMaxValue() }
                    .onEach {
                        setProgress(it.first)
                        it.second?.let { secondaryProgress ->
                            setSecondaryProgress(secondaryProgress)
                        }
                    }
                    .collect()

                dismiss()
            }
        }
        job?.join()
    }
}