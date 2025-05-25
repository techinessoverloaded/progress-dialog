package com.techiness.progressdialoglibrary.builders

import android.content.Context
import com.techiness.progressdialoglibrary.DeterminateProgressDialog
import com.techiness.progressdialoglibrary.IndeterminateProgressDialog
import com.techiness.progressdialoglibrary.ProgressDialogNew
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme
import com.techiness.progressdialoglibrary.helpers.getDefaultTheme

sealed class ProgressDialogBuilder<T: ProgressDialogNew>(
    protected val progressDialog: T
):  ProgressDialogJavaContract<T>, ProgressDialogDslContract by progressDialog {

    override fun create(): T = progressDialog

    companion object {

        @JvmStatic
        @JvmOverloads
        fun indeterminate(
            context: Context,
            theme: ProgressDialogTheme = getDefaultTheme()
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
            theme: ProgressDialogTheme = getDefaultTheme()
        ) = DeterminateProgressDialogBuilder(
            DeterminateProgressDialog(
                context = context,
                theme = theme
            )
        )
    }
}