package prictise.com.application1.kotlin.database

import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.PRIMARY_KEY
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable


/**
 * Description: $Description$
 * Created by yangdongze on 2019-05-22 11:16.
 * Email: yangdongze@sunlands.com
 * Version: $Version$
 */
object DBQueryHelper {
    fun onCreate(db: SQLiteDatabase) {
        db.createTable("Person", true,
                "_id" to INTEGER + PRIMARY_KEY,
                "name" to TEXT,
                "surname" to TEXT,
                "age" to INTEGER)
    }

}