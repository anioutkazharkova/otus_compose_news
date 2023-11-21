package com.azharkova.test_kmm.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val ioDispatcher: CoroutineDispatcher = Dispatchers.Default
actual val uiDispatcher: CoroutineDispatcher = Dispatchers.Main