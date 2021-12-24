package com.myopencvimage

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

object ImageProcessUtils {

    /**
     * 转换为灰度图像
     * @param bitmap
     * @return
     */
    fun convert2Gray(bitmap: Bitmap?): Bitmap? {
        val src = Mat()
        val dst = Mat()
        Utils.bitmapToMat(bitmap, src)
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGRA2GRAY)
        Utils.matToBitmap(dst, bitmap)
        src.release() //释放内存
        dst.release()
        return bitmap
    }

    /**
     * 高斯模糊
     * @param bitmap
     */
    fun gaussianBlur(bitmap: Bitmap?): Bitmap?  {
        val src = Mat()
        val dst = Mat()
        Utils.bitmapToMat(bitmap, src)
        Imgproc.GaussianBlur(src, dst, Size(81.0, 81.0), 0.0, 0.0, 4)
        Utils.matToBitmap(dst, bitmap)
        src.release()
        dst.release()
        return bitmap
    }
}