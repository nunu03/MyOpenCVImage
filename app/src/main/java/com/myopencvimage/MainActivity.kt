package com.myopencvimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val CV_TAG = "OpenCV"
    private var processBtn: Button? = null
    private var iv: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniLoadOpenCV();//加载OpenCV的本地库
        processBtn = findViewById(R.id.process_btn);//实例化按钮并添加事件响应
        processBtn?.setOnClickListener(this);
    }

    //加载OpenCV的本地库
    private fun iniLoadOpenCV() {
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

    override fun onClick(p0: View?) {
        var bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.img);
        var src = Mat();
        var dst = Mat();
        Utils.bitmapToMat(bitmap, src);
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGRA2GRAY);
        Utils.matToBitmap(dst, bitmap);
        iv = findViewById(R.id.sample_img);
        iv?.setImageBitmap(bitmap);
        src.release()
        dst.release()
    }
}