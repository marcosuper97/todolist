package com.example.todolist.ui.edit_screen.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.todolist.R
import com.example.todolist.ui.common.animatedDashedBorder

@Composable
fun ImagePastForm(onBoxClick: () -> Unit, imageUri: Uri?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable { onBoxClick }
            .animatedDashedBorder(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 4f,
                dashWidth = 40f,
                gapWidth = 10f
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
                    .fillMaxSize()
                    .clip(RoundedCornerShape(24.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Preview(showBackground = true, device = "id:Nexus One")
@Composable
fun ImagePastFormPreview() {
    ImagePastForm({}, null)
}

