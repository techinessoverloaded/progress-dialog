package com.techiness.progressdialogexample;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import com.techiness.progressdialoglibrary.ProgressDialog;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTestForLibrary
{
    private final Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private ProgressDialog progressDialog;

    @Test
    public void checkIfModeAndThemeAreSet1()
    {
        progressDialog = new ProgressDialog(appContext);
        assertEquals(progressDialog.getMode(), ProgressDialog.MODE_INDETERMINATE);
        assertEquals(progressDialog.getTheme(), ProgressDialog.THEME_LIGHT);
    }
}