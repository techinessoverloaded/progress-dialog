package com.techiness.progressdialoglibrary

import androidx.appcompat.app.AlertDialog
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme

class IndeterminateProgressDialog internal constructor(
    override val theme: ProgressDialogTheme
): ProgressDialogNew() {
    override lateinit var alertDialog: AlertDialog
}