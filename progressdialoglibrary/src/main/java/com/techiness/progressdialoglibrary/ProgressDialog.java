/*
 * An easy-to-use ProgressDialog Library provided by Techiness Overloaded for Android API 26 and above.
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.techiness.progressdialoglibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import java.util.Locale;

/**
 * A highly customisable ProgressDialog Class for Android API Level 24 and above.
 * Built upon Android's AlertDialog. So no Compatibility and Reliability issues.
 * It provides a neat Material Design UI for ProgressDialog and supports both Determinate and Indeterminate ProgressBars.
 * It also has support for Dark Theme.
 * It can be customized according to User's needs using the provided Methods.
 */
public class ProgressDialog
{
    /**
     * The default mode for ProgressDialog where an Indeterminate Spinner is shown for indicating Progress (even if it is not passed in Constructor).
     * Suitable for implementations where the exact progress of an operation is unknown to the Developer.
     */
    public static final int MODE_INDETERMINATE=0;
    /**
     * In this mode, a Determinate ProgressBar is shown inside the ProgressDialog for indicating Progress.
     * It also has a TextView for numerically showing the Progress Value either as Percentage or as Fraction.
     * Progress Value is shown as Percentage by Default which can be changed using {@link #showProgressTextAsFraction(boolean showProgressTextAsFraction)};
     */
    public static final int MODE_DETERMINATE=1;
    /**
     * The default Theme for ProgressDialog (even if it is not passed in Constructor).
     * Suitable for apps having a Light Theme.
     * Theme can be changed later using {@link #setTheme(int themeConstant)}.
     */
    public static final int THEME_LIGHT=2;
    /**
     * This theme is suitable for apps having a Dark Theme.
     * This Constant SHOULD be passed explicitly in the Constructor for setting Dark Theme for ProgressDialog.
     * Theme can be changed later using {@link #setTheme(int themeConstant)}.
     */
    public static final int THEME_DARK=3;
    private static final int SHOW_AS_FRACTION=4;
    private static final int SHOW_AS_PERCENT=5;
    private static final int HIDE_PROGRESS_TEXT=6;
    private final Context context;
    private TextView titleView,textViewIndeterminate,textViewDeterminate,progressTextView;
    private ProgressBar progressBarDeterminate,progressBarIndeterminate;
    private AlertDialog progressDialog;
    private ConstraintLayout dialogLayout;
    private int mode,theme,incrementAmt,progressViewMode;
    private boolean cancelable;
    /**
     * Simple Constructor accepting only the Activity Level Context as Argument.
     * Theme is set as Light Theme by Default (which can be changed later using {@link #setTheme(int themeConstant)}).
     * Mode is set as Indeterminate by Default (which can be changed later using {@link #setMode(int MODE)}).
     */
    public ProgressDialog(Context context)
    {
        this.context=context;
        initialiseDialog(THEME_LIGHT);
        setMode(MODE_INDETERMINATE);
    }
    /**
     * A Constructor accepting the Activity Level Context and Theme Constant as Arguments.
     * Theme is set as Light Theme if {@link #THEME_LIGHT} is passed (This can be changed later using {@link #setTheme(int themeConstant)}).
     * Theme is set as Dark Theme if {@link #THEME_DARK} is passed (This can be changed later using {@link #setTheme(int themeConstant)}).
     * Mode is set as Indeterminate by Default (which can be changed later using {@link #setMode(int MODE)}).
     */
    public ProgressDialog(Context context,int theme)
    {
        this.context = context;
        initialiseDialog(theme);
        setMode(MODE_INDETERMINATE);
    }
    /**
     * A Constructor accepting the Mode Constant, Activity Level Context and Theme Constant as Arguments.
     * Mode is set as Determinate if {@link #MODE_DETERMINATE} is passed (This can be changed later using {@link #setMode(int MODE)}).
     * Mode is set as Indeterminate if {@link #MODE_INDETERMINATE} is passed (This can be changed later using {@link #setMode(int MODE)}).
     * Theme is set as Light Theme if {@link #THEME_LIGHT} is passed (This can be changed later using {@link #setTheme(int themeConstant)}).
     * Theme is set as Dark Theme if {@link #THEME_DARK} is passed (This can be changed later using {@link #setTheme(int themeConstant)}).
     */
    public ProgressDialog(int mode,Context context,int theme)
    {
        this.context=context;
        initialiseDialog(theme);
        setMode(mode);
    }
    /**
     * A Constructor accepting the Mode Constant and Activity Level Context as Arguments.
     * Mode is set as Determinate if {@link #MODE_DETERMINATE} is passed (This can be changed later using {@link #setMode(int MODE)}).
     * Mode is set as Indeterminate if {@link #MODE_INDETERMINATE} is passed (This can be changed later using {@link #setMode(int MODE)}).
     * Theme is set as Light Theme by Default (which can be changed later using {@link #setTheme(int themeConstant)}).
     */
    public ProgressDialog(int mode,Context context)
    {
        this.context=context;
        initialiseDialog(THEME_LIGHT);
        setMode(mode);
    }
    private void initialiseDialog(int themeValue)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view=LayoutInflater.from(context).inflate(R.layout.layout_progressdialog,null);
        dialogLayout=view.findViewById(R.id.dialog_layout);
        titleView=view.findViewById(R.id.title_progressDialog);
        textViewDeterminate=view.findViewById(R.id.textView_determinate);
        textViewIndeterminate=view.findViewById(R.id.textView_indeterminate);
        progressBarIndeterminate=view.findViewById(R.id.progressbar_indeterminate);
        progressBarDeterminate=view.findViewById(R.id.progressbar_determinate);
        progressTextView=view.findViewById(R.id.ProgressTextView);
        setTheme(themeValue);
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
     * @param MODE The Mode Constant to be passed as Argument ({@link #MODE_DETERMINATE} or {@link #MODE_INDETERMINATE}).
     */
    public void setMode(int MODE)
    {
        if(mode==MODE)
            return;
        if(MODE==MODE_INDETERMINATE)
        {
            textViewDeterminate.setVisibility(View.GONE);
            progressBarDeterminate.setVisibility(View.GONE);
            progressTextView.setVisibility(View.GONE);
            textViewIndeterminate.setVisibility(View.VISIBLE);
            progressBarIndeterminate.setVisibility(View.VISIBLE);
        }
        if(MODE==MODE_DETERMINATE)
        {
            textViewIndeterminate.setVisibility(View.GONE);
            progressBarIndeterminate.setVisibility(View.GONE);
            textViewDeterminate.setVisibility(View.VISIBLE);
            progressBarDeterminate.setVisibility(View.VISIBLE);
            progressViewMode=SHOW_AS_PERCENT;
            progressTextView.setVisibility(View.VISIBLE);
            if(incrementAmt==0)
            {
                incrementAmt=1;
            }
        }
        mode=MODE;
    }
    /**
     * Returns the Current Mode of ProgressDialog.
     * @return The current Mode of ProgressDialog ({@link #MODE_DETERMINATE} or {@link #MODE_INDETERMINATE}).
     */
    public int getMode()
    {
        return mode;
    }

    /**
     * Sets/Changes the Theme of ProgressDialog which is {@link #THEME_LIGHT} by Default.
     * If you're going to use only one Theme constantly, this method is not needed. Instead, use an appropriate Constructor to set the required Theme during Instantiation.
     * @param themeConstant The Theme Constant to be passed ({@link #THEME_LIGHT} or {@link #THEME_DARK}).
     */
    public void setTheme(int themeConstant)
    {
        if(theme==themeConstant)
            return;
        if(themeConstant==THEME_LIGHT)
        {
            dialogLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_dialog));
            titleView.setTextColor(ContextCompat.getColor(context,R.color.black));
            textViewIndeterminate.setTextColor(ContextCompat.getColor(context,R.color.black));
            textViewDeterminate.setTextColor(ContextCompat.getColor(context,R.color.black));
            progressTextView.setTextColor(ContextCompat.getColor(context,R.color.black_light));
        }
        if(themeConstant==THEME_DARK)
        {
            dialogLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_dialog_dark));
            titleView.setTextColor(ContextCompat.getColor(context,R.color.white));
            textViewIndeterminate.setTextColor(ContextCompat.getColor(context,R.color.white));
            textViewDeterminate.setTextColor(ContextCompat.getColor(context,R.color.white));
            progressTextView.setTextColor(ContextCompat.getColor(context,R.color.white_dark));
        }
        theme=themeConstant;
    }

    /**
     * Returns the Current Theme of ProgressDialog.
     * @return The current Theme of ProgressDialog ({@link #THEME_LIGHT} or {@link #THEME_DARK}).
     */
    public int getTheme()
    {
        return theme;
    }
    /**
     * Sets the Text to be displayed alongside ProgressBar. It is Loading by Default.
     * @param message The Text to be displayed inside ProgressDialog.
     */
    public void setMessage(CharSequence message)
    {
        if(mode==MODE_INDETERMINATE)
        {
            textViewIndeterminate.setText(message);
        }
        else
        {
            textViewDeterminate.setText(message);
        }
    }

    /**
     * Sets the Title of ProgressDialog. It is ProgressDialog by Default.
     * Title is Hidden by Default. This Method makes the Title Visible even if "null" is passed as argument.
     * @param title The text to be set as the Title of ProgressDialog.
     */
    public void setTitle(CharSequence title)
    {
        titleView.setVisibility(View.VISIBLE);
        if(title!=null)
        {
            titleView.setText(title);
        }
    }

    /**
     * Hides the Title of ProgressDialog.
     * Title is Hidden by Default.
     * Use this method only if you have used {@link #setTitle(CharSequence title)} before.
     */
    public void hideTitle()
    {
        titleView.setVisibility(View.GONE);
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
        if(mode==MODE_DETERMINATE)
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
        if(mode==MODE_DETERMINATE)
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
        if(mode==MODE_DETERMINATE)
        {
            return incrementAmt;
        }
        else
        {
            return -1;
        }
    }
    /**
     * Increments the Progress Value of Determinate ProgressBar using the Offset Value set using {@link #setIncrementValue(int increment)}.
     * Can be used only in {@link #MODE_DETERMINATE} Mode.
     * If the progress is greater than MaxValue after incrementing, MaxValue will be set as Progress.
     * This method is preferred over {@link #setProgress(int progress)} for continuous and uniform Progress Increments.
     * @return true if Mode is {@link #MODE_DETERMINATE} and Progress is set. false otherwise.
     * @see #setIncrementValue(int increment)
     * @see #setProgress(int progress)
     */
    public boolean incrementProgress()
    {
        if(mode==MODE_DETERMINATE)
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
        if(mode==MODE_DETERMINATE)
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
        if(mode==MODE_DETERMINATE)
        {
            return progressBarDeterminate.getMax();
        }
        else
        {
            return -1;
        }
    }
    /**
     * Returns the Progress Value of Determinate ProgressBar.
     * Can be used only in {@link #MODE_DETERMINATE} Mode.
     * @return The Current Progress Value of Determinate ProgressBar if Mode is {@link #MODE_DETERMINATE}. Else -1 is returned.
     */
    public int getProgress()
    {
        if(mode==MODE_DETERMINATE)
        {
            return progressBarDeterminate.getProgress();
        }
        else
        {
            return -1;
        }
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
        if(mode==MODE_DETERMINATE)
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
     * @return true if Mode is {@link #MODE_DETERMINATE} and Progress is set. false otherwise.
     */
    public boolean hideProgressText()
    {
        if(mode==MODE_DETERMINATE)
        {
            progressViewMode=HIDE_PROGRESS_TEXT;
            if(progressTextView.getVisibility()==View.VISIBLE)
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
    public boolean setOnCancelListener(DialogInterface.OnCancelListener onCancelListener)
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
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener)
    {
        progressDialog.setOnDismissListener(onDismissListener);
    }
    /**
     * Sets the {@link DialogInterface.OnShowListener} for ProgressDialog.
     * @param onShowListener {@link DialogInterface.OnShowListener} listener object.
     */
    public void setOnShowListener(DialogInterface.OnShowListener onShowListener)
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
        if(mode==MODE_DETERMINATE)
        {
            return getProgress() == getMaxValue();
        }
        else
        {
            return false;
        }
    }

    /**
     * Gets the Integral Value required to reach MaxValue from the current ProgressValue.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * @return The Integral Amount required to reach MaxValue. -1 if mode is not {@link #MODE_DETERMINATE}.
     */
    public int getRemainingProgress()
    {
        if(mode==MODE_DETERMINATE)
        {
            return getMaxValue()-getProgress();
        }
        else
        {
            return -1;
        }
    }

    /**
     * Sets the Secondary ProgressValue.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * @param secondaryProgress The integral value to be set as Secondary ProgressValue.
     * @return true if mode is {@link #MODE_DETERMINATE} and Secondary ProgressValue is set. false otherwise.
     */
    public boolean setSecondaryProgress(int secondaryProgress)
    {
        if(mode==MODE_DETERMINATE)
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
        if(mode==MODE_DETERMINATE)
        {
            return progressBarDeterminate.getSecondaryProgress();
        }
        else
        {
            return -1;
        }
    }
    /**
     * Gets the Integral Value required to reach MaxValue from the current Secondary ProgressValue.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * @return The Integral Amount required to reach MaxValue from Secondary ProgressValue. -1 if mode is not {@link #MODE_DETERMINATE}.
     */
    public int getSecondaryRemainingProgress()
    {
        if(mode==MODE_DETERMINATE)
        {
            return getMaxValue()-getSecondaryProgress();
        }
        else
        {
            return -1;
        }
    }
    /**
     * Checks if Secondary ProgressValue is equal to MaxValue.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * @return true if Secondary ProgressValue is equal to MaxValue. false otherwise and also if mode is not {@link #MODE_DETERMINATE}.
     */
    public boolean getHasSecondaryProgressReachedMaxValue()
    {
        if(mode==MODE_DETERMINATE)
        {
            return getSecondaryProgress()==getMaxValue();
        }
        else
        {
            return false;
        }
    }
    /**
     * Sets a Custom Drawable to the Indeterminate ProgressBar.
     * Can be used only in {@link #MODE_INDETERMINATE}.
     * Use this when you need to define a custom Drawable Design for Indeterminate ProgressBar.
     * @param progressDrawable The Drawable object used to draw the Indeterminate ProgressBar.
     * @return true if mode is {@link #MODE_INDETERMINATE} and the Drawable is set. false otherwise.
     * @see #getIndeterminateDrawable()
     * @see #setProgressTintList(ColorStateList tintList)
     */
    public boolean setIndeterminateDrawable(Drawable progressDrawable)
    {
        if(mode==MODE_INDETERMINATE)
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
     * Gets the Drawable object used to draw the Indeterminate ProgressBar.
     * Can be used only in {@link #MODE_INDETERMINATE}.
     * @return Drawable Object if mode is {@link #MODE_INDETERMINATE}. null otherwise.
     * @see #setIndeterminateDrawable(Drawable progressDrawable)
     * @see #getProgressTintList()
     */
    @Nullable
    public Drawable getIndeterminateDrawable()
    {
        if(mode==MODE_INDETERMINATE)
        {
            return progressBarIndeterminate.getIndeterminateDrawable();
        }
        else
        {
            return null;
        }
    }
    /**
     * Sets a Custom Drawable to the Determinate ProgressBar.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * Use this when you need to define a custom Drawable Design for Determinate ProgressBar.
     * @param progressDrawable The Drawable object used to draw the Determinate ProgressBar.
     * @return true if mode is {@link #MODE_DETERMINATE} and the Drawable is set. false otherwise.
     * @see #getDeterminateDrawable()
     * @see #setProgressTintList(ColorStateList tintList)
     */
    public boolean setDeterminateDrawable(Drawable progressDrawable)
    {
        if(mode==MODE_DETERMINATE)
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
     * Gets the Drawable object used to draw the Determinate ProgressBar.
     * Can be used only in {@link #MODE_DETERMINATE}.
     * @return Drawable Object if mode is {@link #MODE_DETERMINATE}. null otherwise.
     * @see #setDeterminateDrawable(Drawable progressDrawable)
     * @see #getProgressTintList()
     */
    @Nullable
    public Drawable getDeterminateDrawable()
    {
        if(mode==MODE_DETERMINATE)
        {
            return progressBarDeterminate.getProgressDrawable();
        }
        else
        {
            return null;
        }
    }

    /**
     * Applies a tint to Indeterminate Drawable if mode is {@link #MODE_INDETERMINATE}.
     * Applies a tint to Determinate Drawable if mode is {@link #MODE_DETERMINATE}.
     * @param tintList The ColorStateList object used to apply tint to ProgressBar's Drawable.
     * @see #getProgressTintList()
     */
    public void setProgressTintList(ColorStateList tintList)
    {
        switch(mode)
        {
            case MODE_INDETERMINATE:
                progressBarIndeterminate.setIndeterminateTintList(tintList);
                break;
            case MODE_DETERMINATE:
                progressBarDeterminate.setProgressTintList(tintList);
                break;
        }
    }

    /**
     * Returns the tint applied to Indeterminate Drawable if mode is {@link #MODE_INDETERMINATE}.
     * Returns the tint applied to Determinate Drawable if mode is {@link #MODE_DETERMINATE}.
     * @return ColorStateList object specifying the tint applied to ProgressBar's Drawable.
     * @see #setProgressTintList(ColorStateList tintList)
     */
    public ColorStateList getProgressTintList()
    {
        if(mode==MODE_INDETERMINATE)
        {
            return progressBarIndeterminate.getIndeterminateTintList();
        }
        else
            {
            return progressBarDeterminate.getProgressTintList();
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
}


