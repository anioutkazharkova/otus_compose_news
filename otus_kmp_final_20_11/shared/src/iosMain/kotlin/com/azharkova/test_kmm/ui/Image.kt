package com.azharkova.test_kmm.ui

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image

actual fun imageFromBytes(bytes: ByteArray):ImageBitmap {
    return Image.makeFromEncoded(bytes).toComposeImageBitmap()
}