package com.techiness.progressdialoglibrary

sealed interface DeterminateProgressDialogDslContract: ProgressDialogDslContract {
    fun setProgress(progress: Int): DeterminateProgressDialogDslContract
    fun setMaxValue(maxValue: Int): DeterminateProgressDialogDslContract
    fun setMinValue(minValue: Int): DeterminateProgressDialogDslContract
    fun setSecondaryProgress(secondaryProgress: Int): DeterminateProgressDialogDslContract
}