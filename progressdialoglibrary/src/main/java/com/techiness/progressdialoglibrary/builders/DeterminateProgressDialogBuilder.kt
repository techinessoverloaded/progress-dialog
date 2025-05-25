package com.techiness.progressdialoglibrary.builders

import android.content.Context
import com.techiness.progressdialoglibrary.DeterminateProgressDialog
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme

class DeterminateProgressDialogBuilder internal constructor(
    override val context: Context,
    override val theme: ProgressDialogTheme
): ProgressDialogBuilder<DeterminateProgressDialog>(), DeterminateProgressDialogDslContract {

    override val progressDialog: DeterminateProgressDialog = DeterminateProgressDialog(
        context = context,
        theme = theme
    )

    override var progress: Int
        get() = progressDialog.progress
        set(value) {
            progressDialog.progress = value
        }
}