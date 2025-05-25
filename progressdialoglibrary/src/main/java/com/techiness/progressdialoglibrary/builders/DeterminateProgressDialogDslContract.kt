package com.techiness.progressdialoglibrary.builders

import com.techiness.progressdialoglibrary.ProgressDialogDsl

@ProgressDialogDsl
interface DeterminateProgressDialogDslContract: ProgressDialogDslContract {
    var progress: Int
    var maxValue: Int
}