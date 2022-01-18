package com.seatrend.android.slog

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * @ClassName JsonLog
 * @Author Tony
 * @Date 2022/1/18
 * @Description
 */
object JsonLog {
    fun printJson(tag: String?, msg: String, headString: String) {
        var message: String
        message = try {
            if (msg.startsWith("{")) {
                val jsonObject = JSONObject(msg)
                jsonObject.toString(SLog.JSON_INDENT)
            } else if (msg.startsWith("[")) {
                val jsonArray = JSONArray(msg)
                jsonArray.toString(SLog.JSON_INDENT)
            } else {
                msg
            }
        } catch (e: JSONException) {
            msg
        }
        SLogUtil.printLine(tag, true)
        message = headString + SLog.LINE_SEPARATOR + message
        val lines: Array<String> = message.split(SLog.LINE_SEPARATOR).toTypedArray()
        for (line in lines) {
            Log.d(tag, "║ $line")
        }
        SLogUtil.printLine(tag, false)
    }
}