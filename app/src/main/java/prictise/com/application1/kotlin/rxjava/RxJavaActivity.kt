package prictise.com.application1.kotlin.rxjava

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import prictise.com.application1.R
import prictise.com.application1.retrofit2.RetrofitManager
import prictise.com.application1.utils.LogcatUtils
import zlc.season.rxdownload4.download
import zlc.season.rxdownload4.manager.file
import zlc.season.rxdownload4.manager.manager
import java.io.*
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * @Author zhisiyi
 * @Date 2019.11.11 14:57
 * @Comment
 */
class RxJavaActivity : Activity() {

    val TAG = RxJavaActivity::class.simpleName;

    var mTestRxjava: Button? = null;
    var mTestMapFlatMap: Button? = null;
    var mAppProgress: ProgressBar? = null
    var mA = 23;
    var mB = 32;
    var cmb: ClipboardManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_rxjava)
        cmb = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        cmb?.primaryClip = ClipData.newPlainText("shareFriendContent", "张三")
        initView()
        initValue()
        initListener()
        initAccessServer()
    }

    private fun initAccessServer() {
        val open = mutableListOf(Keys.POINT_SERVICES_ORDER, Keys.ENABLE_SERVICE_PUT)
        execCmd(open?.toTypedArray(), isRoot = true, isNeedResultMsg = true)
    }

    private fun initValue() {
    }

    private fun initListener() {

        findViewById<Button>(R.id.bt_test_maybe).setOnClickListener() {

        }

        findViewById<Button>(R.id.bt_test_rxdownload).setOnClickListener() {
//            val url = "http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/platform/chatagent_v3.1.15_wechat-server_d8f9c626_release.apk";
            val url = "";
            val taskM = url.manager()
            url.download()
                    .observeOn(AndroidSchedulers.mainThread())
                    .delaySubscription(800, TimeUnit.MILLISECONDS)
                    .subscribeBy(
                            onNext = { progress ->
                                mTestRxjava?.setText(progress.percentStr() + "")
                                LogcatUtils.showDLog(TAG, "progress.downloadSize = " + progress.downloadSize
                                        + "progress.percentStr() = " + progress.percentStr())
                                mAppProgress?.progress = progress.percent().toInt()
                            },
                            onComplete = {
                                LogcatUtils.showDLog(TAG, "下载完成")
                                val file = taskM.file()
                                LogcatUtils.showDLog(TAG, "file = $file")
                            },
                            onError = {
                                LogcatUtils.showDLog(TAG, "error = " + it.localizedMessage)
                            }
                    )

            LogcatUtils.showDLog(TAG, "测试阻塞.")
        }

//        findViewById<Button>(R.id.bt_test_rxdownload).setOnClickListener() {
////            val url = "http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/platform/chatagent_v3.1.19_wechat-server_a6e0d4d0_release.apk";
//            val url = "http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/platform/chatagent_v3.1.15_wechat-server_d8f9c626_release.apk";
//            RxDownload.file(url)
//            Observable.just(1)
//                    .toFlowable(BackpressureStrategy.BUFFER)
//                    .flatMap {
//                        LogcatUtils.showDLog(TAG, "RxDownload.isExists(url) = " + RxDownload.isExists(url))
//                        RxDownload.clearAll()
//                        RxDownload.create(url, true) }
//                    .subscribe({
//                        LogcatUtils.showDLog(TAG, "updateAppVersion url apk download status "
//                                + "it.chunkFlag = "+it.chunkFlag
//                                +"it.downloadSize = " + it.downloadSize
//                                +"it.totalSize = " + it.totalSize
//                                +"it.formatDownloadSize() = " + it.formatDownloadSize()
//                                +"it.formatString() = " + it.formatString()
//                                +"it.formatTotalSize() = "+it.formatTotalSize()
//                                +"it.percent() = " + it.percent());
//                        // updateAppVersion url apk download status zlc.season.rxdownload3.core.g@c05a8dd
//                        when (it) {
//                            is Succeed -> {
//                                LogcatUtils.showELog(TAG, "updateAppVersion url apk download Succeed")
//                            }
//                            is Failed -> {
//                                LogcatUtils.showELog(TAG, "updateAppVersion download apk Failed ${it.throwable}")
////                                clearDownloadFile(url)
//                                RxDownload.delete(url, true)
//                                RxDownload.clear(url)
//                            }
//                            is Deleted -> {
//                                LogcatUtils.showELog(TAG, "updateAppVersion download apk Deleted $it")
////                                clearDownloadFile(url)
//                                RxDownload.delete(url, true)
//                                RxDownload.clear(url)
//                            }
//                        }
//                    }, {
//                        LogcatUtils.showELog(TAG, "updateAppVersion onError  ${it.localizedMessage}")
//                    })
//        }

        findViewById<Button>(R.id.bt_test_file_sort).setOnClickListener() {
            val path = Environment.getExternalStorageDirectory().absolutePath
            val filePath = "$path/tencent/MicroMsg/WeiXin"
            val file = File(filePath)
            if (file.isDirectory) {
                val files = file.listFiles().sorted()

                for (f in files) {
                    Log.i(TAG, "f = ${f.name}")
                }
            }
        }

        findViewById<Button>(R.id.bt_download_pic).setOnClickListener() {

        }
        findViewById<Button>(R.id.bt_test_sample).setOnClickListener() {
            LogcatUtils.showDLog(TAG, "R.id.bt_test_sample click")
            testSample()
        }
        findViewById<Button>(R.id.bt_test_publish_sample).setOnClickListener() {
            Log.i(TAG, "R.id.bt_test_publish_sample click")
            testPublishSample()
            LogcatUtils.showDLog(TAG, "LogcatUtils")
        }
        mTestRxjava?.setOnClickListener(View.OnClickListener {
            //            connect();
//            LogcatUtils.showDLog(TAG, "setOnClickListener")
//            testMethod(testMethod2())
//            startService(Intent(this, AccessServer::class.java))
            LogcatUtils.showDLog(TAG, "clipe = " + cmb?.primaryClip?.getItemAt(0)?.text)
//            getFriendCirclePic()
            clearFriendCirclePic()
//            val accessibleIntent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
//            accessibleIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(accessibleIntent);
        })

        mTestMapFlatMap?.setOnClickListener(View.OnClickListener {
            LogcatUtils.showDLog(TAG, "mTestMapFlatMap")
            Observable.just(System.currentTimeMillis())
                    .filter {
                        true
                    }
                    .subscribeOn(Schedulers.io())
                    .map {
                        LogcatUtils.showDLog(TAG, "map")
                        arrayOf("a", "b", "c")
                    }
                    .filter {
                        LogcatUtils.showDLog(TAG, "it.isEmpty() = " + it.isEmpty())
                        it.isNotEmpty()
                    }
                    .flatMap {
                        return@flatMap Observable.fromIterable(hashMapOf("uploadStates" to it).entries)
                    }
                    .subscribe({
                        var key = it.key;
                        var value = it.value

                        var pair: Pair<String, Array<String>> = it.toPair()
                        var f = pair.first
                        var v = pair.second
                        var size = v.size - 1
                        for (index in 0..size) {
                            LogcatUtils.showDLog(TAG, v[index])
                        }

                        LogcatUtils.showDLog(TAG, "checkSyncUploadState result key = $key")
                        LogcatUtils.showDLog(TAG, "checkSyncUploadState result value = $value")
                        LogcatUtils.showDLog(TAG, "checkSyncUploadState result ${pair.toString()}")
                    }, {
                        //                        LogcatUtils.showDLog(TAG, "checkSyncUploadState error ${it.localizedMessage}")
                    })
        })

    }

    private fun testSample() {
        var interval = Observable.interval(150, TimeUnit.MILLISECONDS);
        interval.subscribe {
            Log.i(TAG, "interval $it")
        }
        interval.sample(1, TimeUnit.SECONDS)
                .subscribe {
                    Log.i(TAG, "debounceMsgPublish start check $it")

                }
    }

    private fun testPublishSample() {
        var publishSubject = PublishSubject.create<Long>()
        publishSubject.onNext(System.currentTimeMillis())
        publishSubject.sample(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.newThread())
                .subscribe(
                        {
                            Log.i(TAG, "debounceMsgPublish start check $it")
                            //聊天消息
//                            if (getMainWeChatDbInfo()?.msg2Server != null) {
//                                getMainWeChatDbInfo()!!.msg2Server?.checkWxMessage2upload()
//                            }
                        }, {
                    Log.i(TAG, "debounceMsgPublish onError ${it.localizedMessage}")
                }
                )

    }

    fun getFriendCirclePic() {
        val file = Environment.getExternalStorageDirectory().toString() + File.separator + "A1"
        LogcatUtils.showDLog(TAG, "files = $file")
        LogcatUtils.showDLog(TAG, "files = ${file[2]}")
        val dir = File(file)
        if (dir.isDirectory) {
            var fileList = dir.list()
            LogcatUtils.showDLog(TAG, "files = ${fileList.toString()}")

            var len = fileList.size - 1
            var result = ""
            if (len > 0) {
                result = fileList[0] + ","
            }
            for (i in 1 until len) {
                result += fileList[i] + ","
            }
            result += fileList[len]
            LogcatUtils.showDLog(TAG, "result = $result")
        }
    }

    fun clearFriendCirclePic() {
        val file = Environment.getExternalStorageDirectory().toString() + File.separator + "A1"
        val dir = File(file)

        if (dir != null && dir.exists() && dir.isDirectory) {
            for (listFile in dir.listFiles()) {
                if (listFile.isFile) {
                    listFile.delete()
                }
            }
        }
    }

    fun t(map: HashMap<String, Array<String>>): Observable<String> {
        return Observable.create<String> { map.toString() }
    }

    private fun testMethod(t2: () -> String) {

        LogcatUtils.showDLog(TAG, "t2=" + t2)
    }

    fun testMethod2(): () -> String {

        return { "eee" }
    }

    private fun initView() {
        mTestRxjava = findViewById(R.id.bt_test_rxjava)
        mTestMapFlatMap = findViewById(R.id.bt_test_map_flatmap)
        mAppProgress = findViewById(R.id.app_progress)
    }

    /**
     * RxJava链式操作
     */
    fun connect() {
//        val myObservable = Observable.create(object : Observable.OnSubscribe<String>() {
//            fun call(sub: Subscriber<in String>) {
//                sub.onNext("Hello, world!")
//                sub.onCompleted()
//            }
//        }
//        )
        var te: String? = "rr"
        var observable = Observable.create<String> {
            it.onNext("Hello, world!")
//            it.onComplete()
        }
        observable.map {
            LogcatUtils.showDLog(TAG, "it = " + it)
            return@map "a"
        }
        var observer = object : Observer<String> {
            override fun onComplete() {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                LogcatUtils.showDLog(TAG, "onComplete()")
            }

            override fun onSubscribe(d: Disposable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                LogcatUtils.showDLog(TAG, "onSubscribe() d = " + d)
            }

            override fun onNext(t: String) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                LogcatUtils.showDLog(TAG, "onNext(t: String) t = " + t)
            }

            override fun onError(e: Throwable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                LogcatUtils.showDLog(TAG, "onError(e: Throwable) e = " + e)
            }
        }
        { LogcatUtils.showELog(TAG, "error : " + te) }

        observable.subscribe(observer)
    }

    fun downLoadPic() {
        RetrofitManager.getAPIService()
    }

    @JvmOverloads
    fun execCmd(commands: Array<String>?, isRoot: Boolean, isNeedResultMsg: Boolean = true): CommandResult {
        var result = -1
        if (commands == null || commands.isEmpty()) {
            return CommandResult(result, "", "")
        }
        Log.i(TAG, "exec cmd is: " + Arrays.toString(commands))
        var process: Process? = null
        var successResult: BufferedReader? = null
        var errorResult: BufferedReader? = null
        var successMsg: StringBuilder? = null
        var errorMsg: StringBuilder? = null
        var os: DataOutputStream? = null
        try {
            process = Runtime.getRuntime().exec(if (isRoot) "su" else "sh")
            os = DataOutputStream(process!!.outputStream)
            for (command in commands) {
                os.write(command.toByteArray())
                os.writeBytes("\n")
                os.flush()
            }
            os.writeBytes("exit\n")
            os.flush()
            //耗时操作，需要注意
            result = process.waitFor()
            if (isNeedResultMsg) {
                successMsg = StringBuilder()
                errorMsg = StringBuilder()
                successResult = BufferedReader(InputStreamReader(process.inputStream, "UTF-8"))
                errorResult = BufferedReader(InputStreamReader(process.errorStream, "UTF-8"))
                var s = successResult.readLine()
                while (s != null) {
                    successMsg.append(s)
                    s = successResult.readLine()
                    if (s != null) successMsg.append("\t")
                }
                s = errorResult.readLine()
                while (s != null) {
                    errorMsg.append(s)
                    s = errorResult.readLine()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            closeIO(os, successResult, errorResult)
            process?.destroy()
        }
        return CommandResult(
                result,
                successMsg?.toString() ?: "",
                errorMsg?.toString() ?: ""
        )
    }

    private fun closeIO(vararg closeables: Closeable?) {
        for (closeable in closeables) {
            if (closeable != null) {
                try {
                    closeable.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

}

class CommandResult(
        /**
         * 结果码
         */
        var result: Int,
        /**
         * 成功信息
         */
        var successMsg: String,
        /**
         * 错误信息
         */
        var errorMsg: String
) {

    override fun toString(): String {
        return "CommandResult(result=$result, successMsg='$successMsg', errorMsg='$errorMsg')"
    }
}
