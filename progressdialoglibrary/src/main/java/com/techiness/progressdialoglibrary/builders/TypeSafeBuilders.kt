package com.techiness.progressdialoglibrary.builders

import android.content.Context
import com.techiness.progressdialoglibrary.DeterminateProgressDialog
import com.techiness.progressdialoglibrary.IndeterminateProgressDialog
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme
import com.techiness.progressdialoglibrary.helpers.getDefaultTheme

fun buildDeterminateProgressDialog(
    context: Context,
    theme: ProgressDialogTheme = getDefaultTheme(),
    builder: ProgressDialogBuilderContract<DeterminateProgressDialog>.() -> Unit
): DeterminateProgressDialog {
    return ProgressDialogBuilder
        .determinate(context, theme)
        .apply(builder)
        .create()
}

fun buildIndeterminateProgressDialog(
    context: Context,
    theme: ProgressDialogTheme = getDefaultTheme(),
    builder: ProgressDialogBuilderContract<IndeterminateProgressDialog>.() -> Unit
): IndeterminateProgressDialog {
    return ProgressDialogBuilder
        .indeterminate(context, theme)
        .apply(builder)
        .create()
}