package com.example.todolist.ui.common

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke

@SuppressLint("SuspiciousModifierThen")
@Composable
fun Modifier.animatedDashedBorder(
    color: Color,
    strokeWidth: Float = 2f,
    dashWidth: Float = 10f,
    gapWidth: Float = 10f,
    cornerRadius: Float = 24f // радиус скругления
): Modifier {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = dashWidth + gapWidth,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    return this.then(
        drawBehind {
            val pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(dashWidth, gapWidth),
                phase = phase
            )
            drawRoundRect(
                color = color,
                style = Stroke(width = strokeWidth, pathEffect = pathEffect),
                size = size,
                cornerRadius = CornerRadius(cornerRadius, cornerRadius)
            )
        }
    )
}