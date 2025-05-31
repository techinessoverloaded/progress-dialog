package com.techiness.progressdialoglibrary.theme

import androidx.annotation.ColorRes

sealed class ProgressDialogTheme {

    @get:ColorRes
    abstract val titleColorId: Int
}
