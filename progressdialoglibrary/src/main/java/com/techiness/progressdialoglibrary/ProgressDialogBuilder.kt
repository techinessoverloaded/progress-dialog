package com.techiness.progressdialoglibrary

import android.content.Context
import com.techiness.progressdialoglibrary.theme.DeterminateProgressDialogTheme
import com.techiness.progressdialoglibrary.theme.IndeterminateProgressDialogTheme
import com.techiness.progressdialoglibrary.theme.ProgressDialogTheme
import com.techiness.progressdialoglibrary.theme.getDefaultDeterminateTheme
import com.techiness.progressdialoglibrary.theme.getDefaultIndeterminateTheme

sealed class ProgressDialogBuilder<T: ProgressDialogNew>(
    protected val progressDialog: T
) {

    fun create(): T = progressDialog

    companion object {

        @JvmStatic
        @JvmOverloads
        fun indeterminate(
            context: Context,
            theme: IndeterminateProgressDialogTheme = getDefaultIndeterminateTheme()
        ) = IndeterminateProgressDialogBuilder(
            IndeterminateProgressDialog(
                context = context,
                theme = theme
            )
        )

        @JvmStatic
        @JvmOverloads
        fun determinate(
            context: Context,
            theme: DeterminateProgressDialogTheme = getDefaultDeterminateTheme()
        ) = DeterminateProgressDialogBuilder(
            DeterminateProgressDialog(
                context = context,
                theme = theme
            )
        )
    }
}