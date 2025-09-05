package com.example.todolist.ui.edit_screen.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.todolist.R
import com.example.todolist.ui.common.animatedDashedBorder

@Composable
fun ImageChangeForm(onImageClick: () -> Unit, imageUri: Uri?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 180.dp)
            .clickable { onImageClick() }
            .then(
                if (imageUri == null) {
                    Modifier.animatedDashedBorder(
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 4f,
                        dashWidth = 40f,
                        gapWidth = 10f
                    )
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (imageUri == null) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painterResource(R.drawable.image_placeholder),
                    contentDescription = stringResource(R.string.add_image)
                )
                Text(stringResource(R.string.press_to_add_image))
            }
        } else {
            AsyncImage(
                model = imageUri,
                contentDescription = stringResource(R.string.choosed_image),
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
        }
    }
}