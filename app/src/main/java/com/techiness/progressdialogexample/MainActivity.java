package com.techiness.progressdialogexample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
public class MainActivity extends AppCompatActivity
{
    Button showProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showProgressDialog=findViewById(R.id.showProgressBut);
        showProgressDialog.setOnClickListener(v-> {

        });
    }
}