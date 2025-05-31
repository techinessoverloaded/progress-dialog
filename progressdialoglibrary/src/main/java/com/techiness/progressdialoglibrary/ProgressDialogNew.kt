package com.techiness.progressdialoglibrary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.techiness.progressdialoglibrary.databinding.LayoutBaseProgressDialogBinding
import com.techiness.progressdialoglibrary.helpers.makeVisible
import com.techiness.progressdialoglibrary.theme.ProgressDialogTheme

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

    fun getTitle() = baseProgressDialogBinding.titleView.text

    override fun setTitle(title: CharSequence) {
        baseProgressDialogBinding.titleView.text = title
        baseProgressDialogBinding.titleView.makeVisible()
    }

    override fun setTitle(@StringRes titleResId: Int) {
        setTitle(context.getString(titleResId))
    }

}