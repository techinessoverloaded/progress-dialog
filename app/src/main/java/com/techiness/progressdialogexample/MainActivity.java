package com.techiness.progressdialogexample;

import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.Toast;
import com.techiness.progressdialoglibrary.ProgressDialog;

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class MainActivity extends AppCompatActivity
{
    ProgressDialog progressDialog;
    Button showDeterBut,showInDeterBut,showDeterTitleBut,showInDeterTitleBut,showDeterWithoutProgressBut,showDeterWithNegativeButton,showInDeterWithNegativeButton;
    SwitchCompat darkSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            progressDialog = new ProgressDialog(ProgressDialog.MODE_DETERMINATE,this,ProgressDialog.THEME_FOLLOW_SYSTEM);
            darkSwitch.setEnabled(false);
        }
        else
        {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
        }
        showDeterBut.setText(String.valueOf(progressDialog.getTheme()));
        progressDialog.setCancelable(true);
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
            }
            else
            {
                if(progressDialog.getTheme()!=ProgressDialog.THEME_LIGHT)
                {
                    progressDialog.setTheme(ProgressDialog.THEME_LIGHT);
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
                progressDialog.hideNegativeButton();
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
                progressDialog.hideNegativeButton();
                progressDialog.setProgress(65);
                progressDialog.setSecondaryProgress(80);
                progressDialog.setCancelable(false);
                progressDialog.setOnShowListener(true, new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface dialog)
                            {
                                Toast.makeText(MainActivity.this,progressDialog.isCancelable()? "true": "false",Toast.LENGTH_LONG).show();
                            }
                        });
                        progressDialog.show();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        progressDialog.setProgress(100);
                        progressDialog.dismiss();
                    }
                }, 50000);
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
                progressDialog.setProgress(54);
                progressDialog.setSecondaryProgress(0);
                progressDialog.showProgressTextAsFraction(true);
                progressDialog.setNegativeButton("Cancel","Determinate",null);
                progressDialog.setOnShowListener(true, new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog)
                    {
                        Toast.makeText(MainActivity.this,"On Show",Toast.LENGTH_LONG).show();
                    }
                });
                progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(MainActivity.this, "On Dismiss",Toast.LENGTH_LONG).show();
                    }
                });
                progressDialog.show();
                break;
            case 7:
                progressDialog.setMode(ProgressDialog.MODE_INDETERMINATE);
                progressDialog.setNegativeButton("Go to next activity","Indeterminate",v -> {
                    Toast.makeText(MainActivity.this,"Custom OnClickListener for Indeterminate",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(MainActivity.this,KotlinActivity.class);
                    startActivity(intent);
                });
                progressDialog.show();
                break;
            default:
                break;
        }
    }
}