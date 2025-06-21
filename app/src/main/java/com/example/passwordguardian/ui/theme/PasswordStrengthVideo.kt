package com.example.passwordguardian.ui.theme

import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.delay

@Composable
fun PasswordStrengthVideo(strength: Int, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // ExoPlayer for the current video (always displayed)
    val activePlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
        }
    }

    // ExoPlayer for preloading the next video (hidden)
    val preloadPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = false
        }
    }

    // Video URI based on strength
    val (videoUri, repeatMode) = when {
        strength < 3 -> Uri.parse("android.resource://${context.packageName}/raw/weak") to ExoPlayer.REPEAT_MODE_ONE
        strength < 4 -> Uri.parse("android.resource://${context.packageName}/raw/good") to ExoPlayer.REPEAT_MODE_OFF
        else -> Uri.parse("android.resource://${context.packageName}/raw/strong") to ExoPlayer.REPEAT_MODE_OFF
    }

    // Track the currently active video Uri
    var activeUri by remember { mutableStateOf(videoUri) }
    var isFirstLoad by remember { mutableStateOf(true) } // Track first load

    // Preload the first video immediately (no black screen)
    LaunchedEffect(Unit) {
        if (isFirstLoad) {
            activePlayer.setMediaItem(MediaItem.fromUri(videoUri))
            activePlayer.prepare()
            activePlayer.repeatMode = repeatMode
            activePlayer.playWhenReady = true
            activeUri = videoUri
            isFirstLoad = false
        }
    }

    // Preload the new video without stopping the active video
    LaunchedEffect(videoUri) {
        if (videoUri != activeUri) {
            preloadPlayer.setMediaItem(MediaItem.fromUri(videoUri))
            preloadPlayer.prepare()
        }
    }

    // Switch to the preloaded video smoothly
    LaunchedEffect(videoUri, activeUri) {
        if (videoUri != activeUri) {
            preloadPlayer.playWhenReady = true
            delay(0) // Smooth transition delay
            activePlayer.setMediaItem(MediaItem.fromUri(videoUri))
            activePlayer.prepare()
            activePlayer.seekTo(0)
            activePlayer.repeatMode = repeatMode
            activePlayer.playWhenReady = true
            activeUri = videoUri // Switch control to the new video
        }
    }

    // Cleanup on exit
    DisposableEffect(Unit) {
        onDispose {
            activePlayer.release()
            preloadPlayer.release()
        }
    }

    // Display the active video with smooth transition
    Box(modifier = modifier) {
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = activePlayer
                    useController = false
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
