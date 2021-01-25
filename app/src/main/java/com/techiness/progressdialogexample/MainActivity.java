package com.techiness.progressdialogexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.techiness.progressdialoglibrary.ProgressDialog;

public class MainActivity extends AppCompatActivity
{
    ProgressDialog progressDialog,progressDialog1,progressDialog2;
    Button showDeterBut,showInDeterBut,showDeterTitleBut,showInDeterTitleBut,showDeterWithoutProgressBut,showDeterWithNegativeButton,showInDeterWithNegativeButton;
    SwitchCompat darkSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        progressDialog1=new ProgressDialog(ProgressDialog.MODE_DETERMINATE,this);
        progressDialog2=new ProgressDialog(ProgressDialog.MODE_INDETERMINATE,this);
        progressDialog.setCancelable(true);
        progressDialog1.setCancelable(true);
        progressDialog2.setCancelable(true);
        bindViews();
        setOnClickListeners();
    }
    private void bindViews()
    {
        darkSwitch=findViewById(R.id.darkSwitch);
        showDeterBut=findViewById(R.id.showDeterBut);
        showInDeterBut=findViewById(R.id.showInDeterBut);
        showDeterTitleBut=findViewById(R.id.showDeterTitleBut);
        showInDeterTitleBut=findViewById(R.id.showInDeterTitleBut);
        showDeterWithoutProgressBut=findViewById(R.id.showDeterWithoutProgressBut);
        showDeterWithNegativeButton=findViewById(R.id.showDeterWithNegativeButton);
        showInDeterWithNegativeButton=findViewById(R.id.showInDeterWithNegativeButton);
    }
    private void setOnClickListeners()
    {
        darkSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked&&progressDialog.getTheme()!=ProgressDialog.THEME_DARK)
            {
                progressDialog.setTheme(ProgressDialog.THEME_DARK);
                progressDialog1.setTheme(ProgressDialog.THEME_DARK);
                progressDialog2.setTheme(ProgressDialog.THEME_DARK);
            }
            else
            {
                if(progressDialog.getTheme()!=ProgressDialog.THEME_LIGHT)
                {
                    progressDialog.setTheme(ProgressDialog.THEME_LIGHT);
                    progressDialog1.setTheme(ProgressDialog.THEME_LIGHT);
                    progressDialog2.setTheme(ProgressDialog.THEME_LIGHT);
                }
            }
        });
        showDeterBut.setOnClickListener(v->onClick(1));
        showInDeterBut.setOnClickListener(v->onClick(2));
        showDeterTitleBut.setOnClickListener(v->onClick(3));
        showInDeterTitleBut.setOnClickListener(v->onClick(4));
        showDeterWithoutProgressBut.setOnClickListener(v->onClick(5));
        showDeterWithNegativeButton.setOnClickListener(v->onClick(6));
        showInDeterWithNegativeButton.setOnClickListener(v->onClick(7));
    }
    private void onClick(int requestCode)
    {
        switch(requestCode)
        {
            case 1:
                progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialog.hideTitle();
                progressDialog.setProgress(65);
                progressDialog.setSecondaryProgress(0);
                progressDialog.setNegativeButton(null,"Determinate");
                progressDialog.show();
                break;
            case 2:
                progressDialog.setMode(ProgressDialog.MODE_INDETERMINATE);
                progressDialog.hideTitle();
                progressDialog.show();
                progressDialog.hideNegativeButton();
                break;
            case 3:
                progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialog.setTitle("Determinate");
                progressDialog.showProgressTextAsFraction(true);
                progressDialog.setProgress(65);
                progressDialog.setSecondaryProgress(80);
                progressDialog.show();
                break;
            case 4:
                progressDialog.setMode(ProgressDialog.MODE_INDETERMINATE);
                progressDialog.setTitle("Indeterminate");
                progressDialog.hideNegativeButton();
                progressDialog.show();
                break;
            case 5:
                progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialog.hideProgressText();
                progressDialog.setProgress(65);
                progressDialog.hideTitle();
                progressDialog.show();
                break;
            case 6:
                progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
                progressDialog.setNegativeButton("Determinate Dismiss","Determinate",v -> {
                    Toast.makeText(MainActivity.this,"Custom OnClickListener for Determinate",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                });
                progressDialog.show();
                break;
            case 7:
                progressDialog.setMode(ProgressDialog.MODE_INDETERMINATE);
                progressDialog.setNegativeButton("Indeterminate Dismiss","Indeterminate",v -> {
                    Toast.makeText(MainActivity.this,"Custom OnClickListener for Indeterminate",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                });
                progressDialog.show();
                break;
            default:
                break;
        }
    }
}