package com.techiness.progressdialoglibrary

import android.content.Context
import com.techiness.progressdialoglibrary.databinding.LayoutIndeterminateProgressDialogBinding
import com.techiness.progressdialoglibrary.helpers.ioDispatcher
import com.techiness.progressdialoglibrary.helpers.mainDispatcher
import com.techiness.progressdialoglibrary.theme.ProgressDialogTheme
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class IndeterminateProgressDialog internal constructor(
    context: Context,
    theme: ProgressDialogTheme
): ProgressDialogNew(context, theme), IndeterminateProgressDialogDslContract {

    private val progressDialogBinding = LayoutIndeterminateProgressDialogBinding.bind(
        getProgressDialogView(R.layout.layout_indeterminate_progress_dialog)
    )

    init {
        initAlertDialog()
    }

    suspend fun showDialogUntil(
        coroutineDispatcher: CoroutineDispatcher = ioDispatcher,
        block: suspend () -> Unit
    ) {
        withContext(mainDispatcher) {
            try {
                show()
                withContext(coroutineDispatcher) {
                    block()
                }
            } finally {
                dismiss()
            }
        }
    }
}