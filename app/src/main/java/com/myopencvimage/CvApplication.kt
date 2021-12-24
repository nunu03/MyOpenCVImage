package com.myopencvimage

import android.app.Application
import android.util.Log
import android.widget.Toast
import org.opencv.android.OpenCVLoader

class CvApplication : Application() {
    private val CV_TAG = "CvApplication"
    override fun onCreate() {
        super.onCreate()
        val success = OpenCVLoader.initDebug();
        if (success) {
            Log.i(CV_TAG, "OpenCV Libraries loaded...");
        } else {
            Toast.makeText(
                this.getApplicationContext(),
                "WARNING: Could not load OpenCV Libraries!",
                Toast.LENGTH_LONG
            ).show();
        }
    }
}