package prictise.com.application1.kotlin.okhttpTest

import android.os.Bundle
import android.widget.Button
import okhttp3.OkHttpClient
import okhttp3.Request
import prictise.com.application1.BaseActivity
import prictise.com.application1.R
import prictise.com.application1.utils.LogcatUtils
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * @Author zhisiyi
 * @Date 2020.01.17 07:48
 * @Comment
 */
class OkHttpActivity : BaseActivity() {

    val TAG = OkHttpActivity::class.simpleName
    val threadService = Executors.newCachedThreadPool()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp)
        initView()
        initListen()
    }

    private fun initView() {

    }

    private fun initListen() {
        findViewById<Button>(R.id.bt_okhttp_download_pic).setOnClickListener {
            val url = "http://c.hiphotos.baidu.com/zhidao/pic/item/d009b3de9c82d1587e249850820a19d8bd3e42a9.jpg"
            val okClient = OkHttpClient()
            val request = Request.Builder()
                    .url(url)
                    .build()
            val call = okClient.newCall(request)
            threadService.submit{
                val response = call.execute()
                LogcatUtils.showDLog(TAG, response.toString())
            }
        }

    }
}