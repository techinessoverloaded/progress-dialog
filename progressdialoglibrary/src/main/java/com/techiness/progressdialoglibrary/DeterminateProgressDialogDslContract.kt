package com.techiness.progressdialoglibrary

import android.os.Build
import androidx.annotation.RequiresApi

sealed interface DeterminateProgressDialogDslContract: ProgressDialogDslContract {
    fun setProgress(progress: Int)
    fun setMaxValue(maxValue: Int)
    @RequiresApi(api = Build.VERSION_CODES.O)
    fun setMinValue(minValue: Int)
    fun setSecondaryProgress(secondaryProgress: Int)
    fun setIsTimeTrackingEnabled(isTimeTrackingEnabled: Boolean)
}