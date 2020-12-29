package com.techiness.progressdialoglibrary;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
public class ProgressDialog
{
    public static final int MODE_INDETERMINATE=0;
    public static final int MODE_DETERMINATE=1;
    private final Context context;
    private TextView titleView,textViewIndeterminate,textViewDeterminate;
    private ProgressBar progressBarDeterminate,progressBarIndeterminate;
    private AlertDialog progressDialog;
    private int mode;
    private int incrementAmt,decrementAmt;
    public ProgressDialog(Context context)
    {
        this.context = context;
        initialiseDialog();
    }
    private void initialiseDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_progressdialog,null);
        titleView=view.findViewById(R.id.title_progressDialog);
        textViewDeterminate=view.findViewById(R.id.textView_determinate);
        textViewIndeterminate=view.findViewById(R.id.textView_indeterminate);
        progressBarIndeterminate=view.findViewById(R.id.progressbar_indeterminate);
        progressBarDeterminate=view.findViewById(R.id.progressbar_determinate);
        builder.setView(view);
        progressDialog=builder.create();
        if(progressDialog.getWindow()!= null)
        {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        setMode(MODE_INDETERMINATE);
        setCancelable(false);
    }
    public void setMode(int m)
    {
        mode=m;
        if(mode==MODE_INDETERMINATE)
        {
            textViewDeterminate.setVisibility(View.GONE);
            progressBarDeterminate.setVisibility(View.GONE);
            textViewIndeterminate.setVisibility(View.VISIBLE);
            progressBarIndeterminate.setVisibility(View.VISIBLE);
        }
        if(mode==MODE_DETERMINATE)
        {
            textViewIndeterminate.setVisibility(View.GONE);
            progressBarIndeterminate.setVisibility(View.GONE);
            textViewDeterminate.setVisibility(View.VISIBLE);
            progressBarDeterminate.setVisibility(View.VISIBLE);
        }
    }
    public int getMode()
    {
        return mode;
    }
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
    public void setTitle(CharSequence title)
    {
        titleView.setVisibility(View.VISIBLE);
        titleView.setText(title);
    }
    public void hideTitle()
    {
        titleView.setVisibility(View.GONE);
    }
    public boolean setProgress(int progress)
    {
        if(mode==MODE_DETERMINATE)
        {
            progressBarDeterminate.setProgress(progress,true);
            return true;
        }
        else
            {
            return false;
        }
    }
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
    public boolean setDecrementValue(int decrement)
    {
        if(mode==MODE_DETERMINATE)
        {
            decrementAmt=decrement;
            return true;
        }
        else
        {
            return false;
        }
    }
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
    public int getDecrementValue()
    {
        if(mode==MODE_DETERMINATE)
        {
            return decrementAmt;
        }
        else
        {
            return 0;
        }
    }
    public boolean incrementProgress()
    {
        if(mode==MODE_DETERMINATE)
        {
            setProgress(progressBarDeterminate.getProgress()+incrementAmt);
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean decrementProgress()
    {
        if(mode==MODE_DETERMINATE)
        {
            setProgress(progressBarDeterminate.getProgress()-decrementAmt);
            return true;
        }
        else
        {
            return false;
        }
    }
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
    public void show()
    {
        progressDialog.show();
    }
    public void dismiss()
    {
        progressDialog.dismiss();
    }
    public void cancel()
    {
        progressDialog.cancel();
    }
    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener)
    {
        progressDialog.setOnCancelListener(onCancelListener);
    }
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener)
    {
        progressDialog.setOnDismissListener(onDismissListener);
    }
    public void setOnShowListener(DialogInterface.OnShowListener onShowListener)
    {
        progressDialog.setOnShowListener(onShowListener);
    }
    public void setCancelable(boolean cancelable)
    {
        progressDialog.setCancelable(cancelable);
    }
}


