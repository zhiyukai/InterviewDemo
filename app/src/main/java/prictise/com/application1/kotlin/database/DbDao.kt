package prictise.com.application1.kotlin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import prictise.com.application1.utils.LogcatUtils

/**
 * @Author zhisiyi
 * @Date 2019.11.08 10:57
 * @Comment
 */

class DbDao(context: Context) {

    val TAG = "DbDao";

    private val dbHelper: DbHelper = DbHelper(context, Constant.DB_NAME, Constant.DB_VERSION)
    //SQLiteDatabase
    private val db: SQLiteDatabase

    init {
        db = dbHelper.writableDatabase
    }

    /**
     * 新增账户信息
     */
    fun addNewAccount(bean: AccountBean): Boolean {
        //新增结果标识
        val result: Boolean
        db.beginTransaction()
        result = try {
            //SQLs语句
            val sql = "insert into " + Constant.TABLE_NAME +
                    " (${Constant.NAME}, ${Constant.ACCOUNT}, ${Constant.PSW}, ${Constant.NOTES}, ${Constant.CREATE_TIME}) " +
                    "values ('${bean.name}', '${bean.account}',' ${bean.psw}', '${bean.notes}', '${bean.createTime}')"
            db.execSQL(sql)
            db.setTransactionSuccessful()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            //结束事物
            db.endTransaction()
        }
        return result
    }

    /**
     * 查询指定关键字的用户
     */
    fun searchAccount(keyword: String): ArrayList<AccountBean> {
        val accountList = arrayListOf<AccountBean>()
        //获取游标
        val cursor = db.query(Constant.TABLE_NAME, null, null, null, null, null, null)
        println("cursor.isBeforeFirst = " + cursor.isBeforeFirst)
        println("cursor.isFirst = " + cursor.isFirst)
        ////判断游标是否为空
        if (cursor.moveToFirst()) {
            println("cursor.isBeforeFirst = " + cursor.isBeforeFirst)
            println("cursor.isFirst = " + cursor.isFirst)
            //遍历
            for (i in 0 until cursor.count) {
                //移动游标
                cursor.moveToPosition(i)
                val name = cursor.getString(Constant.NAME_INDEX)
                val account = cursor.getString(Constant.ACCOUNT_INDEX)
                val psw = cursor.getString(Constant.PSW_INDEX)
                val notes = cursor.getString(Constant.NOTES_INDEX)
                val createTime = cursor.getString(Constant.CREATE_TIME_INDEX)
                val id = cursor.getInt(Constant.ID_INDEX)
                //判断是否包含keyWord
                if (keyword.isEmpty()) {
                    accountList.add(AccountBean(name, account, psw, notes, createTime, id))
                } else if ((name.isNotEmpty() && name.contains(keyword))
                        || (account.isNotEmpty() && account.contains(keyword))) {
                    accountList.add(AccountBean(name, account, psw, notes, createTime, id))
                }
            }
        }
        println("cursor.isAfterLast = " + cursor.isAfterLast)
        println("cursor.isLast = " + cursor.isLast)
        println("cursor.isClosed1 = " + cursor.isClosed)
        cursor.close()
        println("cursor.isClosed2 = " + cursor.isClosed)
        return accountList
    }

    /**
     *修改
     */
    fun modifyAccount(bean: AccountBean?): Boolean {
        //修改结果标识
        val result: Boolean
        db.beginTransaction()
        result = try {
            //SQL语句
            val sql_modify = "update ${Constant.TABLE_NAME} " +
                    "set ${Constant.NAME} = '${bean?.name}', ${Constant.ACCOUNT} = '${bean?.account}'," +
                    "${Constant.PSW} = '${bean?.psw}', ${Constant.NOTES} = '${bean?.notes}'" +
                    " where ${Constant.ID} = ${bean!!.id}"
            LogcatUtils.showELog(TAG, sql_modify)
            db.execSQL(sql_modify)
            db.setTransactionSuccessful()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            LogcatUtils.showELog(TAG, e.toString())
            false
        } finally {
            //结束事物
            db.endTransaction()
        }
        return result
    }

    /**
     * 删除
     */
    fun delAccount(bean: AccountBean?): Boolean {
        //删除结果标识
        val result: Boolean
        db.beginTransaction()
        result = try {
            //SQL语句
            val sql_del = "delete from ${Constant.TABLE_NAME} where ${Constant.ID} = ${bean!!.id}"
            db.execSQL(sql_del)
            db.setTransactionSuccessful()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            //结束事物
            db.endTransaction()
        }
        return result
    }

    /**
     * 账户是否存在
     */
    fun isAccountExist(bean: AccountBean): Boolean {
        //删除结果标识
        var result = false
        if (bean.name.isEmpty()) {
            bean.name = ""
        }
        val accountList = searchAccount(bean.name)
        //遍历accountList，如果账户和名称一样，则认为是同一个账户
        accountList.forEach {
            if (bean.name == it.name && bean.account == it.account) {
                result = true
            }
        }
        return result
    }

    //关闭数据库
    fun closeDb() {
        try {
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}