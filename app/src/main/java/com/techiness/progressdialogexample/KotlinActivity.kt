package com.techiness.progressdialogexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RestrictTo
import com.techiness.progressdialoglibrary.ProgressDialog

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        ProgressDialog(this).also { progressDialog ->
            progressDialog.mode = ProgressDialog.MODE_DETERMINATE
            progressDialog.show()
        }
    }
}