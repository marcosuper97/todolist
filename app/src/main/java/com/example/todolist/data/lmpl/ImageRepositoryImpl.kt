package com.example.todolist.data.lmpl

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.net.toUri
import com.example.todolist.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID


class ImageRepositoryImpl(
    private val contentResolver: ContentResolver,
    private val picturesDir: File
) : ImageRepository {
    override suspend fun loadToStorage(imageUri: String): Result<String> =
        withContext(Dispatchers.IO) {
            runCatching {
                val fileName = "${UUID.randomUUID()}.jpg"
                val file = File(picturesDir, fileName)

                contentResolver.openInputStream(imageUri.toUri())?.use { inputStream ->
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                        ?: throw IllegalStateException("Failed to decode image")
                    FileOutputStream(file).use { outputStream ->
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream)
                    }
                } ?: throw IOException("Failed to open image stream")

                Uri.fromFile(file).toString()
            }
        }

    override suspend fun deleteFromStorage(imageUri: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            runCatching {
                val cleanPath = imageUri.removePrefix("file://")
                val file = File(cleanPath)

                if (!file.exists()) return@runCatching Unit

                if (!file.delete()) {
                    throw IOException("Failed to delete file: $imageUri")
                }

                Unit
            }
        }
}