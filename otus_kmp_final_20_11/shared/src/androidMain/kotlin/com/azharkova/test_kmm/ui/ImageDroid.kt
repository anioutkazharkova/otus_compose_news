package com.azharkova.test_kmm.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual fun imageFromBytes(bytes: ByteArray): ImageBitmap {
    return convertImageByteArrayToBitmap(bytes).asImageBitmap()
}

fun convertImageByteArrayToBitmap(imageData: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
}