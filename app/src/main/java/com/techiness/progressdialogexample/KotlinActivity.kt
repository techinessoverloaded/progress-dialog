package com.techiness.progressdialogexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RestrictTo
import com.techiness.progressdialoglibrary.ProgressDialog

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        ProgressDialog(this).also { progressDialog ->
            progressDialog.setOnShowListener {
                Toast.makeText(this@KotlinActivity,  "From Kotlin", Toast.LENGTH_LONG).show()
            }
            progressDialog.show()
        }
    }
}