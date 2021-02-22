package com.bracket.datasharemain

import android.database.Cursor
import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrialGuideProvider(private val mainApplication: MainApplication) {

    suspend fun getToken(): String {
        var token = ""
        withContext(Dispatchers.IO) {
            val auth = mainApplication.resources.getString(R.string.provider_authority)
            val table = mainApplication.resources.getString(R.string.provider_table_users)
            val cursor: Cursor? = mainApplication.contentResolver?.query(
                Uri.parse("content://${auth}/${table}"),
                null,
                null,
                null,
                null
            )
            cursor?.moveToFirst()
            val value = cursor?.getString(0)
            cursor?.close()
            token = value ?: ""
        }
        return token
    }

}