package com.techiness.progressdialoglibrary.theme

import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import com.techiness.progressdialoglibrary.R
import com.techiness.progressdialoglibrary.helpers.VersionChecker

sealed class DeterminateProgressDialogTheme() : ProgressDialogTheme() {

    data class Custom(
        @get:ColorRes
        override val titleColorId: Int,
    ) : DeterminateProgressDialogTheme()

    data object Light : DeterminateProgressDialogTheme() {
        override val titleColorId: Int = R.color.black
    }

    data object Dark : DeterminateProgressDialogTheme() {
        override val titleColorId: Int = R.color.white
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    data object Auto : DeterminateProgressDialogTheme()
}

internal fun getDefaultDeterminateTheme(): DeterminateProgressDialogTheme {
    return if (VersionChecker.isAtLeastAndroidR()) {
        DeterminateProgressDialogTheme.Auto
    } else {
        DeterminateProgressDialogTheme.Light
    }
}