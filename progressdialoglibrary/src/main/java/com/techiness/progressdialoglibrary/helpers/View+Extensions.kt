package com.techiness.progressdialoglibrary.helpers

import android.view.View

internal fun View.makeGone() {
    visibility = View.GONE
}

internal fun View.makeVisible() {
    visibility = View.VISIBLE
}

internal fun View.makeInvisible() {
    visibility = View.INVISIBLE
}