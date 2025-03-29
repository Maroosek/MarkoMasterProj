package pl.marosek.mgrmarko.FileManager

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FileManager(private val context: Context) {

    //TODO fix this

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val timeFormatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    @RequiresApi(Build.VERSION_CODES.Q)
    fun saveFile(text: String): Boolean {
        return try {
            val fileName = "${dateFormatter.format(Date())}.txt"  // np. "2023-11-15.txt"
            val fileContent = "${getCurrentTime()} $text\n"  // np. "14:30:45 Hello World"

            val contentResolver = context.contentResolver
            val uri = findOrCreateFile(fileName)

            uri?.let {
                contentResolver.openOutputStream(uri, "wa")?.use { outputStream ->
                    outputStream.write(fileContent.toByteArray())
                    true
                }
            } ?: false
        } catch (e: Exception) {
            false
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun findOrCreateFile(fileName: String): android.net.Uri? {
        val contentResolver = context.contentResolver
        val projection = arrayOf(MediaStore.Downloads._ID)

        // Sprawdź, czy plik już istnieje
        val query = contentResolver.query(
            MediaStore.Downloads.EXTERNAL_CONTENT_URI,
            projection,
            "${MediaStore.Downloads.DISPLAY_NAME} = ?",
            arrayOf(fileName),
            null
        )

        return if (query?.count ?: 0 > 0) {
            query?.use {
                it.moveToFirst()
                val id = it.getLong(it.getColumnIndexOrThrow(MediaStore.Downloads._ID))
                android.net.Uri.withAppendedPath(MediaStore.Downloads.EXTERNAL_CONTENT_URI, id.toString())
            }
        } else {
            // Utwórz nowy plik
            val contentValues = ContentValues().apply {
                put(MediaStore.Downloads.DISPLAY_NAME, fileName)
                put(MediaStore.Downloads.MIME_TYPE, "text/plain")
                put(MediaStore.Downloads.IS_PENDING, 1)
            }
            contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)?.also { uri ->
                contentValues.clear()
                contentValues.put(MediaStore.Downloads.IS_PENDING, 0)
                contentResolver.update(uri, contentValues, null, null)
            }
        }
    }

    private fun getCurrentTime(): String {
        return timeFormatter.format(Date())
    }
}