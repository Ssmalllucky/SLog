package com.seatrend.android.slog

import android.text.TextUtils

/**
 * @ClassName SLog
 * @Author Tony
 * @Date 2022/1/12
 * @Description Log打印工具类。
 * [SLog.i]打印Info类日志信息
 * [SLog.v]打印Verbose类日志信息
 * [Slog.w]打印Warn类日志信息
 * [SLog.d]打印Debug类日志信息
 * [SLog.e]打印Error类日志信息
 * 打印开关通过[SLog.IS_SHOW_LOG]控制，默认为true.
 */
object SLog {

    private const val TAG_DEFAULT = "SLog"

    val LINE_SEPARATOR: String = System.getProperty("line.separator")

    const val V = 0x1
    const val D = 0x2
    const val I = 0x3
    const val W = 0x4
    const val E = 0x5
    const val A = 0x6
    const val NULL_TIPS = "Log with null object"
    const val JSON_INDENT = 4

    private const val JSON = 0x7
    private const val XML = 0x8
    private const val STACK_TRACE_INDEX_5 = 5
    private const val SUFFIX = ".java"
    private const val NULL = "null"

    private var IS_SHOW_LOG = true

    fun pringLog(type: Int, tag: String?, objects: Any) {
        if (!IS_SHOW_LOG) {
            return
        }

        var contents: Array<String> = wrapperContent(STACK_TRACE_INDEX_5, tag, objects)
        var tag = contents[0]
        var msg = contents[1]
        var headString = contents[2]

        when (type) {
            V, D, I, W, E, A -> BaseLog.printDefault(type, tag, headString + msg)
            JSON -> JsonLog.printJson(tag, msg, headString)
            XML -> XmlLog.printXml(tag, msg, headString)
        }
    }

    fun json(msg: Any) {
        pringLog(JSON, null, msg)
    }

    fun xml(msg: Any) {
        pringLog(XML, null, msg)
    }

    fun v(msg: Any) {
        pringLog(V, null, msg)
    }

    fun d(msg: Any) {
        pringLog(D, null, msg)
    }

    fun i(msg: Any) {
        pringLog(I, null, msg)
    }

    fun w(msg: Any) {
        pringLog(W, null, msg)
    }

    fun e(msg: Any) {
        pringLog(E, null, msg)
    }

    /**
     * 组装要显示的Log内容。
     */
    private fun wrapperContent(
        stackTraceIndex: Int,
        tagStr: String?,
        objects: Any
    ): Array<String> {
        //返回方法被调用的堆栈信息，返回StackTraceElement数组
        val stackTrace = Thread.currentThread().stackTrace
        val targetElement = stackTrace[stackTraceIndex]
        var className = targetElement.className
        //获取class的层级段落名称，并以数组的方式返回，例如：["com","seatrend","android","slog","SLog"]
        val classNameInfo = className.split("\\.".toRegex()).toTypedArray()

        //得到className，并加上后缀
        if (classNameInfo.isNotEmpty()) {
            className = classNameInfo[classNameInfo.size - 1] + SUFFIX
        }

        //内部类处理
        if (className.contains("$")) {
            className =
                className.split("\\$".toRegex()).toTypedArray()[0] + SUFFIX
        }

        val methodName = targetElement.methodName
        var lineNumber = targetElement.lineNumber
        if (lineNumber < 0) {
            lineNumber = 0
        }
        var tag = tagStr ?: className
        if (TextUtils.isEmpty(tag)) {
            tag = TAG_DEFAULT
        }

        //格式化输出的msg
        val msg: String =
            if (objects == null) NULL_TIPS else getObjectsString(objects)
        val headString = "[ ($className:$lineNumber)#$methodName ] "
        return arrayOf(tag, msg, headString)
    }

    /**
     * 拼装需要打印的字符串。
     */
    private fun getObjectsString(objects: Any): String {
        return if (objects != null) {
            objects as String
        } else {
            NULL
        }
    }
}