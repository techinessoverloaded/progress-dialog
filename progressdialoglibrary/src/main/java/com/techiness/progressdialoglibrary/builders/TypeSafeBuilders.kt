package com.techiness.progressdialoglibrary.builders

import android.content.Context
import com.techiness.progressdialoglibrary.DeterminateProgressDialog
import com.techiness.progressdialoglibrary.IndeterminateProgressDialog
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme
import com.techiness.progressdialoglibrary.helpers.getDefaultTheme
import com.techiness.progressdialoglibrary.helpers.ioDispatcher
import com.techiness.progressdialoglibrary.helpers.mainDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

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

suspend fun showIndeterminateProgressDialogUntil(
    context: Context,
    theme: ProgressDialogTheme = getDefaultTheme(),
    coroutineDispatcher: CoroutineDispatcher = ioDispatcher,
    builder: IndeterminateProgressDialogDslContract.() -> Unit,
    suspendBlock: suspend () -> Unit
) {
    withContext(mainDispatcher) {
        buildIndeterminateProgressDialog(
            context = context,
            theme = theme,
            builder = builder
        )
    }.showDialogUntil(coroutineDispatcher, suspendBlock)
}