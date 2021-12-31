package com.myopencvimage

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.*
import org.opencv.imgproc.Imgproc

object ImageProcessUtils {

     const val CUSTOM_BLUR_COMMAND = "自定义算子-模糊"
     const val CUSTOM_EDGE_COMMAND = "自定义算子-边缘"
     const val CUSTOM_SHARPEN_COMMAND = "自定义算子-锐化"

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

    /**
     * 自定义算子-模糊
     * @param command
     * @param bitmap
     */
    fun customFilter(command: String?, bitmap: Bitmap?): Bitmap? {
        val src = Mat()
        val dst = Mat()
        Utils.bitmapToMat(bitmap, src)
        val kernel: Mat = getCustomOperator(command)
        Imgproc.filter2D(src, dst, -1, kernel, Point(-1.0, -1.0), 0.0, 4)
        Utils.matToBitmap(dst, bitmap)
        kernel.release()
        src.release()
        dst.release()
        return  bitmap
    }


    private fun getCustomOperator(command: String?): Mat {
        val kernel = Mat(3, 3, CvType.CV_32FC1) //为了中心化宽高最好是奇数，32--对应double float，16--int
        when (command) {
            CUSTOM_BLUR_COMMAND -> {//修改data值大小可以看效果
                kernel.put(
                    0,
                    0,
                    1.0 / 8.0,
                    1.0 / 8.0,
                    1.0 / 8.0,
                    1.0 / 8.0,
                    1.0 / 8.0,
                    1.0 / 8.0,
                    1.0 / 8.0,
                    1.0 / 8.0,
                    1.0 / 8.0
                )
            }
            CUSTOM_EDGE_COMMAND -> {
                kernel.put(0, 0, -1.0, -1.0, -1.0, -1.0, 8.0, -1.0, -1.0, -1.0, -1.0)
            }
            CUSTOM_SHARPEN_COMMAND -> {
                kernel.put(0, 0, -1.0, -1.0, -1.0, -1.0, 9.0, -1.0, -1.0, -1.0, -1.0)
            }
        }
        return kernel
    }

    /**
     * 均值模糊
     * @param bitmap
     */
    fun meanBlur(bitmap: Bitmap?) : Bitmap? {
        val src = Mat()
        val dst = Mat()
        Utils.bitmapToMat(bitmap, src)
        Imgproc.blur(src, dst, Size(30.0, 30.0), Point(-1.0, -1.0), Core.BORDER_DEFAULT)
        Utils.matToBitmap(dst, bitmap)
        src.release()
        dst.release()
        return bitmap
    }

    /**
     * 双边模糊
     * @param bitmap
     */
    fun biBlur(bitmap: Bitmap?) : Bitmap? {
        val src = Mat()
        val dst = Mat()
        Utils.bitmapToMat(bitmap, src)
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2BGR)
        Imgproc.bilateralFilter(src, dst, 15, 150.0, 15.0, Core.BORDER_DEFAULT) //双边模糊
        val kernel = Mat(3, 3, CvType.CV_16S) //锐化
        kernel.put(0, 0, 0.0, -1.0, 0.0, -1.0, 5.0, -1.0, 0.0, -1.0, 0.0) //锐化算子
        Imgproc.filter2D(dst, dst, -1, kernel, Point(-1.0, -1.0), 0.0, 4)
        Utils.matToBitmap(dst, bitmap)
        src.release()
        dst.release()
        return bitmap
    }
}