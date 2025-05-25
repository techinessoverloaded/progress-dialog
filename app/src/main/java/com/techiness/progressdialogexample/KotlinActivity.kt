package com.techiness.progressdialogexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RestrictTo
import com.techiness.progressdialoglibrary.builders.buildDeterminateProgressDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        CoroutineScope(Dispatchers.Main).launch {
            buildDeterminateProgressDialog(this@KotlinActivity) {
                maxValue = 100
            }.let {
                withContext(Dispatchers.IO) {
                  it.showDialogUntil(
                      flow {
                          for (i in 0..<100) {
                              emit(i)
                              kotlinx.coroutines.delay(1000) // Simulate some work
                          }
                      }
                  )
                }
            }
        }
    }
}