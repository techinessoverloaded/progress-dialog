package com.techiness.progressdialoglibrary.helpers

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

internal object VersionChecker {

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    internal fun isAtLeastAndroidR(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
    }
}