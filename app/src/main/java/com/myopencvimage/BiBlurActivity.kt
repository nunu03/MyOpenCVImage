package com.myopencvimage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class BiBlurActivity : AppCompatActivity(), View.OnClickListener {
    private var iv: ImageView? = null
    private var bitmap :Bitmap?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biblur)
        iv = findViewById(R.id.sample_img)
        findViewById<Button>(R.id.biblur_btn)?.setOnClickListener(this)
        findViewById<Button>(R.id.reset_btn)?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        bitmap = BitmapFactory.decodeResource(this.resources, R.mipmap.img)
        if (view?.id == R.id.biblur_btn) {
            iv?.setImageBitmap(ImageProcessUtils.biBlur(bitmap));
        } else {
            iv?.setImageBitmap(bitmap);
        }
    }
}