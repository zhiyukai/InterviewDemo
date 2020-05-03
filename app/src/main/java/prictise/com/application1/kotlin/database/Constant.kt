package prictise.com.application1.kotlin.database

import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author zhisiyi
 * @Date 2019.11.08 10:54
 * @Comment
 */

class Constant {

    companion object {
        val DB_NAME = "account.db"
        val DB_VERSION = 1
        val TABLE_NAME = "AccountTable"
        //数据库表字段名
        val NAME = "name"
        val ACCOUNT = "account"
        val PSW = "psw"
        val NOTES = "notes"
        val CREATE_TIME = "create_time"
        val ID = "Id"
        //数据库表字段名对应位置
        val NAME_INDEX = 1
        val ACCOUNT_INDEX = 2
        val PSW_INDEX = 3
        val NOTES_INDEX = 4
        val CREATE_TIME_INDEX = 5
        val ID_INDEX = 0
        //导出数据库信息路径
        val EXPORT_FILE_NAME = "AllAccountExport.txt"
        //sp key 登录密码
        val KEY_LOGIN_PSW = "psw"
        //sp key 是否开启密码登录
        val KEY_IS_PSW_LOGIN = "isPswLogin"

        fun getNow(): String {
            if (android.os.Build.VERSION.SDK_INT >= 24) {
                return SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date())
            } else {
                var tms = Calendar.getInstance()
                return tms.get(Calendar.YEAR).toString() + "-" + tms.get(Calendar.MONTH).toString() + "-" + tms.get(Calendar.DAY_OF_MONTH).toString() + " " + tms.get(Calendar.HOUR_OF_DAY).toString() + ":" + tms.get(Calendar.MINUTE).toString() + ":" + tms.get(Calendar.SECOND).toString() + "." + tms.get(Calendar.MILLISECOND).toString()
            }

        }
    }

}