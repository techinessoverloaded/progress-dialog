package com.techiness.progressdialoglibrary.builders

import com.techiness.progressdialoglibrary.DeterminateProgressDialog

class DeterminateProgressDialogBuilder internal constructor(
    progressDialog: DeterminateProgressDialog
) : ProgressDialogBuilder<DeterminateProgressDialog>(progressDialog),
    DeterminateProgressDialogJavaContract,
    DeterminateProgressDialogDslContract by progressDialog {

    override fun setInitialProgress(progress: Int) = apply {
        this.progress = progress
    }

}