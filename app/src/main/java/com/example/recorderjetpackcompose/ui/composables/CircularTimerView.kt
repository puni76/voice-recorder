package com.example.recorderjetpackcompose.ui.composables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.recorderjetpackcompose.viewModels.TimerViewModel

@Composable
fun CircularTimerView(
    viewModel: TimerViewModel
) {
    val time by viewModel.timerValue.observeAsState()

    val angle = animateFloatAsState(
        targetValue = (time?.times(6)?.toFloat())?.rem(360) ?: 0f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing), label = ""
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .padding(32.dp)
            .clip(shape = RoundedCornerShape(96.dp))
    ) {
        Canvas(
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp)
//                .background(MaterialTheme.colorScheme.onSurface)
        ) {
            drawArc(
                color = Color.Green,
                startAngle = -90f,
                sweepAngle = angle.value,
                useCenter = false,
                style = Stroke(width = 20f, cap = StrokeCap.Round)
            )
        }
    }
}