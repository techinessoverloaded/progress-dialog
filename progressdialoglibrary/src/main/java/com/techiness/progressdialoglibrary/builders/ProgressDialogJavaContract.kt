package com.techiness.progressdialoglibrary.builders

import com.techiness.progressdialoglibrary.ProgressDialogNew

internal interface ProgressDialogJavaContract<T: ProgressDialogNew> {
    fun create(): T
}