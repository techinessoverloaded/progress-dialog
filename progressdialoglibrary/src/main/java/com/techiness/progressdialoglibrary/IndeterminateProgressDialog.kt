package com.techiness.progressdialoglibrary

import android.content.Context
import com.techiness.progressdialoglibrary.builders.IndeterminateProgressDialogDslContract
import com.techiness.progressdialoglibrary.databinding.LayoutIndeterminateProgressDialogBinding
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme
import com.techiness.progressdialoglibrary.helpers.ioDispatcher
import com.techiness.progressdialoglibrary.helpers.mainDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class IndeterminateProgressDialog internal constructor(
    context: Context,
    theme: ProgressDialogTheme
): ProgressDialogNew(context, theme), IndeterminateProgressDialogDslContract {

    private val progressDialogBinding: LayoutIndeterminateProgressDialogBinding

    init {
        progressDialogBinding = LayoutIndeterminateProgressDialogBinding.bind(
            getProgressDialogView(R.layout.layout_indeterminate_progress_dialog)
        )
        initAlertDialog()
    }

    suspend fun showDialogUntil(
        coroutineDispatcher: CoroutineDispatcher = ioDispatcher,
        block: suspend () -> Unit
    ) {
        try {
            withContext(mainDispatcher) {
                show()
            }

            withContext(coroutineDispatcher) {
                block()
            }
        } finally {
            withContext(mainDispatcher) {
                dismiss()
            }
        }
    }
}