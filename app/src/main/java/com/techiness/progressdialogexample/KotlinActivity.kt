package com.techiness.progressdialogexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techiness.progressdialoglibrary.ProgressDialog

class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        val progressDialog = ProgressDialog(this)
        progressDialog.show()
    }
}