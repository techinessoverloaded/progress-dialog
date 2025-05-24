package com.techiness.progressdialoglibrary.helpers

import android.os.Build
import androidx.annotation.RequiresApi

sealed class ProgressDialogTheme {
    data object Light : ProgressDialogTheme()
    data object Dark : ProgressDialogTheme()

    @RequiresApi(api = Build.VERSION_CODES.R)
    data object Auto : ProgressDialogTheme()
}

internal fun getDefaultTheme(): ProgressDialogTheme {
    return if (VersionChecker.isAtLeastAndroidR()) {
        ProgressDialogTheme.Auto
    } else {
        ProgressDialogTheme.Light
    }
}