package com.techiness.progressdialogexample;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.techiness.progressdialoglibrary.ProgressDialog;

public class MainActivity extends AppCompatActivity
{
    Button showProgressDialog;
    ProgressDialog progressDialogLight,progressDialogDark;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialogLight=new ProgressDialog(this);
        //Theme is Light by default. You can also use new ProgressDialog(this,ProgressDialog.THEME_LIGHT);
        progressDialogDark=new ProgressDialog(this,ProgressDialog.THEME_DARK);
        showProgressDialog=findViewById(R.id.showProgressBut);
        showProgressDialog.setOnClickListener(v->{

        });
    }
}