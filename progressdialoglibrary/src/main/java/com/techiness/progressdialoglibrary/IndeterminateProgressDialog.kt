package com.techiness.progressdialoglibrary

import android.content.Context
import com.techiness.progressdialoglibrary.databinding.LayoutIndeterminateProgressDialogBinding
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme

class IndeterminateProgressDialog internal constructor(
    override val context: Context,
    override val theme: ProgressDialogTheme
): ProgressDialogNew() {

    private val progressDialogBinding: LayoutIndeterminateProgressDialogBinding

    init {
        progressDialogBinding = LayoutIndeterminateProgressDialogBinding.bind(
            getProgressDialogView(R.layout.layout_indeterminate_progress_dialog)
        )
        initAlertDialog()
    }

}