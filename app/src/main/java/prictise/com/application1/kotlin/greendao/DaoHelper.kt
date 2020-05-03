package prictise.com.application1.kotlin.greendao

import android.content.Context

/**
 * @Author zhisiyi
 * @Date 2019.12.04 11:28
 * @Comment
 */
object DaoHelper {

    private var sDaoMaster: DaoMaster? = null
    private var sDaoSession: DaoSession? = null

    fun init(context: Context) {
        getDaoMaster(context)
    }

    fun getDaoMaster(context: Context): DaoMaster {
        if (sDaoMaster == null) {
            sDaoMaster = DaoMaster(WxSqlOpenHelper.getWxSqlOpenHelper(context.applicationContext).writableDatabase)
        }
        sDaoSession = sDaoMaster!!.newSession()
        return sDaoMaster as DaoMaster
    }

    fun getDaoSession(context: Context): DaoSession {
        if (sDaoSession == null) sDaoSession = getDaoMaster(context).newSession()
        return sDaoSession!!
    }
}