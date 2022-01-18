package com.seatrend.android.slog

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            SLog.xml("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<phoneInfos>\n" +
                    "    <Brand name=\"华为\">\n" +
                    "        <Type name= \"123\"></Type>\n" +
                    "        <Type name= \"456\"></Type>\n" +
                    "        <Type name= \"789\"></Type>\n" +
                    "    </Brand>\n" +
                    "     <Brand name=\"iphone\">\n" +
                    "        <Type name= \"iphone6\"></Type>\n" +
                    "        <Type name= \"iphone7\"></Type>\n" +
                    "        <Type name= \"iphone4\"></Type>\n" +
                    "    </Brand>\n" +
                    "</phoneInfos>")
        }, 5000)
    }
}