package com.techiness.progressdialoglibrary

class DeterminateProgressDialogBuilder internal constructor(
    progressDialog: DeterminateProgressDialog
) : ProgressDialogBuilder<DeterminateProgressDialog>(progressDialog),
    DeterminateProgressDialogDslContract by progressDialog {

}