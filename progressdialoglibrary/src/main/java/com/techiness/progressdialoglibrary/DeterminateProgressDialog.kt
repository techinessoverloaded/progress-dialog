package com.techiness.progressdialoglibrary

import android.content.Context
import com.techiness.progressdialoglibrary.builders.DeterminateProgressDialogDslContract
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme
import com.techiness.progressdialoglibrary.databinding.LayoutDeterminateProgressDialogBinding
import com.techiness.progressdialoglibrary.helpers.mainDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.withContext

class DeterminateProgressDialog internal constructor(
    context: Context,
    theme: ProgressDialogTheme
): ProgressDialogNew(context, theme), DeterminateProgressDialogDslContract {

    private val progressDialogBinding: LayoutDeterminateProgressDialogBinding

    init {
        progressDialogBinding = LayoutDeterminateProgressDialogBinding.bind(
            getProgressDialogView(R.layout.layout_determinate_progress_dialog)
        )
        initAlertDialog()
    }

    override var progress: Int
        get() = progressDialogBinding.progressbarDeterminate.progress
        set(progressValue) {
            progressDialogBinding.progressbarDeterminate.setProgressCompat(progressValue, true)
        }

    override var maxValue: Int
        get() = progressDialogBinding.progressbarDeterminate.max
        set(value) {
            progressDialogBinding.progressbarDeterminate.max = value
        }

    suspend fun showDialogUntil(
        flow: Flow<Int>
    ) {
        withContext(mainDispatcher) {
            show()

            flow
                .takeWhile { it <= maxValue }
                .onEach { progress = it }
                .collect()

            dismiss()
        }
    }
}