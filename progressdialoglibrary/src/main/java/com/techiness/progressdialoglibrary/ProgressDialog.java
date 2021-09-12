/*
 * An easy-to-use ProgressDialog Library provided by Techiness Overloaded for Android API 24 and above.
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.techiness.progressdialoglibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;
/**
 * A highly customisable ProgressDialog Class for Android API Level 24 and above.
 * Built upon Android's AlertDialog. So no Compatibility and Reliability issues.
 * It provides a neat Material Design UI for ProgressDialog and supports both Determinate and Indeterminate ProgressBars.
 * It also has support for Dark Theme.
 * It can also have a NegativeButton.
 * It can be customized according to User's needs using the provided Methods.
 */
public class ProgressDialog
{
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({THEME_LIGHT,THEME_DARK,THEME_FOLLOW_SYSTEM})
    public @interface ThemeConstant{}
    /**
     * The default Theme for ProgressDialog (even if it is not passed in Constructor).
     * Suitable for apps having a Light Theme.
     * Theme can be changed later using {@link #setTheme(int themeConstant)}.
     */
    public static final int THEME_LIGHT = 1;
    /**
     * This theme is suitable for apps having a Dark Theme.
     * This Constant SHOULD be passed explicitly in the Constructor for setting Dark Theme for ProgressDialog.
     * Theme can be changed later using {@link #setTheme(int themeConstant)}.
     */
    public static final int THEME_DARK = 2;
    /**
     * When this ThemeConstant is used, ProgressDialog's theme is automatically changed to match the System's theme each time before {@link #show()} is called.
     * This Constant can be used starting from Android API Level 31 (Android 11) ONLY.
     * {@link #setTheme(int themeConstant)} will throw {@link IllegalArgumentException} if this Constant is passed in method call in Android versions lower than Android 11.
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    public static final int THEME_FOLLOW_SYSTEM = -1;
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MODE_INDETERMINATE,MODE_DETERMINATE})
    public @interface ModeConstant{}
    /**
     * The default mode for ProgressDialog where an Indeterminate Spinner is shown for indicating Progress (even if it is not passed in Constructor).
     * Suitable for implementations where the exact progress of an operation is unknown to the Developer.
     */
    public static final int MODE_INDETERMINATE = 3;
    /**
     * In this mode, a Determinate ProgressBar is shown inside the ProgressDialog for indicating Progress.
     * It also has a TextView for numerically showing the Progress Value either as Percentage or as Fraction.
     * Progress Value is shown as Percentage by Default which can be changed using {@link #showProgressTextAsFraction(boolean showProgressTextAsFraction)};
     */
    public static final int MODE_DETERMINATE = 4;
    private static final int SHOW_AS_FRACTION = 5;
    private static final int SHOW_AS_PERCENT = 6;
    private static final int HIDE_PROGRESS_TEXT = 7;
    private final Context context;
    private TextView titleView,textViewIndeterminate,textViewDeterminate,progressTextView,negativeButton;
    private ProgressBar progressBarDeterminate,progressBarIndeterminate;
    private AlertDialog progressDialog;
    private ConstraintLayout dialogLayout;
    private int mode,theme,incrementAmt,progressViewMode;
    private boolean cancelable,autoThemeEnabled;
    /**
     * Simple Constructor accepting only the Activity Level Context as Argument.
     * Theme is set as Light Theme by Default (which can be changed later using {@link #setTheme(int themeConstant)}).
     * Mode is set as Indeterminate by Default (which can be changed later using {@link #setMode(int modeConstant)}).
     */
    public ProgressDialog(Context context)
    {
        this.context=context;
        initialiseDialog(THEME_LIGHT,MODE_INDETERMINATE);
    }
    /**
     * A Constructor accepting the Activity Level Context and Theme Constant as Arguments.
     * Theme is set as Light Theme if {@link #THEME_LIGHT} is passed (This can be changed later using {@link #setTheme(int themeConstant)}).
     * Theme is set as Dark Theme if {@link #THEME_DARK} is passed (This can be changed later using {@link #setTheme(int themeConstant)}).
     * Theme is automatically decided at runtime according to System's Theme if {@link #THEME_FOLLOW_SYSTEM} is passed (This can be changed later using {@link #setTheme(int themeConstant)}).
     * NOTE : {@link #THEME_FOLLOW_SYSTEM} can be used starting from Android API Level 31 only.
     * Mode is set as Indeterminate by Default (which can be changed later using {@link #setMode(int modeConstant)}).
     */
    public ProgressDialog(Context context,@ThemeConstant int themeConstant)
    {
        this.context = context;
        initialiseDialog(themeConstant,MODE_INDETERMINATE);
    }
    /**
     * A Constructor accepting the Mode Constant, Activity Level Context and Theme Constant as Arguments.
     * Mode is set as Determinate if {@link } is passed (This can be changed later using {@link #setMode(int modeConstant)}).
     * Mode is set as Indeterminate if {@link #MODE_INDETERMINATE} is passed (This can be changed later using {@link #setMode(int modeConstant)}).
     * Theme is set as Light Theme if {@link #THEME_LIGHT} is passed (This can be changed later using {@link #setTheme(int themeConstant)}).
     * Theme is set as Dark Theme if {@link #THEME_DARK} is passed (This can be changed later using {@link #setTheme(int themeConstant)}).
     * Theme is automatically decided at runtime according to System's Theme if {@link #THEME_FOLLOW_SYSTEM} is passed (This can be changed later using {@link #setTheme(int themeConstant)}).
     * NOTE : {@link #THEME_FOLLOW_SYSTEM} can be used starting from Android API Level 31 only.
     */
    public ProgressDialog(@ModeConstant int modeConstant,Context context,@ThemeConstant int themeConstant)
    {
        this.context=context;
        initialiseDialog(themeConstant,modeConstant);
    }
    /**
     * A Constructor accepting the Mode Constant and Activity Level Context as Arguments.
     * Mode is set as Determinate if {@link #MODE_DETERMINATE} is passed (This can be changed later using {@link #setMode(int modeConstant)}).
     * Mode is set as Indeterminate if {@link #MODE_INDETERMINATE} is passed (This can be changed later using {@link #setMode(int modeConstant)}).
     * Theme is set as Light Theme by Default (which can be changed later using {@link #setTheme(int themeConstant)}).
     */
    public ProgressDialog(@ModeConstant int modeConstant,Context context)
    {
        this.context=context;
        initialiseDialog(THEME_LIGHT,modeConstant);
    }
    private void initialiseDialog(@ThemeConstant int themeValue,@ModeConstant int modeValue)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_progressdialog,null);
        dialogLayout = view.findViewById(R.id.dialog_layout);
        titleView = view.findViewById(R.id.title_progressDialog);
        textViewDeterminate = view.findViewById(R.id.textView_determinate);
        textViewIndeterminate = view.findViewById(R.id.textView_indeterminate);
        progressBarIndeterminate = view.findViewById(R.id.progressbar_indeterminate);
        progressBarDeterminate = view.findViewById(R.id.progressbar_determinate);
        progressTextView = view.findViewById(R.id.ProgressTextView);
        negativeButton = view.findViewById(R.id.negativeBtn);
        setTheme(themeValue);
        setMode(modeValue);
        builder.setView(view);
        progressDialog = builder.create();
        if (progressDialog.getWindow() != null)
        {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        setCancelable(false);
    }
    /**
     * Sets/Changes the mode of ProgressDialog which is {@link #MODE_INDETERMINATE} by Default.
     * If you're going to use only one Mode constantly, this method is not needed. Instead, use an appropriate Constructor to set the required Mode during Instantiation.
     * @param modeConstant The Mode Constant to be passed as Argument ({@link #MODE_DETERMINATE} or {@link #MODE_INDETERMINATE}).
     * @return true if the passed modeConstant is valid and is set. false if the passed Mode is the current Mode or if modeConstant is invalid.
     */
    public boolean setMode(@ModeConstant int modeConstant)
    {
        if(modeConstant==mode)
            return false;
        switch (modeConstant)
        {
            case MODE_DETERMINATE:
                textViewIndeterminate.setVisibility(View.GONE);
                progressBarIndeterminate.setVisibility(View.GONE);
                textViewDeterminate.setVisibility(View.VISIBLE);
                progressBarDeterminate.setVisibility(View.VISIBLE);
                progressViewMode=SHOW_AS_PERCENT;
                progressTextView.setVisibility(View.VISIBLE);
                incrementAmt = incrementAmt==0 ? 1 : incrementAmt;
                mode = modeConstant;
                return true;
            case MODE_INDETERMINATE:
                textViewDeterminate.setVisibility(View.GONE);
                progressBarDeterminate.setVisibility(View.GONE);
                progressTextView.setVisibility(View.GONE);
                textViewIndeterminate.setVisibility(View.VISIBLE);
                progressBarIndeterminate.setVisibility(View.VISIBLE);
                mode = modeConstant;
                return true;
            default:
                return false;
        }
    }
    /**
     * Returns the Current Mode of ProgressDialog.
     * @return The current Mode of ProgressDialog ({@link #MODE_DETERMINATE} or {@link #MODE_INDETERMINATE}).
     */
    @ModeConstant
    public int getMode()
    {
        return mode;
    }
    /**
     * Sets/Changes the Theme of ProgressDialog which is {@link #THEME_LIGHT} by Default.
     * If you're going to use only one Theme constantly, this method is not needed. Instead, use an appropriate Constructor to set the required Theme during Instantiation.
     * @param themeConstant The Theme Constant to be passed.Use {@link #THEME_LIGHT} for Light Mode. Use {@link #THEME_DARK} for Dark Mode. Use {@link #THEME_FOLLOW_SYSTEM} for AutoTheming based on System theme (can be used starting from Android 11 (API Level 31) ONLY).
     * @return true if the passed themeConstant is valid and is set. false if the passed Theme is the current Theme or if themeConstant is invalid.
     * @throws IllegalArgumentException if {@link #THEME_FOLLOW_SYSTEM} is passed as Argument to this method in Android Versions lower than Android 11 (API Level 30)}.
     */
    public boolean setTheme(@ThemeConstant int themeConstant) throws IllegalArgumentException
    {
        if(themeConstant == theme)
            return false;
        switch(themeConstant)
        {
            case THEME_DARK:
            case THEME_LIGHT:
                autoThemeEnabled = false;
                return setThemeInternal(themeConstant);
            case THEME_FOLLOW_SYSTEM:
                if(!isAboveOrEqualToAnd11())
                {
                    throw new IllegalArgumentException("THEME_FOLLOW_SYSTEM can be used starting from Android 11 (API Level 30) only !");
                }
                autoThemeEnabled = true;
                return true;
            default:
                return false;
        }
    }
    /**
     * Returns the Current Theme of ProgressDialog.
     * @return The current Theme of ProgressDialog ({@link #THEME_LIGHT} or {@link #THEME_DARK} or {@link #THEME_FOLLOW_SYSTEM}(starting from Android 11)).
     */
    @ThemeConstant
    public int getTheme()
    {
        return autoThemeEnabled ? THEME_FOLLOW_SYSTEM : theme;
    }
    /**
     * Sets the Text to be displayed alongside ProgressBar.
     * Message is "Loading" by Default. This can be used if null is passed as parameter.
     * Alternative to {@link #setMessage(int resID)}.
     * @param message The Text to be displayed inside ProgressDialog. If null is passed, "Loading" will be set.
     */
    public void setMessage(@Nullable CharSequence message)
    {
        if(message!=null) 
        {
            if (!isDeterminate())
            {
                textViewIndeterminate.setText(message);
            } 
            else
                {
                textViewDeterminate.setText(message);
                }
        }
    }
    /**
     * Sets the Text from the resID provided, to be displayed alongside ProgressBar.
     * Message is "Loading" by Default.
     * Alternative to {@link #setMessage(CharSequence message)}.
     * @param messageResID The resource id for the string resource.
     */
    public void setMessage(@StringRes int messageResID)
    {
        setMessage(context.getText(messageResID).toString());
    }
    /**
     * Sets the Title of ProgressDialog.
     * This is "ProgressDialog" by Default. This can be used by passing null as parameter.
     * Title is Hidden by Default. This Method makes the Title Visible even if null is passed as parameter.
     * Title will be made visible automatically if {@link #setNegativeButton(CharSequence text, CharSequence title, View.OnClickListener listener)} or
     * {@link #setNegativeButton(int resID, int titleResID, View.OnClickListener listener)} was used before.
     * Alternative to {@link #setTitle(int resID)}.
     * @param title The text to be set as the Title of ProgressDialog. If null is passed, "ProgressDialog" will be set.
     */
    public void setTitle(@Nullable CharSequence title)
    {
        if(title!=null)
            titleView.setText(title);
        if(isGone(titleView))
            titleView.setVisibility(View.VISIBLE);
    }
    /**
     * Sets the Title of ProgressDialog using the String resource given.
     * Title is "ProgressDialog" by Default.
     * Title is Hidden by Default. This Method makes the Title Visible even if "null" is passed as argument.
     * Title will be made visible automatically if {@link #setNegativeButton(CharSequence text,CharSequence title,View.OnClickListener listener)} or
     * {@link #setNegativeButton(int resID,int titleResID,View.OnClickListener listener)} was used before.
     * Alternative to {@link #setTitle(CharSequence title)}.
     * @param titleResID The resource id of the string resource.
     */
    public void setTitle(@StringRes int titleResID)
    {
        setTitle(context.getText(titleResID).toString());
    }
    /**
     * Hides the Title of ProgressDialog.
     * Title is Hidden by Default.
     * Use this method only if you have used {@link #setTitle(CharSequence title)} before.
     * This method won't work if {@link #setNegativeButton(CharSequence text, CharSequence title, View.OnClickListener listener)} or
     * {@link #setNegativeButton(int resID, int titleResID, View.OnClickListener listener)} was used before.
     * @return true if Title is Hid. false if the Title is already Hidden or if NegativeButton is used.
     */
    public boolean hideTitle()
    {
        if(!isGone(negativeButton))
            return false;
        if(isVisible(titleView))
            titleView.setVisibility(View.GONE);
        return true;
    }
    /**
     * Sets the Progress Value of Determinate ProgressBar.
     * Can be used only in {@link #MODE_DETERMINATE} Mode.
     * If the parameter progress is greater than MaxValue, MaxValue will be set as Progress.
     * @param progress The Integral Progress Value to be set in Determinate ProgressBar.
     * @return true if Mode is {@link #MODE_DETERMINATE} and Progress is set. false otherwise.
     * @see #incrementProgress()
     */
    public boolean setProgress(int progress)
    {
        if(isDeterminate())
        {
            progressBarDeterminate.setProgress(progress,true);
            setProgressText();
            return true;
        }
        else
            {
                return false;
            }
    }
    /**
     * Sets the Increment Offset Value for Determinate ProgressBar.
     * The value is 1 by Default.
     * Should be called before calling {@link #incrementProgress()} method.
     * Can be used only in {@link #MODE_DETERMINATE} Mode.
     * @param increment The Integral Offset Value for Incrementing Progress in Determinate ProgressBar.
     * @return true if Mode is {@link #MODE_DETERMINATE} and Progress is set. false otherwise.
     * @see #incrementProgress()
     */
    public boolean setIncrementValue(int increment)
    {
        if(isDeterminate())
        {
            incrementAmt=increment;
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Returns the Current Increment Offset Value.
     * Can be used only in {@link #MODE_DETERMINATE} Mode.
     * @return The Current Increment Offset Value of Determinate ProgressBar if Mode is {@link #MODE_DETERMINATE}. Else -1 is returned.
     */
    public int getIncrementValue()
    {
        return isDeterminate() ? incrementAmt : -1;
    }
    /**
     * Increments the Progress Value of Determinate ProgressBar using the Offset Value set using {@link #setIncrementValue(int increment)}.
     * Can be used only in {@link #MODE_DETERMINATE) Mode.
     * If the progress is greater than MaxValue after incrementing, MaxValue will be set as Progress.
     * This method is preferred over {@link #setProgress(int progress)} for continuous and uniform Progress Increments.
     * @return true if Mode is {@link #MODE_DETERMINATE} and Progress is set. false otherwise.
     * @see #setIncrementValue(int increment)
     * @see #setProgress(int progress)
     */
    public boolean incrementProgress()
    {
        if(isDeterminate())
        {
            progressBarDeterminate.incrementProgressBy(incrementAmt);
            setProgressText();
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Sets the Maximum value of Determinate ProgressBar.
     * The Default MaxValue of Determinate ProgressBar is 100.
     * Can be used only in {@link #MODE_DETERMINATE} Mode.
     * It is advised to use this method before calling {@link #setProgress(int progress)} or {@link #incrementProgress()} if you want to change the Default MaxValue.
     * @param maxValue The Integral Value to be set as MaxValue for Determinate ProgressBar.
     * @return true if Mode is {@link #MODE_DETERMINATE} and Progress is set. false otherwise.
     */
    public boolean setMaxValue(int maxValue)
    {
        if(isDeterminate())
        {
            progressBarDeterminate.setMax(maxValue);
            setProgress(getProgress());
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Returns the MaxValue of Determinate ProgressBar.
     * Can be used only in {@link #MODE_DETERMINATE} Mode.
     * @return The Current MaxValue of Determinate ProgressBar if Mode is {@link #MODE_DETERMINATE}. Else -1 is returned.
     */
    public int getMaxValue()
    {
        return isDeterminate() ? progressBarDeterminate.getMax() : -1;
    }
    /**
     * Returns the Progress Value of Determinate ProgressBar.
     * Can be used only in {@link #MODE_DETERMINATE} Mode.
     * @return The Current Progress Value of Determinate ProgressBar if Mode is {@link #MODE_DETERMINATE}. Else -1 is returned.
     */
    public int getProgress()
    {
        return isDeterminate() ? progressBarDeterminate.getProgress() : -1;
    }
    /**
     * Toggles the Progress TextView's format as Fraction if "true" is passed.
     * Progress TextView's Default format is Percentage format.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * If {@link #hideProgressText()} was used before, this method will again make Progress TextView visible.
     * @param progressTextAsFraction The boolean value to change Progress TextView's format.
     * @return true if Mode is {@link #MODE_DETERMINATE} and Progress is set. false otherwise and also on Redundant Calls.
     */
    public boolean showProgressTextAsFraction(boolean progressTextAsFraction)
    {
        if(isDeterminate())
        {
            if(progressTextAsFraction)
            {
                switch(progressViewMode)
                {
                    case SHOW_AS_FRACTION:
                        return false;
                    case SHOW_AS_PERCENT:
                    case HIDE_PROGRESS_TEXT:
                        progressViewMode=SHOW_AS_FRACTION;
                        progressTextView.setText(getProgressAsFraction());
                        break;
                }
            }
            else
            {
                switch(progressViewMode)
                {
                    case SHOW_AS_PERCENT:
                        return false;
                    case SHOW_AS_FRACTION:
                    case HIDE_PROGRESS_TEXT:
                        progressViewMode=SHOW_AS_PERCENT;
                        progressTextView.setText(getProgressAsPercent());
                        break;
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Hides the Progress TextView.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * @return true if Mode is {@link #MODE_DETERMINATE} and Progress TextView is hidden. false otherwise.
     */
    public boolean hideProgressText()
    {
        if(isDeterminate())
        {
            progressViewMode=HIDE_PROGRESS_TEXT;
            if(!isGone(progressTextView))
            {
                progressTextView.setVisibility(View.GONE);
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Starts the ProgressDialog and shows it on the Screen.
     */
    public void show()
    {
        if(autoThemeEnabled)
        {
            if(isSystemInNightMode()&&theme==THEME_LIGHT)
            {
                setThemeInternal(THEME_DARK);
            }
            else if(!isSystemInNightMode()&&theme==THEME_DARK)
            {
                setThemeInternal(THEME_LIGHT);
            }
        }
        progressDialog.show();
    }
    /**
     * Dismisses the ProgressDialog, removing it from the Screen.
     * Calls {@link DialogInterface.OnDismissListener}, if it is set using {@link #setOnDismissListener(DialogInterface.OnDismissListener OnDismissListener)}.
     * To be used after the Task calling ProgressDialog is Over or if any Exception Occurs during Task execution.
     * In case of passing to Another Activity/Fragment, this method SHOULD be called before starting the next Activity/Fragment.
     * Else, it would cause WindowLeakedException.
     */
    public void dismiss()
    {
        progressDialog.dismiss();
    }
    /**
     * Sets the {@link DialogInterface.OnCancelListener} for ProgressDialog.
     * Should be used only if {@link #setCancelable(boolean cancelable)} was passed with true earlier since cancel() cannot be called explicitly
     * and ProgressDialog is NOT cancelable by Default.
     * @param onCancelListener {@link DialogInterface.OnCancelListener} listener object.
     * @return true if ProgressDialog is Cancelable. false otherwise.
     */
    public boolean setOnCancelListener(final DialogInterface.OnCancelListener onCancelListener)
    {
        if(cancelable)
        {
            progressDialog.setOnCancelListener(onCancelListener);
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Sets the {@link DialogInterface.OnDismissListener} for ProgressDialog.
     * @param onDismissListener {@link DialogInterface.OnDismissListener} listener object.
     */
    public void setOnDismissListener(final DialogInterface.OnDismissListener onDismissListener)
    {
        progressDialog.setOnDismissListener(onDismissListener);
    }
    /**
     * Sets the {@link DialogInterface.OnShowListener} for ProgressDialog.
     * @param onShowListener {@link DialogInterface.OnShowListener} listener object.
     */
    public void setOnShowListener(final DialogInterface.OnShowListener onShowListener)
    {
        progressDialog.setOnShowListener(onShowListener);
    }
    /**
     * Toggles the Cancelable property of ProgressDialog which is false by Default.
     * If it is set to true, the User can cancel the ProgressDialog by pressing Back Button or by touching any other part of the screen.
     * It is NOT RECOMMENDED to set Cancelable to true.
     * @param cancelable boolean value which toggles the Cancelable property of ProgressDialog.
     */
    public void setCancelable(boolean cancelable)
    {
        progressDialog.setCancelable(cancelable);
        this.cancelable=cancelable;
    }
    /**
     * Checks if ProgressValue is equal to MaxValue.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * @return true if ProgressValue is equal to MaxValue. false otherwise and also if mode is not {@link #MODE_DETERMINATE}.
     */
    public boolean getHasProgressReachedMaxValue()
    {
        return isDeterminate() ? getProgress() == getMaxValue() : false;
    }
    /**
     * Gets the Integral Value required to reach MaxValue from the current ProgressValue.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * @return The Integral Amount required to reach MaxValue. -1 if mode is not {@link #MODE_DETERMINATE}.
     */
    public int getRemainingProgress()
    {
        return isDeterminate() ? getMaxValue()-getProgress() : -1;
    }
    /**
     * Sets the Secondary ProgressValue.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * @param secondaryProgress The integral value to be set as Secondary ProgressValue.
     * @return true if mode is {@link #MODE_DETERMINATE} and Secondary ProgressValue is set. false otherwise.
     */
    public boolean setSecondaryProgress(int secondaryProgress)
    {
        if(isDeterminate())
        {
            progressBarDeterminate.setSecondaryProgress(secondaryProgress);
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Gets the Secondary ProgressValue.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * @return Integral Secondary ProgressValue if mode is {@link #MODE_DETERMINATE}. -1 otherwise.
     */
    public int getSecondaryProgress()
    {
        return isDeterminate() ? progressBarDeterminate.getSecondaryProgress() : -1;
    }
    /**
     * Gets the Integral Value required to reach MaxValue from the current Secondary ProgressValue.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * @return The Integral Amount required to reach MaxValue from Secondary ProgressValue. -1 if mode is not {@link #MODE_DETERMINATE}.
     */
    public int getSecondaryRemainingProgress()
    {
        return isDeterminate() ? getMaxValue()-getSecondaryProgress() : -1;
    }
    /**
     * Checks if Secondary ProgressValue is equal to MaxValue.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * @return true if Secondary ProgressValue is equal to MaxValue. false otherwise and also if mode is not {@link #MODE_DETERMINATE}.
     */
    public boolean getHasSecondaryProgressReachedMaxValue()
    {
        return isDeterminate() ? getSecondaryProgress()==getMaxValue() : false;
    }
    /**
     * Sets a Custom Drawable to the Indeterminate ProgressBar.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * Use this when you need to define a custom Drawable Design for Indeterminate ProgressBar.
     * Alternative to {@link #setIndeterminateDrawable(int resID)}.
     * @param progressDrawable The Drawable object used to draw the Indeterminate ProgressBar.
     * @return true if mode is {@link #MODE_INDETERMINATE} and the Drawable is set. false otherwise.
     * @see #getIndeterminateDrawable()
     * @see #setProgressTintList(ColorStateList tintList)
     */
    public boolean setIndeterminateDrawable(Drawable progressDrawable)
    {
        if(!isDeterminate())
        {
            progressBarIndeterminate.setIndeterminateDrawable(progressDrawable);
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Sets a Custom Drawable from the passed Drawable resource to the Indeterminate ProgressBar.
     * Can be used only in {@link #MODE_INDETERMINATE}.
     * Use this when you need to define a custom Drawable Design for Indeterminate ProgressBar.
     * Alternative to {@link #setIndeterminateDrawable(Drawable progressDrawable)}.
     * @param progressDrawableResID The resource id of the Drawable resource used to draw the Indeterminate ProgressBar.
     * @return true if mode is {@link #MODE_INDETERMINATE} and the Drawable is set. false otherwise.
     * @see #getIndeterminateDrawable()
     * @see #setProgressTintList(ColorStateList tintList)
     */
    public boolean setIndeterminateDrawable(@DrawableRes int progressDrawableResID)
    {
        return setIndeterminateDrawable(ContextCompat.getDrawable(context,progressDrawableResID));
    }
    /**
     * Gets the Drawable object used to draw the Indeterminate ProgressBar.
     * Can be used only in {@link #MODE_INDETERMINATE}.
     * @return Drawable Object if mode is {@link #MODE_INDETERMINATE}. null otherwise.
     * @see #setIndeterminateDrawable(Drawable progressDrawable)
     * @see #getProgressTintList()
     */
    @Nullable
    public Drawable getIndeterminateDrawable()
    {
        return !isDeterminate() ? progressBarIndeterminate.getIndeterminateDrawable() : null;
    }
    /**
     * Sets a Custom Drawable to the Determinate ProgressBar.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * Use this when you need to define a custom Drawable Design for Determinate ProgressBar.
     * Alternative to {@link #setDeterminateDrawable(int resID)}.
     * @param progressDrawable The Drawable object used to draw the Determinate ProgressBar.
     * @return true if mode is {@link #MODE_DETERMINATE} and the Drawable is set. false otherwise.
     * @see #getDeterminateDrawable()
     * @see #setProgressTintList(ColorStateList tintList)
     */
    public boolean setDeterminateDrawable(Drawable progressDrawable)
    {
        if(isDeterminate())
        {
            progressBarDeterminate.setProgressDrawable(progressDrawable);
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Sets a Custom Drawable from the passed Drawable resource to the Determinate ProgressBar.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * Use this when you need to define a custom Drawable Design for Determinate ProgressBar.
     * Alternative to {@link #setDeterminateDrawable(Drawable progressDrawable)}.
     * @param progressDrawableResID The resource id of the Drawable resource used to draw the Determinate ProgressBar.
     * @return true if mode is {@link #MODE_DETERMINATE} and the Drawable is set. false otherwise.
     * @see #getDeterminateDrawable()
     * @see #setProgressTintList(ColorStateList tintList)
     */
    public boolean setDeterminateDrawable(@DrawableRes int progressDrawableResID)
    {
        return setDeterminateDrawable(ContextCompat.getDrawable(context,progressDrawableResID));
    }
    /**
     * Gets the Drawable object used to draw the Determinate ProgressBar.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * @return Drawable Object if mode is {@link #MODE_DETERMINATE}. null otherwise.
     * @see #setDeterminateDrawable(Drawable progressDrawable)
     * @see #getProgressTintList()
     */
    @Nullable
    public Drawable getDeterminateDrawable()
    {
        return isDeterminate() ? progressBarDeterminate.getProgressDrawable() : null;
    }
    /**
     * Applies a tint to Indeterminate Drawable if mode is {@link #MODE_INDETERMINATE}.
     * Applies a tint to Determinate Drawable if mode is {@link #MODE_DETERMINATE}.
     * @param tintList The ColorStateList object used to apply tint to ProgressBar's Drawable.
     * @see #getProgressTintList()
     */
    public void setProgressTintList(ColorStateList tintList)
    {
        if(!isDeterminate())
            progressBarIndeterminate.setIndeterminateTintList(tintList);
        else
            progressBarDeterminate.setProgressTintList(tintList);
    }
    /**
     * Returns the tint applied to Indeterminate Drawable if mode is {@link #MODE_INDETERMINATE}.
     * Returns the tint applied to Determinate Drawable if mode is {@link #MODE_DETERMINATE}.
     * @return ColorStateList object specifying the tint applied to ProgressBar's Drawable.
     * @see #setProgressTintList(ColorStateList tintList)
     */
    public ColorStateList getProgressTintList()
    {
        return isDeterminate() ? progressBarDeterminate.getProgressTintList() : progressBarIndeterminate.getIndeterminateTintList();
    }
    /**
     * Sets the NegativeButton with the passed text for the ProgressDialog and also sets the OnClickListener for the Button.
     * NegativeButton is hidden by default. This method makes it visible.
     * Default Text for NegativeButton is "CANCEL". This can be used by passing null for text parameter.
     * This method also enables the Title to be shown (even if it was hidden till then). If null is passed, default title "ProgressDialog" will be used.
     * If {@link #setTitle(CharSequence)} or {@link #setTitle(int)} was used before, and new Title is passed, the new Title will Override the previously set Title.
     * If null is passed for listener, default listener which dismisses the ProgressDialog when clicked, will be used.
     * Alternative to {@link #setNegativeButton(int resID,int titleResID,View.OnClickListener listener)}.
     * @param text The text to be set in the NegativeButton. If null, "CANCEL" will be set.
     * @param listener The {@link View.OnClickListener} listener to be set to NegativeButton.
     */
    public void setNegativeButton(@Nullable CharSequence text,@Nullable CharSequence title,@Nullable final View.OnClickListener listener)
    {
        if(text!=null) 
            negativeButton.setText(text);
        negativeButton.setOnClickListener(listener==null ? (View.OnClickListener) v -> dismiss() : listener);
        if(isGone(negativeButton))
        {
            enableNegativeButton(title);
        }
    }
    /**
     * Sets the NegativeButton with the text from passed resource id for the ProgressDialog and also sets the OnClickListener for the Button.
     * NegativeButton is hidden by default. This method makes it visible.
     * Default Text for NegativeButton is "CANCEL". To use default text, use {@link #setNegativeButton(CharSequence text,CharSequence title,View.OnClickListener listener)}.
     * This method also enables the Title to be shown (even if it was hidden till then). If invalid titleResID is passed, default title "ProgressDialog" will be used.
     * If {@link #setTitle(CharSequence)} or {@link #setTitle(int)} was used before, and new titleResID is passed, the new Title will Override the previously set Title.
     * If null is passed for listener, default listener which dismisses the ProgressDialog when clicked, will be used.
     * @param textResID The resource id of the text to be set in the NegativeButton.
     * @param listener The {@link View.OnClickListener} listener to be set to NegativeButton.
     */
    public void setNegativeButton(@StringRes int textResID,@StringRes int titleResID,@Nullable final View.OnClickListener listener)
    {
        setNegativeButton(context.getText(textResID).toString(),context.getText(titleResID).toString(),listener);
    }
    /**
     * Hides the NegativeButton. NegativeButton is Hidden by Default.
     * Use this method only if you have used {@link #setNegativeButton(CharSequence text, CharSequence title, View.OnClickListener listener)} or
     * {@link #setNegativeButton(int resID, int titleResID, View.OnClickListener listener)} before.
     * Note : This method will not hide the Title. You have to explicitly call {@link #hideTitle()} to do the same.
     */
    public void hideNegativeButton()
    {
        if(!isGone(negativeButton))
        {
            negativeButton.setVisibility(View.GONE);
        }
    }
    private String getProgressAsFraction()
    {
        return getProgress()+"/"+getMaxValue();
    }
    private String getProgressAsPercent()
    {
        double val=((double)getProgress()/(double)getMaxValue())*100;
        return String.format(Locale.getDefault(),"%.2f",val)+"%";
    }
    private void setProgressText()
    {
        switch(progressViewMode)
        {
            case SHOW_AS_FRACTION:
                progressTextView.setText(getProgressAsFraction());
                break;
            case SHOW_AS_PERCENT:
                progressTextView.setText(getProgressAsPercent());
                break;
            case HIDE_PROGRESS_TEXT:
                break;
        }
    }
    private boolean isVisible(View view)
    {
        return view.getVisibility()==View.VISIBLE;
    }
    private boolean isGone(View view)
    {
        return view.getVisibility()==View.GONE;
    }
    private boolean isDeterminate()
    {
        return mode==MODE_DETERMINATE;
    }
    @RequiresApi(api = Build.VERSION_CODES.R)
    private boolean isSystemInNightMode()
    {
        return context.getResources().getConfiguration().isNightModeActive();
    }
    private boolean isAboveOrEqualToAnd11()
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R;
    }
    private void enableNegativeButton(@Nullable CharSequence title)
    {
        negativeButton.setVisibility(View.VISIBLE);
        if(isGone(titleView))
            setTitle(title);
    }
    private boolean setThemeInternal(@ThemeConstant int themeConstant)
    {
        switch(themeConstant)
        {
            case THEME_DARK:
                dialogLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_dialog_dark));
                titleView.setTextColor(ContextCompat.getColor(context, R.color.white));
                textViewIndeterminate.setTextColor(ContextCompat.getColor(context, R.color.white));
                textViewDeterminate.setTextColor(ContextCompat.getColor(context, R.color.white));
                progressTextView.setTextColor(ContextCompat.getColor(context, R.color.white_dark));
                negativeButton.setTextColor(ContextCompat.getColor(context, R.color.white));
                theme = themeConstant;
                return true;
            case THEME_LIGHT:
                dialogLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_dialog));
                titleView.setTextColor(ContextCompat.getColor(context, R.color.black));
                textViewIndeterminate.setTextColor(ContextCompat.getColor(context, R.color.black));
                textViewDeterminate.setTextColor(ContextCompat.getColor(context, R.color.black));
                progressTextView.setTextColor(ContextCompat.getColor(context, R.color.black_light));
                negativeButton.setTextColor(ContextCompat.getColor(context, R.color.black));
                theme = themeConstant;
                return true;
            default:
                return false;
        }
    }
}


