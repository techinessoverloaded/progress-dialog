package com.techiness.progressdialoglibrary

import android.content.Context
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme
import com.techiness.progressdialoglibrary.databinding.LayoutDeterminateProgressDialogBinding

class DeterminateProgressDialog internal constructor(
    override val context: Context,
    override val theme: ProgressDialogTheme
): ProgressDialogNew() {

    private val progressDialogBinding: LayoutDeterminateProgressDialogBinding

    init {
        progressDialogBinding = LayoutDeterminateProgressDialogBinding.bind(
            getProgressDialogView(R.layout.layout_determinate_progress_dialog)
        )
        initAlertDialog()
    }

    var progress: Int
        get() = progressDialogBinding.progressbarDeterminate.progress
        set(progressValue) {
            progressDialogBinding.progressbarDeterminate.setProgressCompat(progressValue, true)
        }
}