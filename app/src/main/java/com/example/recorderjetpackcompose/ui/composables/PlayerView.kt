package com.example.recorderjetpackcompose.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.recorderjetpackcompose.R
import com.example.recorderjetpackcompose.ui.customViews.AudioVisualizerView

@Composable
fun PlayerView(
    getSessionId: () -> Int?,
    onPlay: () -> Unit,
    onPause: () -> Unit
) {

    var sessionId: Int? by remember {
        mutableStateOf(null)
    }
    var isPlaying by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .clip(
                RoundedCornerShape(corner = CornerSize(8.dp))
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val context = LocalContext.current
        if (sessionId != null) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                factory = {
                    AudioVisualizerView(context)
                }
            ) {
                it.apply {
                    setColor()
                    setDensityValue()
                    setPlayerId(sessionId)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            RoundButton(
                modifier = Modifier.fillMaxWidth(),
                iconId = if (isPlaying) {
                    R.drawable.ic_pause
                } else {
                    R.drawable.ic_play
                },
                color = Color.Green,
            ) {
                isPlaying = if (isPlaying) {
                    onPause()
                    false
                } else {
                    onPlay()
                    sessionId = getSessionId()
                    true
                }
            }
        }
    }
}