package com.azharkova.test_kmm.ui

import androidx.compose.foundation.Image as ComposeImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.azharkova.test_kmm.network.createHttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.readBytes

expect fun imageFromBytes(bytes: ByteArray): ImageBitmap
suspend fun loadPicture(url: String): Result<ImageBitmap> {
    val client = createHttpClient()
    return try {
        val image = client.get(url).readBytes()
        Result.success(imageFromBytes(image))
    } catch (e: Exception) {
        Result.failure(e)
    }
}

@Composable
fun ExternalImage(url: String, modifier: Modifier, OnFail: @Composable () -> Unit) {
    var isLoading by remember { mutableStateOf(false) }
    var hasFail by remember { mutableStateOf(false) }
    var imageBitmap: ImageBitmap? by remember { mutableStateOf(null) }

    LaunchedEffect(url) {
        isLoading = true
        loadPicture(url)
            .onSuccess {
                imageBitmap = it
            }
            .onFailure {
                hasFail = true
            }
        isLoading = false
    }

    when {
        isLoading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        hasFail -> {
            OnFail()
        }
        else -> {
            imageBitmap?.let { bitmap ->
ComposeImage(
                    bitmap = bitmap,
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    alpha = DefaultAlpha,
                    colorFilter = null,
                )

            } ?: OnFail()
        }
    }
}