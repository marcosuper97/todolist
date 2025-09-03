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
): ImageRepository {
    override suspend fun loadToStorage(imagePath: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            val fileName = "${UUID.randomUUID()}.jpg"
            val file = File(picturesDir, fileName)

            contentResolver.openInputStream(imagePath.toUri())?.use { inputStream ->
                val bitmap = BitmapFactory.decodeStream(inputStream)
                    ?: return@withContext Result.failure(IllegalStateException("Failed to decode image"))
                FileOutputStream(file).use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream)
                }
            } ?: return@withContext Result.failure(IOException("Failed to open image stream"))

            Result.success(Uri.fromFile(file).toString())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteFromStorage(fileUri: String) : Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val cleanPath = fileUri.removePrefix("file://")
            val file = File(cleanPath)
            if (!file.exists()) {
                return@withContext Result.success(Unit)
            }
            if (file.delete()) {
                Result.success(Unit)
            } else {
                Result.failure(IOException("Failed to delete file: $fileUri"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}