package com.techiness.progressdialoglibrary.builders

import com.techiness.progressdialoglibrary.IndeterminateProgressDialog

class IndeterminateProgressDialogBuilder internal constructor(
    progressDialog: IndeterminateProgressDialog
): ProgressDialogBuilder<IndeterminateProgressDialog>(progressDialog),
    IndeterminateProgressDialogJavaContract,
    IndeterminateProgressDialogDslContract by progressDialog {
}