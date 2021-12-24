package com.myopencvimage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.jump_gray)?.setOnClickListener(this);
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.jump_gray){
            startActivity(Intent(this,GrayActivity::class.java))
        }
    }
}