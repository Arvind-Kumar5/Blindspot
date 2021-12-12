//MainActivity.kt, OverlaySurfaceView.kt, ObjectDetector.kt, YuvToRgbConverter.kt, and DetectionObject.kt are all implementations taken from https://linuxtut.com/en/fc661a72d08f7f59cf41/
package com.example.blindspot

import android.graphics.RectF

/**
 * 検出結果を入れるクラス
 */
data class DetectionObject(
        val score: Float,
        val label: String,
        val boundingBox: RectF
)