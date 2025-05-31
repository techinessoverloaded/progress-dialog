package com.techiness.progressdialoglibrary.builders

import android.content.Context
import com.techiness.progressdialoglibrary.DeterminateProgressDialogDslContract
import com.techiness.progressdialoglibrary.ProgressDialogBuilder
import com.techiness.progressdialoglibrary.theme.DeterminateProgressDialogTheme
import com.techiness.progressdialoglibrary.theme.getDefaultDeterminateTheme

fun buildDeterminateProgressDialog(
    context: Context,
    theme: DeterminateProgressDialogTheme = getDefaultDeterminateTheme(),
    builder: DeterminateProgressDialogDslContract.() -> Unit
) = ProgressDialogBuilder
    .determinate(context, theme)
    .apply(builder)
    .create()

fun showDeterminateProgressDialog(
    context: Context,
    theme: DeterminateProgressDialogTheme = getDefaultDeterminateTheme(),
    builder: DeterminateProgressDialogDslContract.() -> Unit
) = buildDeterminateProgressDialog(
    context = context,
    theme = theme,
    builder = builder
).also {
    it.show()
}