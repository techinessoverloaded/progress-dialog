package com.techiness.progressdialoglibrary.builders

import android.content.Context
import com.techiness.progressdialoglibrary.DeterminateProgressDialog
import com.techiness.progressdialoglibrary.IndeterminateProgressDialog
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme
import com.techiness.progressdialoglibrary.helpers.getDefaultTheme

fun buildDeterminateProgressDialog(
    context: Context,
    theme: ProgressDialogTheme = getDefaultTheme(),
    builder: DeterminateProgressDialogDslContract.() -> Unit
): DeterminateProgressDialog {
    return ProgressDialogBuilder
        .determinate(context, theme)
        .apply(builder)
        .create()
}

fun buildIndeterminateProgressDialog(
    context: Context,
    theme: ProgressDialogTheme = getDefaultTheme(),
    builder: IndeterminateProgressDialogDslContract.() -> Unit
): IndeterminateProgressDialog {
    return ProgressDialogBuilder
        .indeterminate(context, theme)
        .apply(builder)
        .create()
}

fun showDeterminateProgressDialog(
    context: Context,
    theme: ProgressDialogTheme = getDefaultTheme(),
    builder: DeterminateProgressDialogDslContract.() -> Unit
): DeterminateProgressDialog {
    return buildDeterminateProgressDialog(
        context = context,
        theme = theme,
        builder = builder
    ).also {
        it.show()
    }
}

fun showIndeterminateProgressDialog(
    context: Context,
    theme: ProgressDialogTheme = getDefaultTheme(),
    builder: IndeterminateProgressDialogDslContract.() -> Unit
): IndeterminateProgressDialog {
    return buildIndeterminateProgressDialog(
        context = context,
        theme = theme,
        builder = builder
    ).also {
        it.show()
    }
}