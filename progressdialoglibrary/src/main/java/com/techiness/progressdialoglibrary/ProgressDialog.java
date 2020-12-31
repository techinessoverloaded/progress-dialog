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
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
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
     * Theme cannot be changed after Instantiation of ProgressDialog Object.
     */
    public static final int THEME_LIGHT=2;
    /**
     * This theme is suitable for apps having a Dark Theme.
     * This Constant SHOULD be passed explicitly in the Constructor for setting Dark Theme for ProgressDialog.
     * Theme cannot be changed after Instantiation of ProgressDialog Object.
     */
    public static final int THEME_DARK=3;
    private static final int SHOW_AS_FRACTION=4;
    private static final int SHOW_AS_PERCENT=5;
    private static final int HIDE_PROGRESS_TEXT=6;
    private final Context context;
    private TextView titleView,textViewIndeterminate,textViewDeterminate,progressTextView;
    private ProgressBar progressBarDeterminate,progressBarIndeterminate;
    private AlertDialog progressDialog;
    private int mode,theme,incrementAmt,progressViewMode;
    private boolean cancelable;
    /**
     * Simple Constructor accepting only the Activity Level Context as Argument.
     * Theme is set as Light Theme by Default (which cannot be changed later).
     * Mode is set as Indeterminate by Default (which can be changed later using {@link #setMode(int MODE)}).
     */
    public ProgressDialog(Context context)
    {
        this.context=context;
        this.theme=THEME_LIGHT;
        initialiseDialog();
        setMode(MODE_INDETERMINATE);
    }
    /**
     * A Constructor accepting the Activity Level Context and Theme Constant as Arguments.
     * Theme is set as Light Theme if {@link #THEME_LIGHT} is passed (This cannot be changed later).
     * Theme is set as Dark Theme if {@link #THEME_DARK} is passed (This cannot be changed later).
     * Mode is set as Indeterminate by Default (which can be changed later using {@link #setMode(int MODE)}).
     */
    public ProgressDialog(Context context,int theme)
    {
        this.context = context;
        this.theme = theme;
        initialiseDialog();
        setMode(MODE_INDETERMINATE);
    }
    /**
     * A Constructor accepting the Mode Constant, Activity Level Context and Theme Constant as Arguments.
     * Mode is set as Determinate if {@link #MODE_DETERMINATE} is passed (This can be changed later using {@link #setMode(int MODE)}).
     * Mode is set as Indeterminate if {@link #MODE_INDETERMINATE} is passed (This can be changed later using {@link #setMode(int MODE)}).
     * Theme is set as Light Theme if {@link #THEME_LIGHT} is passed (This cannot be changed later).
     * Theme is set as Dark Theme if {@link #THEME_DARK} is passed (This cannot be changed later).
     */
    public ProgressDialog(int mode,Context context,int theme)
    {
        this.context=context;
        this.theme=theme;
        initialiseDialog();
        setMode(mode);
    }
    /**
     * A Constructor accepting the Mode Constant and Activity Level Context as Arguments.
     * Mode is set as Determinate if {@link #MODE_DETERMINATE} is passed (This can be changed later using {@link #setMode(int MODE)}).
     * Mode is set as Indeterminate if {@link #MODE_INDETERMINATE} is passed (This can be changed later using {@link #setMode(int MODE)}).
     * Theme is set as Light Theme by Default (which cannot be changed later).
     */
    public ProgressDialog(int mode,Context context)
    {
        this.context=context;
        this.theme=THEME_LIGHT;
        initialiseDialog();
        setMode(mode);
    }
    private boolean initialiseDialog() throws NullPointerException
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view;
        switch(theme)
        {
            case THEME_LIGHT:
                view=LayoutInflater.from(context).inflate(R.layout.layout_progressdialog,null);
                titleView=view.findViewById(R.id.title_progressDialog);
                textViewDeterminate=view.findViewById(R.id.textView_determinate);
                textViewIndeterminate=view.findViewById(R.id.textView_indeterminate);
                progressBarIndeterminate=view.findViewById(R.id.progressbar_indeterminate);
                progressBarDeterminate=view.findViewById(R.id.progressbar_determinate);
                progressTextView=view.findViewById(R.id.ProgressTextView);
                builder.setView(view);
                break;
            case THEME_DARK:
                view=LayoutInflater.from(context).inflate(R.layout.layout_progressdialog_dark,null);
                titleView=view.findViewById(R.id.title_progressDialog_dark);
                textViewDeterminate=view.findViewById(R.id.textView_determinate_dark);
                textViewIndeterminate=view.findViewById(R.id.textView_indeterminate_dark);
                progressBarIndeterminate=view.findViewById(R.id.progressbar_indeterminate_dark);
                progressBarDeterminate=view.findViewById(R.id.progressbar_determinate_dark);
                progressTextView=view.findViewById(R.id.ProgressTextViewDark);
                builder.setView(view);
                break;
            default:
                view=null;
                break;
        }
        if(view!=null)
        {
            progressDialog = builder.create();
            if (progressDialog.getWindow() != null) {
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            cancelable=false;
            setCancelable(false);
            return true;
        }
        else
        {
            throw new NullPointerException("Couldn't initialise ProgressDialog ! Make sure to call the Constructor properly !");
        }
    }
    /**
     * Sets/Changes the mode of ProgressDialog.
     * @param modeCode The Mode Constant to be passed as Argument ({@link #MODE_DETERMINATE} or {@link #MODE_INDETERMINATE}).
     */
    public void setMode(int modeCode)
    {
        mode=modeCode;
        if(mode==MODE_INDETERMINATE)
        {
            textViewDeterminate.setVisibility(View.GONE);
            progressBarDeterminate.setVisibility(View.GONE);
            progressTextView.setVisibility(View.GONE);
            textViewIndeterminate.setVisibility(View.VISIBLE);
            progressBarIndeterminate.setVisibility(View.VISIBLE);
        }
        if(mode==MODE_DETERMINATE)
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
     * @param progress The Integral Progress Value to be set in Determinate ProgressBar.
     * @return true if Mode is {@link #MODE_DETERMINATE} and Progress is set. false otherwise.
     */
    public boolean setProgress(int progress)
    {
        if(mode==MODE_DETERMINATE)
        {
            progressBarDeterminate.setProgress(progress,true);
            switch(progressViewMode)
            {
                case SHOW_AS_FRACTION:
                    String temp = progress+"//"+getMaxValue();
                    progressTextView.setText(temp);
                    break;
                case SHOW_AS_PERCENT:
                    int val=(progress/getMaxValue())*100;
                    String txt = val+"%";
                    progressTextView.setText(txt);
                    break;
                case HIDE_PROGRESS_TEXT:
                    break;
            }
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
     * This method is preferred over {@link #setProgress(int progress)} for using Determinate ProgressDialog inside a Handler.
     * @return true if Mode is {@link #MODE_DETERMINATE} and Progress is set. false otherwise.
     */
    public boolean incrementProgress()
    {
        if(mode==MODE_DETERMINATE)
        {
            setProgress(progressBarDeterminate.getProgress()+incrementAmt);
            switch(progressViewMode)
            {
                case SHOW_AS_FRACTION:
                    String temp = getProgress()+"//"+getMaxValue();
                    progressTextView.setText(temp);
                    break;
                case SHOW_AS_PERCENT:
                    int val=(getProgress()/getMaxValue())*100;
                    String txt = val+"%";
                    progressTextView.setText(txt);
                    break;
                case HIDE_PROGRESS_TEXT:
                    break;
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Sets the Maximum value of Determinate ProgressBar
     * Can be used only in {@link #MODE_DETERMINATE} Mode.
     * @param maxValue The Integral Value to be set as MaxValue for Determinate ProgressBar.
     * @return true if Mode is {@link #MODE_DETERMINATE} and Progress is set. false otherwise.
     */
    public boolean setMaxValue(int maxValue)
    {
        if(mode==MODE_DETERMINATE)
        {
            progressBarDeterminate.setMax(maxValue);
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
     * Can be used only in {@link #MODE_DETERMINATE}
     * @param progressTextAsFraction The boolean value to change Progress TextView's format.
     * @return true if Mode is {@link #MODE_DETERMINATE} and Progress is set. false otherwise.
     */
    public boolean showProgressTextAsFraction(boolean progressTextAsFraction)
    {
        if(mode==MODE_DETERMINATE)
        {
            if(progressTextAsFraction)
            {
                progressViewMode=SHOW_AS_FRACTION;
            }
            else
            {
                progressViewMode=SHOW_AS_PERCENT;
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
     * Can be used only in {@link #MODE_DETERMINATE}
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
     * Starts the ProgressDialog and shows it on Screen if proper Instantiation was done.
     * Else NullPointerException is thrown.
     */
    public void show() throws NullPointerException
    {
        if(progressDialog==null)
        {
            throw new NullPointerException("ProgressDialog not initiated properly ! Make sure to give proper parameters while calling Constructor !");
        }
        else
            {
            progressDialog.show();
        }
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
}


