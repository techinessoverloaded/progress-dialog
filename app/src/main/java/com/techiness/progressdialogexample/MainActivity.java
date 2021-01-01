package com.techiness.progressdialogexample;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.techiness.progressdialoglibrary.ProgressDialog;

public class MainActivity extends AppCompatActivity
{
    ProgressDialog progressDialogLight,progressDialogDark;
    Button showLightDeterBut,showLightInDeterBut,showLightDeterTitleBut,showLightInDeterTitleBut,showLightDeterwithoutProgressBut;
    Button showDarkDeterBut,showDarkInDeterBut,showDarkDeterTitleBut,showDarkInDeterTitleBut,showDarkDeterwithoutProgressBut;
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
        bindViews();
        setOnClickListeners();
    }
    private void bindViews()
    {
        showLightDeterBut=findViewById(R.id.showLightDeterBut);
        showLightInDeterBut=findViewById(R.id.showLightInDeterBut);
        showLightDeterTitleBut=findViewById(R.id.showLightDeterTitleBut);
        showLightInDeterTitleBut=findViewById(R.id.showLightInDeterTitleBut);
        showLightDeterwithoutProgressBut=findViewById(R.id.showLightDeterwithoutProgressBut);
        showDarkDeterBut=findViewById(R.id.showDarkDeterBut);
        showDarkInDeterBut=findViewById(R.id.showDarkInDeterBut);
        showDarkDeterTitleBut=findViewById(R.id.showDarkDeterTitleBut);
        showDarkInDeterTitleBut=findViewById(R.id.showDarkInDeterTitleBut);
        showDarkDeterwithoutProgressBut=findViewById(R.id.showDarkDeterwithoutProgressBut);
    }
    private void setOnClickListeners()
    {
        showLightDeterBut.setOnClickListener(v->onClick(1));
        showLightInDeterBut.setOnClickListener(v->onClick(2));
        showLightDeterTitleBut.setOnClickListener(v->onClick(3));
        showLightInDeterTitleBut.setOnClickListener(v->onClick(4));
        showLightDeterwithoutProgressBut.setOnClickListener(v->onClick(5));
        showDarkDeterBut.setOnClickListener(v->onClick(6));
        showDarkInDeterBut.setOnClickListener(v->onClick(7));
        showDarkDeterTitleBut.setOnClickListener(v->onClick(8));
        showDarkInDeterTitleBut.setOnClickListener(v->onClick(9));
        showDarkDeterwithoutProgressBut.setOnClickListener(v->onClick(10));
    }
    private void onClick(int requestCode)
    {
        switch(requestCode)
        {
            case 1:
                progressDialogLight.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialogLight.setProgress(65);
                progressDialogLight.show();
                break;
            case 2:
                progressDialogLight.setMode(ProgressDialog.MODE_INDETERMINATE);
                progressDialogLight.show();
                break;
            case 3:
                progressDialogLight.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialogLight.setTitle("Determinate");
                progressDialogLight.showProgressTextAsFraction(true);
                progressDialogLight.setProgress(65);
                progressDialogLight.show();
                break;
            case 4:
                progressDialogLight.setMode(ProgressDialog.MODE_INDETERMINATE);
                progressDialogLight.setTitle("Indeterminate");
                progressDialogLight.show();
                break;
            case 5:
                progressDialogLight.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialogLight.hideProgressText();
                progressDialogLight.setProgress(65);
                progressDialogLight.show();
                break;
            case 6:
                progressDialogDark.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialogDark.setProgress(65);
                progressDialogDark.show();
                break;
            case 7:
                progressDialogDark.setMode(ProgressDialog.MODE_INDETERMINATE);
                progressDialogDark.show();
                break;
            case 8:
                progressDialogDark.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialogDark.setTitle("Determinate");
                progressDialogLight.showProgressTextAsFraction(true);
                progressDialogDark.setProgress(65);
                progressDialogDark.show();
                break;
            case 9:
                progressDialogDark.setMode(ProgressDialog.MODE_INDETERMINATE);
                progressDialogDark.setTitle("Indeterminate");
                progressDialogDark.show();
                break;
            case 10:
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