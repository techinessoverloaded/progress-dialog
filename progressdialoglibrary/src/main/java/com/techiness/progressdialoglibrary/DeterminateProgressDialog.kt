package com.techiness.progressdialoglibrary

import android.content.Context
import com.techiness.progressdialoglibrary.databinding.LayoutDeterminateProgressDialogBinding
import com.techiness.progressdialoglibrary.helpers.mainDispatcher
import com.techiness.progressdialoglibrary.theme.ProgressDialogTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.withContext

class DeterminateProgressDialog(
    context: Context,
    theme: ProgressDialogTheme
): ProgressDialogNew(context, theme), DeterminateProgressDialogDslContract {

    private val progressDialogBinding = LayoutDeterminateProgressDialogBinding.bind(
        getProgressDialogView(R.layout.layout_determinate_progress_dialog)
    )

    init {
        initAlertDialog()
    }

    override fun setProgress(progress: Int) = apply {
        progressDialogBinding.progressbarDeterminate.setProgressCompat(progress, true)
    }

    fun getProgress() = progressDialogBinding.progressbarDeterminate.progress

    override fun setMaxValue(maxValue: Int) = apply {
        progressDialogBinding.progressbarDeterminate.max = maxValue
    }

    fun getMaxValue() = progressDialogBinding.progressbarDeterminate.max

    override fun setMinValue(minValue: Int) = apply {
        progressDialogBinding.progressbarDeterminate.min = minValue
    }

    fun getMinValue() = progressDialogBinding.progressbarDeterminate.min

    override fun setSecondaryProgress(secondaryProgress: Int) = apply {
        progressDialogBinding.progressbarDeterminate.secondaryProgress = secondaryProgress
    }

    fun getSecondaryProgress() = progressDialogBinding.progressbarDeterminate.secondaryProgress

    suspend fun showDialogUntil(
        flow: Flow<Int>
    ) {
        showDialogUntil(
            flow = flow.map { it to null }
        )
    }

    suspend fun showDialogUntil(
        flow: Flow<Pair<Int, Int?>>
    ) {
        withContext(mainDispatcher) {
            show()

            flow
                .takeWhile { it.first <= getMaxValue() }
                .onEach {
                    setProgress(it.first)
                    it.second?.let { secondaryProgress ->
                        setSecondaryProgress(secondaryProgress)
                    }
                }
                .collect()

            dismiss()
        }
    }
}