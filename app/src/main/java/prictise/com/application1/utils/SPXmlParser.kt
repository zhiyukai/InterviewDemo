package prictise.com.application1.utils

import android.text.TextUtils
import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream


/**
 * Description: $Description$
 * Created by yangdongze on 2018/11/20 16:31.
 * Email: yangdongze@sunlands.com
 * Version: $Version$
 */
object SPXmlParser {

    //命名空间
    private val NS: String? = null

    @Throws(XmlPullParserException::class, IOException::class)
    fun parse(inputStream: InputStream): HashMap<String, String> {
        inputStream.use { it ->
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(it, null)
            parser.nextTag()
            return startParse(parser)
        }
    }


    @Throws(XmlPullParserException::class, IOException::class)
    private fun startParse(parser: XmlPullParser): HashMap<String, String> {
        val hashMap = HashMap<String, String>()
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when {
                parser.name == "string" -> readString(parser, hashMap)
                parser.name == "int" -> readInt(parser, hashMap)
                parser.name == "boolean" -> readBoolean(parser, hashMap)
                parser.name == "long" -> readLong(parser, hashMap)
                else -> skip(parser)
            }
        }
        return hashMap
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readString(parser: XmlPullParser, hashMap: HashMap<String, String>) {
        parser.require(XmlPullParser.START_TAG, NS, "string")
        val key = parser.getAttributeValue(null, "name")
        var value = parser.getAttributeValue(null, "value")
        if (TextUtils.isEmpty(value)) value = readText(parser) else readText(parser)
        if (key != null && !TextUtils.isEmpty(value)) hashMap[key] = value
        parser.require(XmlPullParser.END_TAG, NS, "string")
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readInt(parser: XmlPullParser, hashMap: HashMap<String, String>) {
        parser.require(XmlPullParser.START_TAG, NS, "int")
        val key = parser.getAttributeValue(null, "name")
        var value = parser.getAttributeValue(null, "value")
        if (TextUtils.isEmpty(value)) value = readText(parser) else readText(parser)
        if (key != null && !TextUtils.isEmpty(value)) hashMap[key] = value
        parser.require(XmlPullParser.END_TAG, NS, "int")
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readBoolean(parser: XmlPullParser, hashMap: HashMap<String, String>) {
        parser.require(XmlPullParser.START_TAG, NS, "boolean")
        val key = parser.getAttributeValue(null, "name")
        var value = parser.getAttributeValue(null, "value")
        if (TextUtils.isEmpty(value)) value = readText(parser) else readText(parser)
        if (key != null && !TextUtils.isEmpty(value)) hashMap[key] = value
        parser.require(XmlPullParser.END_TAG, NS, "boolean")
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readLong(parser: XmlPullParser, hashMap: HashMap<String, String>) {
        parser.require(XmlPullParser.START_TAG, NS, "long")
        val key = parser.getAttributeValue(null, "name")
        var value = parser.getAttributeValue(null, "value")
        if (TextUtils.isEmpty(value)) value = readText(parser) else readText(parser)
        if (key != null && !TextUtils.isEmpty(value)) hashMap[key] = value
        parser.require(XmlPullParser.END_TAG, NS, "long")
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readText(parser: XmlPullParser): String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun skip(parser: XmlPullParser) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            throw IllegalStateException()
        }
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }
}