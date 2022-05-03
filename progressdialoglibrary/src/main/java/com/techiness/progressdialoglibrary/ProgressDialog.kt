/*
 * An easy-to-use ProgressDialog Library provided by Techiness Overloaded for Android API 24 and above.
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.techiness.progressdialoglibrary

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnShowListener
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.techiness.progressdialoglibrary.ProgressDialog.Companion.MODE_DETERMINATE
import com.techiness.progressdialoglibrary.ProgressDialog.Companion.MODE_INDETERMINATE
import com.techiness.progressdialoglibrary.ProgressDialog.Companion.THEME_DARK
import com.techiness.progressdialoglibrary.ProgressDialog.Companion.THEME_LIGHT
import com.techiness.progressdialoglibrary.databinding.LayoutProgressdialogBinding
import java.util.Locale

/**
 * An easy to use ProgressDialog library for Android API level 24 and above.
 * The below given parameters apply for all the overloaded constructors in Java or a Single Constructor with default arguments in Kotlin.
 * @param context The Activity context which must be provided for sure no matter which overloaded constructor is called.
 * @param modeConstant The [Int] constant, which is an optional parameter that accepts either [MODE_DETERMINATE] or [MODE_INDETERMINATE]. If it is not passed, [MODE_INDETERMINATE] will be set by default, which can be changed later by setting [mode].
 * @param themeConstant The [Int] constant, which is an optional parameter that accepts either [THEME_DARK], [THEME_LIGHT], or [THEME_FOLLOW_SYSTEM]
 * If it is not passed, [THEME_LIGHT] will be set by default, which can be changed later by setting [theme].
 *
 * **NOTE** : [THEME_FOLLOW_SYSTEM] can be used starting from Android API Level 31 (Android 11) only. Attempting to use it in lower versions will throw [IllegalArgumentException].
 */
class ProgressDialog @JvmOverloads constructor(
    @ModeConstant modeConstant: Int = MODE_INDETERMINATE,
    private val context: Context,
    @ThemeConstant themeConstant: Int = THEME_LIGHT)
{
    @SuppressLint("InlinedApi")
    @IntDef(THEME_LIGHT, THEME_DARK, THEME_FOLLOW_SYSTEM)
    @Retention(AnnotationRetention.SOURCE)
    annotation class ThemeConstant

    @IntDef(MODE_INDETERMINATE, MODE_DETERMINATE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class ModeConstant

    companion object
    {
        /**
         * The default Theme for ProgressDialog (even if it is not passed in Constructor).
         * Suitable for apps having a Light Theme.
         * Theme can be changed later by setting [theme].
         */
        const val THEME_LIGHT = 1

        /**
         * This theme is suitable for apps having a Dark Theme.
         * This Constant SHOULD be passed explicitly in the Constructor for setting Dark Theme for ProgressDialog.
         * Theme can be changed later by setting [theme].
         */
        const val THEME_DARK = 2

        /**
         * When this ThemeConstant is used, ProgressDialog's theme is automatically changed to match the System's theme each time before [show] is called.
         * This Constant can be used starting from Android API Level 31 (Android 11) ONLY.
         * Setting [theme] will throw [IllegalArgumentException] if this Constant is passed in method call in Android versions lower than Android 11.
         */
        @RequiresApi(api = Build.VERSION_CODES.R)
        const val THEME_FOLLOW_SYSTEM = 3

        /**
         * The default mode for ProgressDialog where an Indeterminate Spinner is shown for indicating Progress (even if it is not passed in Constructor).
         * Suitable for implementations where the exact progress of an operation is unknown to the Developer.
         */
        const val MODE_INDETERMINATE = 4

        /**
         * In this mode, a Determinate ProgressBar is shown inside the ProgressDialog for indicating Progress.
         * It also has a TextView for numerically showing the Progress Value either as Percentage or as Fraction.
         * Progress Value is shown as Percentage by Default which can be changed using [showProgressTextAsFraction].
         */
        const val MODE_DETERMINATE = 5
        private const val SHOW_AS_FRACTION = 6
        private const val SHOW_AS_PERCENT = 7
        private const val HIDE_PROGRESS_TEXT = 8
    }

    private var progressDialog: AlertDialog
    private var progressViewMode = 0
    private var autoThemeEnabled = false
    private var binding: LayoutProgressdialogBinding = LayoutProgressdialogBinding.inflate(LayoutInflater.from(context))

    /**
     * Gets/Sets the mode of the ProgressDialog which is [MODE_INDETERMINATE] by Default.
     * If you're going to use only one Mode constantly, no need to set this. Instead, use an appropriate Constructor to set the required Mode during Instantiation.
     * Only [MODE_DETERMINATE] or [MODE_INDETERMINATE] should be passed as parameter for the Setter.
     * @throws [IllegalArgumentException] If any other value other than [MODE_DETERMINATE] or [MODE_INDETERMINATE] is passed to the Setter.
     */
    var mode: Int = MODE_INDETERMINATE
    @ModeConstant get() = field
    @Throws(IllegalArgumentException::class)
    set(@ModeConstant modeConstant)
    {
        if(modeConstant == field)
            return
        when(modeConstant)
        {
            MODE_DETERMINATE ->
            {
                binding.textViewIndeterminate.visibility = View.GONE
                binding.progressbarIndeterminate.visibility = View.GONE
                binding.textViewDeterminate.visibility = View.VISIBLE
                binding.progressbarDeterminate.visibility = View.VISIBLE
                progressViewMode = SHOW_AS_PERCENT
                binding.progressTextView.visibility = View.VISIBLE
                incrementValue = if (incrementValue == 0) 1 else incrementValue
                field = modeConstant
            }
            MODE_INDETERMINATE -> {
                binding.textViewDeterminate.visibility = View.GONE
                binding.progressbarDeterminate.visibility = View.GONE
                binding.progressTextView.visibility = View.GONE
                binding.textViewIndeterminate.visibility = View.VISIBLE
                binding.progressbarIndeterminate.visibility = View.VISIBLE
                field = modeConstant
            }
            else -> throw IllegalArgumentException("Invalid Values passed to function ! Make sure to pass MODE_INDETERMINATE or MODE_DETERMINATE only !")
        }
    }

    /**
     * Gets/Sets the theme of the ProgressDialog. The theme is [THEME_LIGHT] by Default.
     * If you're going to use only one Theme constantly, no need to set this. Instead, use an appropriate Constructor to set the required Theme during Instantiation.
     * Only [THEME_LIGHT], [THEME_DARK] or [THEME_FOLLOW_SYSTEM] should be passed as parameter for the Setter.
     * @throws [IllegalArgumentException] If any other value other than [THEME_LIGHT], [THEME_DARK] or [THEME_FOLLOW_SYSTEM] is passed to the Setter or if [THEME_FOLLOW_SYSTEM] is used in Android Versions BELOW Android 11 (API Level 30).
     */
    var theme = THEME_LIGHT
    @SuppressLint("InlinedApi")
    @ThemeConstant get()
    {
        return if(autoThemeEnabled) THEME_FOLLOW_SYSTEM else field
    }
    @Throws(IllegalArgumentException::class)
    set(@ThemeConstant themeConstant)
    {
        if(themeConstant == field)
            return
        when(themeConstant)
        {
            THEME_DARK, THEME_LIGHT ->
            {
                autoThemeEnabled = false
                if (setThemeInternal(themeConstant))
                    field = themeConstant
            }
            THEME_FOLLOW_SYSTEM ->
            {
                require(isAboveOrEqualToAnd11())
                { "THEME_FOLLOW_SYSTEM can be used starting from Android 11 (API Level 30) only !" }
                autoThemeEnabled = true
            }
            else -> throw IllegalArgumentException("Invalid Values passed to function ! Make sure to pass THEME_LIGHT, THEME_DARK or THEME_FOLLOW_SYSTEM only !")
        }
    }

    /**
     * Toggles the Cancelable property of ProgressDialog which is false by Default.
     * If it is set to true, the User can cancel the ProgressDialog by pressing Back Button or by touching any other part of the screen.
     * It is NOT RECOMMENDED to set Cancelable to true.
     */
    var isCancelable = false
    set(cancelable)
    {
        field = cancelable
        progressDialog.setCancelable(field)
    }

    constructor(context: Context,@ThemeConstant themeConstant: Int = THEME_LIGHT):
            this(modeConstant = MODE_INDETERMINATE, context = context, themeConstant = themeConstant)

    init
    {
        val builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        progressDialog = builder.create()
        if (progressDialog.window != null)
        {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        }
        mode = modeConstant
        theme = themeConstant
        isCancelable = false
    }

    /**
     * Gets/Sets the Progress Value of Determinate ProgressBar.
     * Can be used only in [MODE_DETERMINATE] Mode. Does not do anything if the ProgressDialog is in [MODE_INDETERMINATE] Mode.
     * If the parameter progress is greater than MaxValue for Setter, MaxValue will be set as Progress.
     * The Getter will return the current Progress Value if it is in [MODE_DETERMINATE] Mode, else -1 will be returned.
     * @see incrementProgress
     * @throws UnsupportedOperationException If the Setter is called on [MODE_INDETERMINATE] Mode.
     */
    var progress: Int
    get()
    {
        return if (isDeterminate) binding.progressbarDeterminate.progress else -1
    }
    @Throws(UnsupportedOperationException::class)
    set(progressValue)
    {
        if (isDeterminate)
        {
            binding.progressbarDeterminate.setProgress(progressValue, true)
            setProgressText()
        }
        else
        {
            throw UnsupportedOperationException("Cannot set Progress for Indeterminate ProgressDialog !")
        }
    }

    /**
     * Gets the Message showed in the ProgressDialog.
     * @return The Text displayed in current [MODE_INDETERMINATE] ProgressBar or [MODE_DETERMINATE] ProgressBar respectively.
     */
    fun getMessage() : CharSequence
    {
        return if(!isDeterminate) binding.textViewIndeterminate.text else binding.textViewDeterminate.text
    }

    /**
     * Sets the Text to be displayed alongside ProgressBar.
     * Message is "Loading" by Default.
     * @param message The Text to be displayed inside ProgressDialog.
     */
    fun setMessage(message: CharSequence)
    {
        if (!isDeterminate)
        {
            binding.textViewIndeterminate.text = message
        }
        else
        {
            binding.textViewDeterminate.text = message
        }
    }

    /**
     * Sets the Text from the resID provided, to be displayed alongside ProgressBar.
     * Message is "Loading" by Default.
     * @param messageResID The resource id for the string resource.
     */
    fun setMessage(@StringRes messageResID: Int)
    {
        setMessage(context.getText(messageResID).toString())
    }

    /**
     * Gets the Current Title of The ProgressDialog.
     * @return The Title of the ProgressDialog if the Title is Visible, else an empty [CharSequence].
     */
    fun getTitle() : CharSequence
    {
        return if(isVisible(binding.titleView)) binding.titleView.text else ""
    }

    /**
     * Sets the Title of ProgressDialog.
     * This is "ProgressDialog" by Default.
     * Title is Hidden by Default. This Method makes the Title Visible.
     * Title will be made visible automatically if [setNegativeButton] or
     * [setNegativeButton] was used before.
     * @param title The text to be set as the Title of ProgressDialog.
     */
    fun setTitle(title: CharSequence)
    {
        binding.titleView.text = title
        if (isGone(binding.titleView))
            binding.titleView.visibility = View.VISIBLE
    }

    /**
     * Sets the Title of ProgressDialog using the String resource given.
     * Title is "ProgressDialog" by Default.
     * Title is Hidden by Default. This Method makes the Title Visible.
     * Title will be made visible automatically if [setNegativeButton] or
     * [setNegativeButton] was used before.
     * @param titleResID The resource id of the string resource.
     */
    fun setTitle(@StringRes titleResID: Int)
    {
        setTitle(context.getText(titleResID).toString())
    }

    /**
     * Hides the Title of ProgressDialog.
     * Title is Hidden by Default.
     * Use this method only if you have used [setTitle] before.
     * This method won't work if [setNegativeButton] was used before.
     * @return true if Title is Hid. false if the Title is already Hidden or if NegativeButton is used.
     */
    fun hideTitle(): Boolean
    {
        if (!isGone(binding.negativeButton)) return false
        if (isVisible(binding.titleView)) binding.titleView.visibility = View.GONE
        return true
    }

    /**
     * Gets/Sets the Increment Value of the [MODE_DETERMINATE] ProgressDialog.
     * Gets the Increment Value only if the ProgressDialog is in [MODE_DETERMINATE] Mode, else -1 is returned.
     * Sets the Increment Value only if the ProgressDialog is in [MODE_DETERMINATE] Mode. If the passed parameter is greater than maxValue, it will not be set.
     */
    var incrementValue: Int = 1
    get()
    {
        return if (isDeterminate) field else -1
    }
    set(incrementAmount)
    {
        if (isDeterminate)
        {
            field = incrementAmount
        }
    }

    /**
     * Increments the Progress Value of Determinate ProgressBar using the Offset Value set using [incrementValue].
     * Can be used only in [MODE_DETERMINATE] Mode.
     * @throws UnsupportedOperationException If called in [MODE_INDETERMINATE] Mode.
     */
    @Throws(UnsupportedOperationException::class)
    fun incrementProgress()
    {
        if (isDeterminate)
        {
            binding.progressbarDeterminate.incrementProgressBy(incrementValue)
            setProgressText()
        }
        else
        {
            throw UnsupportedOperationException("Cannot Increment Progress in Indeterminate ProgressDialog !")
        }
    }

    /**
     * Gets/Sets the Maximum value of [MODE_DETERMINATE] ProgressBar.
     * Gets the Maximum Value only if the ProgressBar is in [MODE_DETERMINATE]. Else, -1 is returned.
     * Sets the parameter value as Maximum Value only if the ProgressBar is in [MODE_DETERMINATE] Mode.
     * It is advised to Set this value before setting [progress] or calling [incrementProgress] Method.
     * @throws UnsupportedOperationException If the Setter is called in [MODE_INDETERMINATE] Mode.
     */
    var maxValue: Int
    get()
    {
        return if (isDeterminate) binding.progressbarDeterminate.max else -1
    }
    @Throws(UnsupportedOperationException::class)
    set(maxValue)
    {
        if (isDeterminate)
        {
            binding.progressbarDeterminate.max = maxValue
            progress = progress
        }
        else
        {
            throw UnsupportedOperationException("Cannot set Max Value in Indeterminate ProgressDialog !")
        }
    }

    /**
     * Toggles the Progress TextView's format as Fraction if "true" is passed.
     * Progress TextView's Default format is Percentage format.
     * Can be used only in [MODE_DETERMINATE].
     * If [hideProgressText] was used before, this method will again make Progress TextView visible.
     * @param progressTextAsFraction The boolean value to change Progress TextView's format.
     * @return true if Mode is [MODE_DETERMINATE] and Progress is set. false otherwise and also on Redundant Calls.
     */
    fun showProgressTextAsFraction(progressTextAsFraction: Boolean): Boolean
    {
        return if (isDeterminate)
        {
            if (progressTextAsFraction)
            {
                when (progressViewMode)
                {
                    SHOW_AS_FRACTION -> return false
                    SHOW_AS_PERCENT, HIDE_PROGRESS_TEXT -> {
                        progressViewMode = SHOW_AS_FRACTION
                        binding.progressTextView.text = progressAsFraction
                    }
                }
            } else {
                when (progressViewMode) {
                    SHOW_AS_PERCENT -> return false
                    SHOW_AS_FRACTION, HIDE_PROGRESS_TEXT -> {
                        progressViewMode = SHOW_AS_PERCENT
                        binding.progressTextView.text = progressAsPercent
                    }
                }
            }
            true
        }
        else
        {
            false
        }
    }

    /**
     * Hides the Progress TextView.
     * Can be used only in [MODE_DETERMINATE].
     * @return true if Mode is [MODE_DETERMINATE] and Progress TextView is hidden. false otherwise.
     */
    fun hideProgressText(): Boolean
    {
        return if (isDeterminate)
        {
            progressViewMode = HIDE_PROGRESS_TEXT
            if (!isGone(binding.progressTextView))
            {
                binding.progressTextView.visibility = View.GONE
            }
            true
        }
        else
        {
            false
        }
    }

    /**
     * Starts the ProgressDialog and shows it on the Screen.
     */
    @SuppressLint("NewApi")
    fun show()
    {
        if (autoThemeEnabled)
        {
            if (isSystemInNightMode)
            {
                if(theme != THEME_DARK)
                    setThemeInternal(THEME_DARK)
            }
            else if (!isSystemInNightMode)
            {
                if(theme != THEME_LIGHT)
                    setThemeInternal(THEME_LIGHT)
            }
        }
        progressDialog.show()
    }

    /**
     * Dismisses the ProgressDialog, removing it from the Screen.
     * Calls [DialogInterface.OnDismissListener], if it is set using [setOnDismissListener].
     * To be used after the Task calling ProgressDialog is Over or if any Exception Occurs during Task execution.
     * In case of passing to Another Activity/Fragment, this method SHOULD be called before starting the next Activity/Fragment.
     * Else, it would cause WindowLeakedException.
     */
    fun dismiss()
    {
        progressDialog.dismiss()
    }

    /**
     * Sets the [DialogInterface.OnCancelListener] for ProgressDialog.
     * Should be used only if [isCancelable] was set true earlier since cancel() cannot be called explicitly
     * and ProgressDialog is NOT cancelable by Default.
     * @param onCancelListener [DialogInterface.OnCancelListener] listener object.
     * @return true if ProgressDialog is Cancelable. false otherwise.
     */
    fun setOnCancelListener(onCancelListener: DialogInterface.OnCancelListener): Boolean
    {
        return if (isCancelable)
        {
            progressDialog.setOnCancelListener(onCancelListener)
            true
        }
        else
        {
            false
        }
    }

    /**
     * Sets the [DialogInterface.OnDismissListener] for ProgressDialog.
     * @param onDismissListener [DialogInterface.OnDismissListener] listener object.
     */
    fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener) {
        progressDialog.setOnDismissListener(onDismissListener)
    }

    /**
     * Sets the [DialogInterface.OnShowListener] for ProgressDialog.
     * @param onShowListener [DialogInterface.OnShowListener] listener object.
     */
    fun setOnShowListener(onShowListener: OnShowListener)
    {
        progressDialog.setOnShowListener(onShowListener)
    }

    /**
     * Checks if ProgressValue is equal to MaxValue.
     * Can be used only in [MODE_DETERMINATE].
     * @return true if ProgressValue is equal to MaxValue. false otherwise and also if mode is not [MODE_DETERMINATE].
     */
    fun hasProgressReachedMaxValue(): Boolean = if (isDeterminate) progress == maxValue else false

    /**
     * Gets the Integral Value required to reach MaxValue from the current ProgressValue.
     * Can be used only in [MODE_DETERMINATE].
     * @return The Integral Amount required to reach MaxValue. -1 if mode is not [MODE_DETERMINATE].
     */
    fun remainingProgress(): Int = if (isDeterminate) maxValue - progress else -1

    /**
     * Gets/Sets the Secondary ProgressValue.
     * Can be used only in [MODE_DETERMINATE].
     * Getter returns the Secondary ProgressValue only if the ProgressDialog is in [MODE_DETERMINATE] else -1 is returned.
     * Setter works only when called on [MODE_DETERMINATE] Mode.
     */
    var secondaryProgress: Int
    get() = if (isDeterminate) binding.progressbarDeterminate.secondaryProgress else -1
    set(secondaryProgressValue)
    {
        if (isDeterminate)
        {
            binding.progressbarDeterminate.secondaryProgress = secondaryProgressValue
        }
    }

    /**
     * Gets the Integral Value required to reach MaxValue from the current Secondary ProgressValue.
     * Can be used only in [MODE_DETERMINATE].
     * @return The Integral Amount required to reach MaxValue from Secondary ProgressValue. -1 if mode is not [MODE_DETERMINATE].
     */
    fun secondaryRemainingProgress(): Int = if (isDeterminate) maxValue - secondaryProgress else -1

    /**
     * Checks if Secondary ProgressValue is equal to MaxValue.
     * Can be used only in [MODE_DETERMINATE].
     * @return true if Secondary ProgressValue is equal to MaxValue. false otherwise and also if mode is not [MODE_DETERMINATE].
     */
    fun hasSecondaryProgressReachedMaxValue(): Boolean = if (isDeterminate) secondaryProgress == maxValue else false

    /**
     * Sets a Custom Drawable to the Indeterminate ProgressBar.
     * Can be used only in [MODE_DETERMINATE].
     * Use this when you need to define a custom Drawable Design for Indeterminate ProgressBar.
     * @param progressDrawable The Drawable object used to draw the Indeterminate ProgressBar.
     * @return true if mode is [MODE_INDETERMINATE] and the Drawable is set. false otherwise.
     * @see getIndeterminateDrawable
     */
    fun setIndeterminateDrawable(progressDrawable: Drawable?): Boolean
    {
        return if (!isDeterminate)
        {
            binding.progressbarIndeterminate.indeterminateDrawable = progressDrawable
            true
        }
        else
        {
            false
        }
    }

    /**
     * Sets a Custom Drawable from the passed Drawable resource to the Indeterminate ProgressBar.
     * Can be used only in [MODE_INDETERMINATE].
     * Use this when you need to define a custom Drawable Design for Indeterminate ProgressBar.
     * @param progressDrawableResID The resource id of the Drawable resource used to draw the Indeterminate ProgressBar.
     * @return true if mode is [MODE_INDETERMINATE] and the Drawable is set. false otherwise.
     * @see getIndeterminateDrawable
     */
    fun setIndeterminateDrawable(@DrawableRes progressDrawableResID: Int): Boolean
    {
        return setIndeterminateDrawable(ContextCompat.getDrawable(context, progressDrawableResID))
    }

    /**
     * Gets the Drawable object used to draw the Indeterminate ProgressBar.
     * Can be used only in [MODE_INDETERMINATE].
     * @return Drawable Object if mode is [MODE_INDETERMINATE]. null otherwise.
     * @see setIndeterminateDrawable
     */
    fun getIndeterminateDrawable(): Drawable?
    {
       return if (!isDeterminate) binding.progressbarIndeterminate.indeterminateDrawable else null
    }

    /**
     * Sets a Custom Drawable to the Determinate ProgressBar.
     * Can be used only in [MODE_DETERMINATE].
     * Use this when you need to define a custom Drawable Design for Determinate ProgressBar.
     * @param progressDrawable The Drawable object used to draw the Determinate ProgressBar.
     * @return true if mode is [MODE_DETERMINATE] and the Drawable is set. false otherwise.
     * @see getDeterminateDrawable
     */
    fun setDeterminateDrawable(progressDrawable: Drawable?): Boolean
    {
        return if (isDeterminate) {
            binding.progressbarDeterminate.progressDrawable = progressDrawable
            true
        } else {
            false
        }
    }

    /**
     * Sets a Custom Drawable from the passed Drawable resource to the Determinate ProgressBar.
     * Can be used only in [MODE_DETERMINATE].
     * Use this when you need to define a custom Drawable Design for Determinate ProgressBar.
     * @param progressDrawableResID The resource id of the Drawable resource used to draw the Determinate ProgressBar.
     * @return true if mode is [MODE_DETERMINATE] and the Drawable is set. false otherwise.
     * @see getDeterminateDrawable
     */
    fun setDeterminateDrawable(@DrawableRes progressDrawableResID: Int): Boolean {
        return setDeterminateDrawable(ContextCompat.getDrawable(context, progressDrawableResID))
    }

    /**
     * Gets the Drawable object used to draw the Determinate ProgressBar.
     * Can be used only in [MODE_DETERMINATE].
     * @return Drawable Object if mode is [MODE_DETERMINATE]. null otherwise.
     * @see setDeterminateDrawable
     */
    fun getDeterminateDrawable(): Drawable?
    {
        return if (isDeterminate) binding.progressbarDeterminate.progressDrawable else null
    }

    /**
     * Gets/Sets the Tint List Associated with the Current [MODE_DETERMINATE] or [MODE_INDETERMINATE] Mode ProgressDialog respectively.
     */
    var progressTintList: ColorStateList?
    get() = if (isDeterminate) binding.progressbarDeterminate.progressTintList else binding.progressbarIndeterminate.indeterminateTintList
    set(tintList)
    {
        if (!isDeterminate)
            binding.progressbarIndeterminate.indeterminateTintList = tintList
        else
            binding.progressbarDeterminate.progressTintList = tintList
    }

    /**
     * Gets/Sets the Secondary Progress Tint List Associated with the Current [MODE_DETERMINATE] or [MODE_INDETERMINATE] Mode ProgressDialog respectively.
     */
    var secondaryProgressTintList: ColorStateList?
    get() = if (isDeterminate) binding.progressbarDeterminate.secondaryProgressTintList else binding.progressbarIndeterminate.secondaryProgressTintList
    set(secondaryTintList)
    {
        if (!isDeterminate)
            binding.progressbarIndeterminate.secondaryProgressTintList = secondaryTintList
        else
            binding.progressbarDeterminate.secondaryProgressTintList = secondaryTintList
    }

    /**
     * Sets the NegativeButton with the passed text for the ProgressDialog and also sets the OnClickListener for the Button.
     * NegativeButton is hidden by default. This method makes it visible.
     * This method also enables the Title to be shown (even if it was hidden till then).
     * If [setTitle] or [setTitle] was used before, and new Title is passed, the new Title will Override the previously set Title.
     * If null is passed for listener, default listener which dismisses the ProgressDialog when clicked, will be used.
     * @param text The text to be set in the NegativeButton.
     * @param listener The [View.OnClickListener] listener to be set to NegativeButton. If null, default cancel listener will be used.
     */
    fun setNegativeButton(text: CharSequence, title: CharSequence, listener: View.OnClickListener?)
    {
        binding.negativeButton.text = text
        binding.negativeButton.setOnClickListener(listener ?: View.OnClickListener { dismiss() })
        if (isGone(binding.negativeButton))
        {
            enableNegativeButton(title)
        }
    }

    /**
     * Sets the NegativeButton with the text from passed resource id for the ProgressDialog and also sets the OnClickListener for the Button.
     * NegativeButton is hidden by default. This method makes it visible.
     * This method also enables the Title to be shown (even if it was hidden till then).
     * If [setTitle] or [setTitle] was used before, and new titleResID is passed, the new Title will Override the previously set Title.
     * If null is passed for listener, default listener which dismisses the ProgressDialog when clicked, will be used.
     * @param textResID The resource id of the text to be set in the NegativeButton.
     * @param listener The [View.OnClickListener] listener to be set to NegativeButton.
     */
    fun setNegativeButton(@StringRes textResID: Int, @StringRes titleResID: Int, listener: View.OnClickListener?)
    {
        setNegativeButton(context.getText(textResID).toString(), context.getText(titleResID).toString(), listener)
    }

    /**
     * Hides the NegativeButton. NegativeButton is Hidden by Default.
     * Use this method only if you have used [setNegativeButton] or
     * [setNegativeButton] before.
     * Note : This method will not hide the Title. You have to explicitly call [hideTitle] to do the same.
     */
    fun hideNegativeButton() {
        if (!isGone(binding.negativeButton)) {
            binding.negativeButton.visibility = View.GONE
        }
    }

    private val progressAsFraction: String
       get() = "${progress}/${maxValue}"

    private val progressAsPercent: String
        get()
        {
            val `val` = progress.toDouble() / maxValue.toDouble() * 100
            return String.format(Locale.getDefault(), "%.2f", `val`) + "%"
        }

    private fun setProgressText()
    {
        when (progressViewMode)
        {
            SHOW_AS_FRACTION -> binding.progressTextView.text =
                progressAsFraction
            SHOW_AS_PERCENT -> binding.progressTextView.text =
                progressAsPercent
            HIDE_PROGRESS_TEXT -> {}
        }
    }

    private fun isVisible(view: View): Boolean
    {
        return view.visibility == View.VISIBLE
    }

    private fun isGone(view: View): Boolean
    {
        return view.visibility == View.GONE
    }

    private val isDeterminate: Boolean
        get() = mode == MODE_DETERMINATE

    @get:RequiresApi(api = Build.VERSION_CODES.R)
    private val isSystemInNightMode: Boolean
        get() = context.resources.configuration.isNightModeActive

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    private fun isAboveOrEqualToAnd11(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    private fun enableNegativeButton(title: CharSequence)
    {
        binding.negativeButton.visibility = View.VISIBLE
        if (isGone(binding.titleView)) setTitle(title)
    }

    private fun setThemeInternal(@ThemeConstant themeConstant: Int): Boolean
    {
        return when (themeConstant)
        {
            THEME_DARK -> {
                binding.dialogLayout.background = ContextCompat.getDrawable(context, R.drawable.bg_dialog_dark)
                binding.titleView.setTextColor(ContextCompat.getColor(context, R.color.white))
                binding.textViewIndeterminate.setTextColor(ContextCompat.getColor(context, R.color.white))
                binding.textViewDeterminate.setTextColor(ContextCompat.getColor(context, R.color.white))
                binding.progressTextView.setTextColor(ContextCompat.getColor(context, R.color.white_dark))
                binding.negativeButton.setTextColor(ContextCompat.getColor(context, R.color.white))
                true
            }
            THEME_LIGHT ->
            {
                binding.dialogLayout.background = ContextCompat.getDrawable(context, R.drawable.bg_dialog)
                binding.titleView.setTextColor(ContextCompat.getColor(context, R.color.black))
                binding.textViewIndeterminate.setTextColor(ContextCompat.getColor(context, R.color.black))
                binding.textViewDeterminate.setTextColor(ContextCompat.getColor(context, R.color.black))
                binding.progressTextView.setTextColor(ContextCompat.getColor(context, R.color.black_light))
                binding.negativeButton.setTextColor(ContextCompat.getColor(context, R.color.black))
                true
            }
            else -> false
        }
    }
}