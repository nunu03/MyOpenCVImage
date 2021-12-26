package com.myopencvimage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity(), RecycleviewAdapter.OnItemClickListener {
    private val mRecyclerview by lazy {findViewById<RecyclerView>(R.id.recyclerview) }
    private var mData = mutableListOf("灰度", "高斯模糊", "自定义模糊效果")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerview.layoutManager = LinearLayoutManager(this)
        val dividerDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        dividerDecoration.setDrawable(resources.getDrawable(R.drawable.bg_divider))
        mRecyclerview.addItemDecoration(dividerDecoration)
        val adapter = RecycleviewAdapter(mData)
        adapter.setOnItemClickListener(this)
        mRecyclerview.adapter = adapter
    }

    override fun onItemClick(view: View?, position: Int) {
        when (position) {
            0 -> {
                startActivity(Intent(this,GrayActivity::class.java))
            }
            1 -> {
                startActivity(Intent(this,GaussianActivity::class.java))
            }
            2 -> {
                startActivity(Intent(this,CustomerGaussianActivity::class.java))
            }
        }
    }
}