package com.techiness.progressdialoglibrary.builders

import android.content.Context
import com.techiness.progressdialoglibrary.IndeterminateProgressDialog
import com.techiness.progressdialoglibrary.IndeterminateProgressDialogDslContract
import com.techiness.progressdialoglibrary.ProgressDialogBuilder
import com.techiness.progressdialoglibrary.helpers.ioDispatcher
import com.techiness.progressdialoglibrary.helpers.mainDispatcher
import com.techiness.progressdialoglibrary.theme.IndeterminateProgressDialogTheme
import com.techiness.progressdialoglibrary.theme.getDefaultIndeterminateTheme
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

fun buildIndeterminateProgressDialog(
    context: Context,
    theme: IndeterminateProgressDialogTheme = getDefaultIndeterminateTheme(),
    builder: IndeterminateProgressDialogDslContract.() -> Unit
) = ProgressDialogBuilder
    .indeterminate(context, theme)
    .apply(builder)
    .create()

fun showIndeterminateProgressDialog(
    context: Context,
    theme: IndeterminateProgressDialogTheme = getDefaultIndeterminateTheme(),
    builder: IndeterminateProgressDialogDslContract.() -> Unit
) = buildIndeterminateProgressDialog(
    context = context,
    theme = theme,
    builder = builder
).also {
    it.show()
}

suspend fun showIndeterminateProgressDialogUntil(
    context: Context,
    theme: IndeterminateProgressDialogTheme = getDefaultIndeterminateTheme(),
    coroutineDispatcher: CoroutineDispatcher = ioDispatcher,
    builder: IndeterminateProgressDialogDslContract.() -> Unit,
    suspendBlock: suspend () -> Unit
) = withContext(mainDispatcher) {
    buildIndeterminateProgressDialog(
        context = context,
        theme = theme,
        builder = builder
    ).showDialogUntil(coroutineDispatcher, suspendBlock)
}