package com.techiness.progressdialoglibrary.builders

import com.techiness.progressdialoglibrary.DeterminateProgressDialog

internal interface DeterminateProgressDialogJavaContract:
    ProgressDialogJavaContract<DeterminateProgressDialog> {

    fun setInitialProgress(progress: Int): DeterminateProgressDialogJavaContract
}