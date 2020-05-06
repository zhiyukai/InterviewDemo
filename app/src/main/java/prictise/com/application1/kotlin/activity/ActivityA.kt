package prictise.com.application1.kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import prictise.com.application1.BaseActivity
import prictise.com.application1.R

/**
 * @Author zhisiyi
 * @Date 2020.04.10 15:16
 * @Comment
 */
class ActivityA : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        findViewById<View>(R.id.bt_lunch_b).setOnClickListener {
            startActivity(Intent(this, ActivityB::class.java))
        }
    }
}