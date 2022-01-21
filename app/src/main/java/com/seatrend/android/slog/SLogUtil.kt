package com.seatrend.android.slog

import android.text.TextUtils
import android.util.Log

/**
 * @ClassName SLogUtil
 * @Author Tony
 * @Date 2022/1/18
 * @Description
 */
object SLogUtil {

    fun isEmpty(line: String): Boolean {
        return TextUtils.isEmpty(line) || line == "\n" || line == "\t" || TextUtils.isEmpty(line.trim { it <= ' ' })
    }

    fun printLine(tag: String?, isTop: Boolean) {
        if (isTop) {
            Log.d(
                tag,
                "╔═══════════════════════════════════════════════════════════════════════════════════════"
            )
        } else {
            Log.d(
                tag,
                "╚═══════════════════════════════════════════════════════════════════════════════════════"
            )
        }
    }
}