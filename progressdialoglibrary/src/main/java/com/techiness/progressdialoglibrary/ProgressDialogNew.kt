package com.techiness.progressdialoglibrary

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.techiness.progressdialoglibrary.databinding.LayoutBaseProgressDialogBinding
import com.techiness.progressdialoglibrary.helpers.makeVisible
import com.techiness.progressdialoglibrary.theme.ProgressDialogTheme
import kotlinx.coroutines.Job
import java.util.concurrent.CancellationException

sealed class ProgressDialogNew(
    protected val context: Context,
    protected val theme: ProgressDialogTheme
): ProgressDialogDslContract {

    protected lateinit var alertDialog: AlertDialog

    protected var job: Job? = null

    private var customShowListener: DialogInterface.OnShowListener? = null

    private var customCancelListener: DialogInterface.OnCancelListener? = null

    private var customDismissListener: DialogInterface.OnDismissListener? = null

    private val onShowListener = DialogInterface.OnShowListener { dialog ->
        doWorkOnDialogAppearance()
        customShowListener?.onShow(dialog)
    }

    private val onDismissListener = DialogInterface.OnDismissListener { dialog ->
        cancelPendingJobs("ProgressDialog dismissed")
        customDismissListener?.onDismiss(dialog)
    }

    private val onCancelListener = DialogInterface.OnCancelListener { dialog ->
        cancelPendingJobs("ProgressDialog cancelled")
        customCancelListener?.onCancel(dialog)
    }

    protected val baseProgressDialogBinding: LayoutBaseProgressDialogBinding by lazy {
        LayoutBaseProgressDialogBinding.inflate(LayoutInflater.from(context))
    }

    protected open fun doWorkOnDialogAppearance() = Unit

    @CallSuper
    protected open fun cancelPendingJobs(reason: String) {
        job?.cancel(CancellationException(reason))
        job = null
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
            .setOnDismissListener(onDismissListener)
            .setOnCancelListener(onCancelListener)
            .create().also {
                setOnShowListener(onShowListener)
            }
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

    abstract fun getMessage(): CharSequence

    override fun setMessage(@StringRes messageResId: Int) {
        setMessage(context.getString(messageResId))
    }

    override fun setOnShowListener(listener: DialogInterface.OnShowListener) {
        TODO("Not yet implemented")
    }

    override fun setOnDismissListener(listener: DialogInterface.OnDismissListener) {
        customDismissListener = listener
    }

    override fun setOnCancelListener(listener: DialogInterface.OnCancelListener) {
        customCancelListener = listener
    }

}