package com.seatrend.android.slog

import android.util.Log
import java.io.StringReader
import java.io.StringWriter
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

/**
 * @ClassName XmlLog
 * @Author Tony
 * @Date 2022/1/18
 * @Description
 */
object XmlLog {

    fun printXml(tag: String, xml: String, headString: String) {
        var xml = xml
        if (xml != null) {
            xml = formatXML(xml)
            xml = """
            $headString
            $xml
            """.trimIndent()
        } else {
            xml = headString + SLog.NULL_TIPS
        }
        SLogUtil.printLine(tag, true)
        val lines: Array<String> = xml.split(SLog.LINE_SEPARATOR).toTypedArray()
        for (line in lines) {
            if (!SLogUtil.isEmpty(line)) {
                Log.d(tag, "║ $line")
            }
        }
        SLogUtil.printLine(tag, false)
    }

    private fun formatXML(inputXML: String): String {
        return try {
            val xmlInput: Source = StreamSource(StringReader(inputXML))
            val xmlOutput = StreamResult(StringWriter())
            val transformer = TransformerFactory.newInstance().newTransformer()
            transformer.setOutputProperty(OutputKeys.INDENT, "yes")
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")
            transformer.transform(xmlInput, xmlOutput)
            xmlOutput.writer.toString().replaceFirst(">".toRegex(), ">\n")
        } catch (e: Exception) {
            e.printStackTrace()
            inputXML
        }
    }
}