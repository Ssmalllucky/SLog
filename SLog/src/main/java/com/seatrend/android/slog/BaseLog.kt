package com.seatrend.android.slog

import android.util.Log
import com.seatrend.android.slog.SLog.A
import com.seatrend.android.slog.SLog.D
import com.seatrend.android.slog.SLog.E
import com.seatrend.android.slog.SLog.I
import com.seatrend.android.slog.SLog.V
import com.seatrend.android.slog.SLog.W

/**
 * @ClassName BaseLog
 * @Author Tony
 * @Date 2022/1/12
 * @Description
 */
object BaseLog {

    val MAX_LENGTH = 4000

    fun printDefault(type: Int, tag: String, msg: String) {
        var index = 0
        var length = msg.length
        var countOfSub = length / MAX_LENGTH

        if (countOfSub > 0) {
            for (i in msg.indices) {
                var sub = msg.substring(index, index + MAX_LENGTH)
                printSub(type, tag, sub)
            }
            printSub(type, tag, msg.substring(index, length))
        } else {
            printSub(type, tag, msg)
        }
    }

    fun printSub(type: Int, tag: String, msg: String) {
        when (type) {
            V -> Log.v(tag, msg)
            I -> Log.i(tag, msg)
            D -> Log.d(tag, msg)
            W -> Log.w(tag, msg)
            E -> Log.e(tag, msg)
            A -> Log.i(tag, msg)
        }
    }
}