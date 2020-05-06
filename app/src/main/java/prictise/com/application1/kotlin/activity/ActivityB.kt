package prictise.com.application1.kotlin.activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import prictise.com.application1.BaseActivity
import prictise.com.application1.MainApplication
import prictise.com.application1.R

/**
 * @Author zhisiyi
 * @Date 2020.04.10 15:16
 * @Comment
 */
class ActivityB : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

//        findViewById<View>(R.id.bt_lunch_c).setOnClickListener {
        val intent = Intent(MainApplication.getApplicationInstance(), ActivityC::class.java)
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        MainApplication.getApplicationInstance().startActivity(intent)
//        }
    }
}