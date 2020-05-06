package prictise.com.application1.kotlin.okhttpTest

import android.os.Bundle
import android.widget.Button
import okhttp3.OkHttpClient
import okhttp3.Request
import prictise.com.application1.BaseActivity
import prictise.com.application1.R
import prictise.com.application1.utils.LogcatUtils
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

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
            //            val url = "http://c.hiphotos.baidu.com/zhidao/pic/item/d009b3de9c82d1587e249850820a19d8bd3e42a9.jpg"
            val url = "http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/testjpg/img10M.jpeg"
            val builder = OkHttpClient.Builder()
                    .connectTimeout(6, TimeUnit.MINUTES)
            val okClient = builder.build()

            val len = 9
            for (index in 0 until len) {
                downloadPic(url, okClient, "$index".plus(".jpeg"))
            }
        }

    }

    private fun downloadPic(url: String, okClient: OkHttpClient, picName: String) {
        val request = Request.Builder()
                .url(url)
                .build()
        val call = okClient.newCall(request)
        threadService.submit {
            val response = call.execute()
            LogcatUtils.showDLog(TAG, response.toString())
//                LogcatUtils.showDLog(TAG, "response.body()?.string() = "+response.body()?.string())
            val bytes = response.body()?.bytes()
            val path = "/sdcard/Download/$picName"
            val stream = FileOutputStream(File(path))
            stream.write(bytes)
            stream.flush()
            stream.close()
            LogcatUtils.showDLog(TAG, "$picName 下载完成")
        }
    }
}