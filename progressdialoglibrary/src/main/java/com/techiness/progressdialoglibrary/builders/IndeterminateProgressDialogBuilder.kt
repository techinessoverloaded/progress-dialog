package com.techiness.progressdialoglibrary.builders

import android.content.Context
import com.techiness.progressdialoglibrary.IndeterminateProgressDialog
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme

class IndeterminateProgressDialogBuilder internal constructor(
    override val context: Context,
    override val theme: ProgressDialogTheme
): ProgressDialogBuilder<IndeterminateProgressDialog>(), IndeterminateProgressDialogDslContract {

    override val progressDialog: IndeterminateProgressDialog = IndeterminateProgressDialog(
        context = context,
        theme = theme
    )
}