package com.techiness.progressdialoglibrary

import android.content.Context
import com.techiness.progressdialoglibrary.databinding.LayoutIndeterminateProgressDialogBinding
import com.techiness.progressdialoglibrary.helpers.ioDispatcher
import com.techiness.progressdialoglibrary.helpers.mainDispatcher
import com.techiness.progressdialoglibrary.theme.ProgressDialogTheme
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
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

    override fun getMessage(): CharSequence = progressDialogBinding.textViewIndeterminate.text

    override fun setMessage(message: CharSequence) {
        progressDialogBinding.textViewIndeterminate.text = message
    }

    suspend fun showDialogUntil(
        coroutineDispatcher: CoroutineDispatcher = ioDispatcher,
        block: suspend () -> Unit
    ) {
        cancelPendingJobs("showDialogUntil called again")
        job = CoroutineScope(mainDispatcher).launch {
            if (isActive) {
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
        job?.join()
    }
}