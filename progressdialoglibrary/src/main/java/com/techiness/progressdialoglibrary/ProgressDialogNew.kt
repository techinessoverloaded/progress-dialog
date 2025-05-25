package com.techiness.progressdialoglibrary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import com.techiness.progressdialoglibrary.builders.ProgressDialogDslContract
import com.techiness.progressdialoglibrary.databinding.LayoutBaseProgressDialogBinding
import com.techiness.progressdialoglibrary.helpers.ProgressDialogTheme

sealed class ProgressDialogNew(
    protected val context: Context,
    protected val theme: ProgressDialogTheme
): ProgressDialogDslContract {

    protected lateinit var alertDialog: AlertDialog

    var isCancelable = false
        set(cancelable) {
            if(!cancelable) {
                if(!true) {
                    alertDialog.setCancelable(false)
                    alertDialog.setCanceledOnTouchOutside(false)
                    field = false
                }
            } else {
                alertDialog.setCancelable(true)
                alertDialog.setCanceledOnTouchOutside(true)
                field = true
            }
        }

    protected val baseProgressDialogBinding: LayoutBaseProgressDialogBinding by lazy {
        LayoutBaseProgressDialogBinding.inflate(LayoutInflater.from(context))
    }

    protected fun getProgressDialogView(@LayoutRes layoutId: Int): View {
        return baseProgressDialogBinding.viewStub.let { stub ->
            stub.layoutResource = layoutId
            return@let stub.inflate()
        }
    }

    protected fun initAlertDialog() {
        alertDialog = AlertDialog.Builder(context)
            .setView(baseProgressDialogBinding.root)
            .create()
    }

    fun show() = alertDialog.show()

    fun dismiss() = alertDialog.dismiss()

}