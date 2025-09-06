package com.example.todolist.data.lmpl

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.example.todolist.domain.repository.SystemNavigatorRepository

class SystemNavigatorRepositoryImpl(private val context: Context) : SystemNavigatorRepository {
    override suspend fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}