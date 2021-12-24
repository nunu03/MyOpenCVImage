package com.myopencvimage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class GrayActivity : AppCompatActivity(), View.OnClickListener {
    private var iv: ImageView? = null
    private var bitmap :Bitmap?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gray)
        iv = findViewById(R.id.sample_img)
        findViewById<Button>(R.id.gray_btn)?.setOnClickListener(this)
        findViewById<Button>(R.id.reset_btn)?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        bitmap = BitmapFactory.decodeResource(this.resources, R.mipmap.img)
        if (view?.id == R.id.gray_btn) {
            val grayBitmap = ImageProcessUtils.convert2Gray(bitmap)
            iv?.setImageBitmap(grayBitmap);
        } else {
            iv?.setImageBitmap(bitmap);
        }
    }
}