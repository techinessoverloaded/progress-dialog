package com.techiness.progressdialoglibrary

import androidx.appcompat.app.AlertDialog
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme

sealed class ProgressDialogNew {

    protected abstract val alertDialog: AlertDialog
    protected abstract val theme: ProgressDialogTheme


}