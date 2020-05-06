package prictise.com.application1.utils

import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import prictise.com.application1.qiniu.utils.StringUtils
import java.io.ByteArrayInputStream
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory


/**
 * @Author zhisiyi
 * @Date 2020.04.22 14:55
 * @Comment
 */
object FileParseUtil {
    val TAG = "FileParseUtil"
    fun parseMsgContentTicket(msgContent: String): String {
        var flag: Boolean = true
        var ticket: String? = ""
        try {
            val documentBuilderFactory = DocumentBuilderFactory.newInstance()
            var documentBuilder = documentBuilderFactory.newDocumentBuilder()
            val bytes = ByteArrayInputStream(msgContent.toByteArray())
            documentBuilder.parse(bytes)
        } catch (e: java.lang.Exception) {
            LogcatUtils.showDLog(TAG, "parseMsgContentTicket  e.cause = ${e.cause}")
            LogcatUtils.showDLog(TAG, "parseMsgContentTicket  e.localizedMessage = ${e.localizedMessage}")
            LogcatUtils.showDLog(TAG, "parseMsgContentTicket  e.message = ${e.message}")
            LogcatUtils.showDLog(TAG, "parseMsgContentTicket  e.printStackTrace() = ${e.printStackTrace()}")
            LogcatUtils.showDLog(TAG, "parseMsgContentTicket  e.fillInStackTrace() = ${e.fillInStackTrace()}")
            flag = false
        }
        LogcatUtils.showDLog(TAG, "parseMsgContentTicket  flag = $flag")
        if (flag) {
            try {
                val documentBuilderFactory = DocumentBuilderFactory.newInstance()
                var documentBuilder = documentBuilderFactory.newDocumentBuilder()
                val bytes = ByteArrayInputStream(msgContent.toByteArray())
                var document = documentBuilder.parse(bytes)
                val attributes = document.childNodes.item(0).attributes
                ticket = attributes.getNamedItem("ticket")?.nodeValue
            } catch (e: java.lang.Exception) {
                LogcatUtils.showDLog(TAG, "parseMsgContentTicket3  e.cause = ${e.cause}")
                LogcatUtils.showDLog(TAG, "parseMsgContentTicket3  e.localizedMessage = ${e.localizedMessage}")
                LogcatUtils.showDLog(TAG, "parseMsgContentTicket3  e.message = ${e.message}")
                LogcatUtils.showDLog(TAG, "parseMsgContentTicket3  e.printStackTrace() = ${e.printStackTrace()}")
                LogcatUtils.showDLog(TAG, "parseMsgContentTicket3  e.fillInStackTrace() = ${e.fillInStackTrace()}")
            }
        }
        return ticket!!
    }

    fun isGoodJson(json: String): Boolean {
        return if (StringUtils.isBlank(json)) {
            false
        } else try {
            JsonParser().parse(json)
            true
        } catch (e: JsonParseException) {
            LogcatUtils.showDLog(TAG, "bad json: $json")
            false
        }
    }

    fun deleteFileFromDisk(filePath: String): Boolean {
        Log.d(TAG, "deleteFileFromDisk")
        if (TextUtils.isEmpty(filePath)) {
            Log.d(TAG, "deleteFileFromDisk filePath is empty.")
            return false
        }
        val deleteFile = File(filePath)
        if (deleteFile.exists()) {
            return deleteFile.delete()
        }

        return false
    }

    fun <T> parseGson(contentGson: String, clazz: Class<T>): T? {
        if (TextUtils.isEmpty(contentGson) || clazz == null) {
            return null
        }
        try {
            val gsonType = object : TypeToken<String>() {}.type
            return Gson().fromJson<T>(contentGson, gsonType)
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "parseGson e : ${e.localizedMessage}")
        }
        return null
    }
}