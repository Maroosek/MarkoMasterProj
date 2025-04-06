package pl.marosek.mgrmarko.fileManager

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FileManager {

//    fun saveTextToDownloads(context: Context, fileName: String, text: String) {
//        val contentResolver = context.contentResolver
//        val values = ContentValues().apply {
//            put(MediaStore.Downloads.DISPLAY_NAME, fileName)
//            put(MediaStore.Downloads.MIME_TYPE, "text/plain")
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                put(MediaStore.Downloads.IS_PENDING, 1)
//            }
//        }
//
//        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
//        } else {
//            MediaStore.Files.getContentUri("external")
//        }
//
//        val fileUri = contentResolver.insert(collection, values)
//
//        if (fileUri != null) {
//            contentResolver.openOutputStream(fileUri).use { outputStream ->
//                outputStream?.write(text.toByteArray())
//            }
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                values.clear()
//                values.put(MediaStore.Downloads.IS_PENDING, 0)
//                contentResolver.update(fileUri, values, null, null)
//            }
//
//            Toast.makeText(context, "Zapisano do Download/$fileName", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(context, "Nie udało się zapisać pliku.", Toast.LENGTH_SHORT).show()
//        }
//    }

    fun saveDataToFile(context: Context, source: String, data: String) {
        val dateFormat = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        val fileName = "$currentDate.txt"

        val contentResolver = context.contentResolver

        // Check if the file already exists
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Files.getContentUri("external")
        }

        val projection = arrayOf(MediaStore.Downloads._ID)
        val selection = "${MediaStore.Downloads.DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf(fileName)

        var existingFileUri: Uri? = null
        contentResolver.query(
            collection,
            projection,
            selection,
            selectionArgs,
            null
        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Downloads._ID)
                val id = cursor.getLong(idColumn)
                existingFileUri = ContentUris.withAppendedId(collection, id)
            }
        }

        try {
            if (existingFileUri != null) {
                // If exists - append data
                contentResolver.openOutputStream(existingFileUri!!, "wa")?.use { outputStream ->
                    val timestamp = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                    val dataToAppend = "\n\n[$timestamp] Źródło: $source\n$data"
                    outputStream.write(dataToAppend.toByteArray())
                }
                Toast.makeText(context, "Updated file $fileName", Toast.LENGTH_SHORT).show()
            } else {
                // If not exists - create new
                val values = ContentValues().apply {
                    put(MediaStore.Downloads.DISPLAY_NAME, fileName)
                    put(MediaStore.Downloads.MIME_TYPE, "text/plain")
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        put(MediaStore.Downloads.IS_PENDING, 1)
                    }
                }

                val newFileUri = contentResolver.insert(collection, values)

                if (newFileUri != null) {
                    contentResolver.openOutputStream(newFileUri)?.use { outputStream ->
                        val timestamp = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
                        val fileContent = "[$timestamp] Źródło: $source\n$data"
                        outputStream.write(fileContent.toByteArray())
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        values.clear()
                        values.put(MediaStore.Downloads.IS_PENDING, 0)
                        contentResolver.update(newFileUri, values, null, null)
                    }

                    Toast.makeText(context, "Created new file $fileName", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Could not create file.", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error while saving: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}