package com.techiness.progressdialoglibrary.theme

import android.os.Build
import androidx.annotation.RequiresApi
import com.techiness.progressdialoglibrary.helpers.VersionChecker

sealed class IndeterminateProgressDialogTheme : ProgressDialogTheme() {
    data object Light : IndeterminateProgressDialogTheme()
    data object Dark : IndeterminateProgressDialogTheme()

    @RequiresApi(api = Build.VERSION_CODES.R)
    data object Auto : IndeterminateProgressDialogTheme()
}

internal fun getDefaultIndeterminateTheme(): IndeterminateProgressDialogTheme {
    return if (VersionChecker.isAtLeastAndroidR()) {
        IndeterminateProgressDialogTheme.Auto
    } else {
        IndeterminateProgressDialogTheme.Light
    }
}