package com.techiness.progressdialoglibrary

class IndeterminateProgressDialogBuilder internal constructor(
    progressDialog: IndeterminateProgressDialog
): ProgressDialogBuilder<IndeterminateProgressDialog>(progressDialog),
    IndeterminateProgressDialogDslContract by progressDialog {
}