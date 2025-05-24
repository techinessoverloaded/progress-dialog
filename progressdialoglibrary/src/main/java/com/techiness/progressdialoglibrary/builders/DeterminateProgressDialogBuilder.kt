package com.techiness.progressdialoglibrary.builders

import android.content.Context
import com.techiness.progressdialoglibrary.DeterminateProgressDialog
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme

class DeterminateProgressDialogBuilder internal constructor(
    override val context: Context,
    override val theme: ProgressDialogTheme
): ProgressDialogBuilder<DeterminateProgressDialog>() {
    override val progressDialog: DeterminateProgressDialog
        get() = TODO("Not yet implemented")
}