package com.seatrend.android.slog

import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import java.io.*
import java.util.*

/**
 * @ClassName FileLog
 * @Author Tony
 * @Date 2022/1/18
 * @Description
 */
object FileLog {

    private val FILE_PREFIX = "SLog_"
    private val FILE_FORMAT = ".log"

    fun printFile(
        tag: String?,
        targetDirectory: File,
        @Nullable fileName: String?,
        headString: String,
        msg: String
    ) {
        var fileName = fileName
        fileName = fileName ?: getFileName()
        if (save(targetDirectory, fileName, msg)) {
            Log.d(
                tag,
                headString + " save log success ! location is >>>" + targetDirectory.absolutePath + "/" + fileName
            )
        } else {
            Log.e(tag, headString + "save log fails !")
        }
    }

    private fun save(dic: File, @NonNull fileName: String?, msg: String): Boolean {
        val file = File(dic, fileName)
        return try {
            val outputStream: OutputStream = FileOutputStream(file)
            val outputStreamWriter = OutputStreamWriter(outputStream, "UTF-8")
            outputStreamWriter.write(msg)
            outputStreamWriter.flush()
            outputStream.close()
            true
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            false
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            false
        } catch (e: IOException) {
            e.printStackTrace()
            false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun getFileName(): String? {
        val random = Random()
        return FILE_PREFIX + java.lang.Long.toString(
            System.currentTimeMillis() + random.nextInt(
                10000
            )
        ).substring(4) + FILE_FORMAT
    }
}