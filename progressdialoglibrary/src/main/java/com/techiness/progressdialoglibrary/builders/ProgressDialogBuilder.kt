package com.techiness.progressdialoglibrary.builders

import android.content.Context
import com.techiness.progressdialoglibrary.ProgressDialogNew
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme
import com.techiness.progressdialoglibrary.helpers.getDefaultTheme

sealed class ProgressDialogBuilder<T: ProgressDialogNew>: ProgressDialogDslContract {

    protected abstract val context: Context
    protected abstract val theme: ProgressDialogTheme
    protected abstract val progressDialog: T

    fun create(): T = progressDialog

    companion object {

        @JvmStatic
        @JvmOverloads
        fun indeterminate(
            context: Context,
            theme: ProgressDialogTheme = getDefaultTheme()
        ) = IndeterminateProgressDialogBuilder(
            context = context,
            theme = theme
        )

        @JvmStatic
        @JvmOverloads
        fun determinate(
            context: Context,
            theme: ProgressDialogTheme = getDefaultTheme()
        ) = DeterminateProgressDialogBuilder(
            context = context,
            theme = theme
        )
    }
}