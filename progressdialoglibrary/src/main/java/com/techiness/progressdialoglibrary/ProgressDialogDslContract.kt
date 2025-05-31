package com.techiness.progressdialoglibrary

import androidx.annotation.StringRes

sealed interface ProgressDialogDslContract {
    fun setTitle(title: CharSequence)
    fun setTitle(@StringRes titleResId: Int)
}