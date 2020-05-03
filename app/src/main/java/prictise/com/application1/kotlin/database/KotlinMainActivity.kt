package prictise.com.application1.kotlin.database

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import prictise.com.application1.R

/**
 * @Author zhisiyi
 * @Date 2019.11.08 11:11
 * @Comment
 */
class KotlinMainActivity : Activity() {

    var dbDao: DbDao? = null

    var mAddDataBT: Button? = null;
    var mFindDataBT: Button? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        initView()
        initValue()
        initListener()
    }

    private fun initValue() {
        dbDao = DbDao(applicationContext)
    }

    private fun initListener() {
        mAddDataBT?.setOnClickListener(View.OnClickListener {
            var accountBean: AccountBean = AccountBean("张三", "1234", "1234",
                    "notes", Constant.getNow()
                    , 1)
            dbDao?.addNewAccount(accountBean)
        })

        mFindDataBT?.setOnClickListener(View.OnClickListener {
            var accountBeans: ArrayList<AccountBean>? = dbDao?.searchAccount("张")
            println(accountBeans)
        })
    }

    private fun initView() {
        mAddDataBT = findViewById(R.id.bt_add_data)
        mFindDataBT = findViewById(R.id.bt_find_data)

    }
}