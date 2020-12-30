package com.techiness.progressdialogexample;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.techiness.progressdialoglibrary.ProgressDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    ProgressDialog progressDialogLight,progressDialogDark;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialogLight=new ProgressDialog(this);
        //Theme is Light by default. You can also use new ProgressDialog(this,ProgressDialog.THEME_LIGHT);
        progressDialogDark=new ProgressDialog(this,ProgressDialog.THEME_DARK);
        progressDialogLight.setCancelable(true);
        progressDialogDark.setCancelable(true);
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.showLightDeterBut:
                progressDialogLight.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialogLight.setProgress(65);
                progressDialogLight.show();
                break;
            case R.id.showLightInDeterBut:
                progressDialogLight.setMode(ProgressDialog.MODE_INDETERMINATE);
                progressDialogLight.show();
                break;
            case R.id.showLightDeterTitleBut:
                progressDialogLight.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialogLight.setTitle("Determinate");
                progressDialogLight.setProgress(65);
                progressDialogLight.show();
                break;
            case R.id.showLightInDeterTitleBut:
                progressDialogLight.setMode(ProgressDialog.MODE_INDETERMINATE);
                progressDialogLight.setTitle("Determinate");
                progressDialogLight.show();
                break;
            case R.id.showLightDeterwithoutProgressBut:
                progressDialogLight.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialogLight.hideProgressText();
                progressDialogLight.setProgress(65);
                progressDialogLight.show();
                break;
            case R.id.showDarkDeterBut:
                progressDialogDark.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialogDark.setProgress(65);
                progressDialogDark.show();
                break;
            case R.id.showDarkInDeterBut:
                progressDialogDark.setMode(ProgressDialog.MODE_INDETERMINATE);
                progressDialogDark.show();
                break;
            case R.id.showDarkDeterTitleBut:
                progressDialogDark.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialogDark.setTitle("Determinate");
                progressDialogDark.setProgress(65);
                progressDialogDark.show();
                break;
            case R.id.showDarkInDeterTitleBut:
                progressDialogDark.setMode(ProgressDialog.MODE_INDETERMINATE);
                progressDialogDark.setTitle("Determinate");
                progressDialogDark.show();
                break;
            case R.id.showDarkDeterwithoutProgressBut:
                progressDialogDark.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialogDark.hideProgressText();
                progressDialogDark.setProgress(65);
                progressDialogDark.show();
                break;
            default:
                break;
        }
    }
}