package prictise.com.application1.kotlin.greendao

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import prictise.com.application1.R
import prictise.com.application1.utils.LogcatUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author zhisiyi
 * @Date 2019.12.04 11:21
 * @Comment
 */
class GreenDaoTestActivity : Activity() {
    val TAG = GreenDaoTestActivity::class.simpleName
    private var incre: Int? = 0
    private var etId: EditText? = null
    private var etTimeId: EditText? = null
    private var etUpdate: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_greendao)
        initView()
    }

    private fun initView() {
        etId = findViewById<EditText>(R.id.et_id)
        etTimeId = findViewById(R.id.et_time_id)
        etUpdate = findViewById(R.id.et_update)

        var testEntityDao = DaoHelper.getDaoSession(this).testEntityDao

        findViewById<Button>(R.id.bt_find_other_thread).setOnClickListener {
            object : Thread() {
                override fun run() {
                    var eList = testEntityDao.queryBuilder().build().list()
                    LogcatUtils.showDLog(TAG, "find eList = $eList")
                }
            }.start()
        }


        findViewById<Button>(R.id.bt_add).setOnClickListener {
            LogcatUtils.showDLog(TAG, "bt_add")
//            find()
            testEntityDao = DaoHelper.getDaoSession(this).testEntityDao
            for (i in 0 until 10) {

                var testEntity = TestEntity()
                testEntity?.apply {
                    stuWechatId = "id$incre$i"
                    var date = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())
                    time = date
                    text = "id$incre$i$date"
                }

                incre = incre?.plus(1)

                LogcatUtils.showDLog(TAG, "testEntity = $testEntity")
                testEntityDao.insertOrReplace(testEntity)
            }

//            var list = mutableListOf<TestEntity>()
//
//            var testEntity1 = TestEntity()
//
//            testEntity1.stuWechatId = "id111"
//            testEntity1.time = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())
//            testEntity1.timeMill = System.currentTimeMillis()
//
//            var testEntity2 = TestEntity()
//            testEntity2.stuWechatId = "id222"
//            testEntity2.time = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())
//            testEntity2.timeMill = System.currentTimeMillis()
//
//            var testEntity3 = TestEntity()
//            testEntity3.stuWechatId = "id333"
//            testEntity3.time = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())
//            testEntity3.timeMill = System.currentTimeMillis()
//
//            list.add(testEntity1)
//            list.add(testEntity2)
//            list.add(testEntity3)

//            DaoHelper.getDaoSession(this).testEntityDao.insertOrReplaceInTx(list)
//            DaoHelper.getDaoSession(this).testEntityDao.insertOrReplace(null)

//            find()
        }

        findViewById<Button>(R.id.bt_update).setOnClickListener {
            LogcatUtils.showDLog(TAG, "bt_update")
//            find()
            var testEntity = DaoHelper.getDaoSession(this).testEntityDao.queryBuilder()
                    .where(TestEntityDao.Properties.StuWechatId.eq(etUpdate?.text))
                    .build().uniqueOrThrow()
            testEntity.addType = 3
            var date = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())
            testEntity.time = date
            testEntity.text = date+testEntity.stuWechatId
            DaoHelper.getDaoSession(this).testEntityDao.update(testEntity)
//            find()
        }
        findViewById<Button>(R.id.bt_find).setOnClickListener {
            LogcatUtils.showDLog(TAG, "bt_find")
            val id = etId?.text.toString()
            findById(id)
        }

        findViewById<Button>(R.id.bt_delete_all).setOnClickListener {
            DaoHelper.getDaoSession(this).testEntityDao.deleteAll()
        }
        findViewById<Button>(R.id.bt_delete).setOnClickListener {
            LogcatUtils.showDLog(TAG, "bt_delete")
            find()
            LogcatUtils.showDLog(TAG, "delete before")
            var time: Long = etTimeId?.text.toString().toLong()
            LogcatUtils.showDLog(TAG, "delete before time = $time")
            var offset3dayTimeMillis = 1575456089700
            DaoHelper.getDaoSession(this).testEntityDao.queryBuilder()
                    .where(TestEntityDao.Properties.TimeMill.lt(time))
                    .buildDelete().executeDeleteWithoutDetachingEntities()
            LogcatUtils.showDLog(TAG, "delete after")
            find()
        }

    }

    private fun find() {
        var eList = DaoHelper.getDaoSession(this).testEntityDao.queryBuilder().build().list()
        LogcatUtils.showDLog(TAG, "find eList = $eList")
    }

    private fun findById(id: String) {
        LogcatUtils.showDLog(TAG, "findById id = $id")
        var te = DaoHelper.getDaoSession(this).testEntityDao.queryBuilder()
                .where(TestEntityDao.Properties.StuWechatId.eq(id))
                .build().unique()

        LogcatUtils.showDLog(TAG, "findById te = $te")

    }
}