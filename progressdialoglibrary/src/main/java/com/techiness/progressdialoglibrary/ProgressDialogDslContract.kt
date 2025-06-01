package com.techiness.progressdialoglibrary

import android.content.DialogInterface
import androidx.annotation.StringRes

sealed interface ProgressDialogDslContract {
    fun setTitle(title: CharSequence)
    fun setTitle(@StringRes titleResId: Int)
    fun setMessage(message: CharSequence)
    fun setMessage(@StringRes messageResId: Int)
    fun setOnShowListener(listener: DialogInterface.OnShowListener)
    fun setOnCancelListener(listener: DialogInterface.OnCancelListener)
    fun setOnDismissListener(listener: DialogInterface.OnDismissListener)
}