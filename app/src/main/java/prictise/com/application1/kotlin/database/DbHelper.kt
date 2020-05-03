package prictise.com.application1.kotlin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * @Author zhisiyi
 * @Date 2019.11.08 10:55
 * @Comment
 */
/**
 * Author ： BlackHao
 * Time : 2017/8/30 14:16
 * Description : 数据库初始化工具类
 */
class DbHelper(context: Context, DB_NAME: String, DB_VERSION: Int)
    : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    private val createSql = "create table if not exists ${Constant.TABLE_NAME} " +
            "(${Constant.ID} integer primary key, ${Constant.NAME} text, ${Constant.ACCOUNT} text, " +
            "${Constant.PSW} text, ${Constant.NOTES} text, ${Constant.CREATE_TIME} text)"

    override fun onCreate(db: SQLiteDatabase?) = db!!.execSQL(createSql)

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}