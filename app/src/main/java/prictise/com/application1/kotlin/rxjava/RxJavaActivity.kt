package prictise.com.application1.kotlin.rxjava

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.KeyguardManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.os.*
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ProgressBar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import prictise.com.application1.R
import prictise.com.application1.designMode.Build.PersonNormal
import prictise.com.application1.interfaceCus.PersonInterface
import prictise.com.application1.kotlin.activity.ActivityA
import prictise.com.application1.kotlin.bean.GsonSerializeBean
import prictise.com.application1.retrofit2.RetrofitManager
import prictise.com.application1.utils.FileParseUtil
import prictise.com.application1.utils.FileParseUtil.parseGson
import prictise.com.application1.utils.LogcatUtils
import prictise.com.application1.utils.ToastUtil
import zlc.season.rxdownload4.download
import zlc.season.rxdownload4.manager.file
import zlc.season.rxdownload4.manager.manager
import java.io.Closeable
import java.io.File
import java.io.IOException
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


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
    var mTestData: TestData? = null

    var mCountDownTimer: CountDownTimer? = null
    private val mTotalTime = 10 * 1000.toLong()
    private val mInterval: Long = 1000

    private var mActivityManager: ActivityManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_rxjava)
        cmb = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        cmb?.primaryClip = ClipData.newPlainText("shareFriendContent", "张三")

        mActivityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        initView()
        initValue()
        initListener()
        initAccessServer()
    }

    private fun initAccessServer() {
        val open = mutableListOf(Keys.POINT_SERVICES_ORDER, Keys.ENABLE_SERVICE_PUT)
    }

    private fun initValue() {


        /**
         * CountDownTimer timer = new CountDownTimer(3000, 1000)中，
         * 第一个参数表示总时间，第二个参数表示间隔时间。
         * 意思就是每隔一秒会回调一次方法onTick，然后1秒之后会回调onFinish方法。
         */
        mCountDownTimer = object : CountDownTimer(mTotalTime, mInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val time = millisUntilFinished / 1000
                if (time == 0L) {
                    // 倒计时结束
                    LogcatUtils.showDLog(TAG, "倒计时结束")
                } else {
                    LogcatUtils.showDLog(TAG, "start")
                }
            }

            override fun onFinish() {}
        }
        //调用 CountDownTimer 对象的 start() 方法开始倒计时，也不涉及到线程处理
//        mCountDownTimer?.start()
    }

    fun getTestData(): TestData? {
        return mTestData
    }

    private fun string(): String {
        try {
            LogcatUtils.showDLog(TAG, "bt_test_finally string try")
            return "a"
        } finally {
            LogcatUtils.showDLog(TAG, "bt_test_finally string finally")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mCountDownTimer?.cancel()
    }

    @RequiresApi(Build.VERSION_CODES.ECLAIR_MR1)
    private fun initListener() {

        findViewById<Button>(R.id.bt_test_process).setOnClickListener {
            // 通过调用ActivityManager的getRunningAppProcesses()方法获得系统里所有正在运行的进程

            // 通过调用ActivityManager的getRunningAppProcesses()方法获得系统里所有正在运行的进程
            val appProcessList: List<ActivityManager.RunningAppProcessInfo> = mActivityManager!!.runningAppProcesses
//                    .getRunningAppProcesses()

            for (appProcessInfo in appProcessList) {

                // 进程ID号
                val pid: Int = appProcessInfo.pid
                // 用户ID 类似于Linux的权限不同，ID也就不同 比如 root等
                val uid: Int = appProcessInfo.uid
                // 进程名，默认是包名或者由属性android：process=""指定
                val processName: String = appProcessInfo.processName

                LogcatUtils.showDLog(TAG, "pid = $pid")
                LogcatUtils.showDLog(TAG, "uid = $uid")
                LogcatUtils.showDLog(TAG, "processName = $processName")
                // 获得该进程占用的内存
                // 获得该进程占用的内存
//                val myMempid = intArrayOf(pid)
                // 此MemoryInfo位于android.os.Debug.MemoryInfo包中，用来统计进程的内存信息
                // 此MemoryInfo位于android.os.Debug.MemoryInfo包中，用来统计进程的内存信息
//                val memoryInfo: Array<Debug.MemoryInfo> = mActivityManager
//                        .getProcessMemoryInfo(myMempid)
                // 获取进程占内存用信息 kb单位
                // 获取进程占内存用信息 kb单位
//                val memSize = memoryInfo[0].dalvikPrivateDirty
                // 构造一个ProcessInfo对象
                // 构造一个ProcessInfo对象
//                val processInfo = ProcessInfo()
                // 设置processinfo信息
                // 设置processinfo信息
//                processInfo.setPid(pid)
//                processInfo.setUid(uid)
//                processInfo.setMemSize(memSize)
//                processInfo.setProcessName(processName)
                // 添加到processInfoList中
                // 添加到processInfoList中
//                processInfoList.add(processInfo)
            }
        }

        findViewById<Button>(R.id.bt_test_rx_java).setOnClickListener {
            val array = arrayListOf<Int>()
            for (index in 0..6) {
                array.add(index)
            }
            Observable.fromIterable(array.toList())
                    .map {
                        LogcatUtils.showDLog(TAG, "it = $it")
                    }
//                    .subscribe({
//                        LogcatUtils.showDLog(TAG,"subscribe1 = $it")
//                    },{
//                        LogcatUtils.showDLog(TAG,"subscribe2 = $it")
//                    })
        }

        findViewById<Button>(R.id.bt_dao_ji_shi).setOnClickListener {
            // 让倒计时开始
            LogcatUtils.showDLog(TAG, "倒计时打开")
            mCountDownTimer?.start()
        }

        findViewById<Button>(R.id.bt_test_iterator).setOnClickListener {
            /**
             *  var result: Int,
            /**
             * 成功信息
            */
            var successMsg: String,
            /**
             * 错误信息
            */
            var errorMsg: String
             */
            val applyEntity = "[{\"result\":1,\"successMsg\":\"成功信息\",\"errorMsg\":\"错误信息\"}]"

            val target1 = parseGsonToS(applyEntity,
                    ArrayList<CommandResult>()::class.java)

            LogcatUtils.showDLog(TAG, "bt_test_iterator target1 = $target1")

            val target = ArrayList<String>()

            target.add("张三")
//            target.add("李四")
//            target.add("王五")
//            target.add("张六")

            val iterator = target.iterator()
            var count = 0
            while (iterator.hasNext()) {
                val next = iterator.next()
                LogcatUtils.showDLog(TAG, "bt_test_iterator next = $next")
                iterator.remove()
                Thread.sleep(200)
                if (count == 1) {
                    break
                }
                count++
            }

            LogcatUtils.showDLog(TAG, "bt_test_iterator target = $target")
        }

        findViewById<Button>(R.id.bt_test_finally).setOnClickListener {
            LogcatUtils.showDLog(TAG, "bt_test_finally ${string()}")
        }
        findViewById<Button>(R.id.bt_test_string).setOnClickListener {
            val url = "http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/video/test_cat_1.mp4"
            val name = url!!.substring(url.lastIndexOf("/") + 1)
            val subUrl = url!!.substring(0, url.lastIndexOf("/"))
            val resultString = subUrl.replace(":", "-").replace("/", "_")

            LogcatUtils.showDLog(TAG, "bt_test_string name is $name")
            LogcatUtils.showDLog(TAG, "bt_test_string subUrl is $subUrl")
            LogcatUtils.showDLog(TAG, "bt_test_string resultString is $resultString")
        }
        findViewById<Button>(R.id.bt_test_not_empty).setOnClickListener {
            var chatRoomIds: List<String>? = null
//            LogcatUtils.showDLog(TAG, "bt_test_xie_cheng chatRoomIds is ${chatRoomIds!!.isNotEmpty()}")
            LogcatUtils.showDLog(TAG, "bt_test_xie_cheng chatRoomIds isNullOrEmpty() is ${chatRoomIds!!.isNullOrEmpty()}")
            LogcatUtils.showDLog(TAG, "bt_test_xie_cheng chatRoomIds !isNullOrEmpty() is ${!chatRoomIds!!.isNullOrEmpty()}")

        }

        findViewById<Button>(R.id.bt_test_list_to_array).setOnClickListener {
            val testListToArray = ArrayList<String>()
            testListToArray.add("张三")
            testListToArray.add("李四")
            testListToArray.add("王五")
            LogcatUtils.showDLog(TAG, "bt_test_xie_cheng testListToArray is ${testListToArray}")
            LogcatUtils.showDLog(TAG, "bt_test_xie_cheng testListToArray.toArray() is ${testListToArray.toArray()}")
            LogcatUtils.showDLog(TAG, "bt_test_xie_cheng testListToArray.toTypedArray() is ${testListToArray.toTypedArray()}")

        }

        findViewById<Button>(R.id.bt_test_data_dir).setOnClickListener {
            val applicationInfo: ApplicationInfo = getApplicationInfo()
            LogcatUtils.showDLog(TAG, "bt_test_data_dir applicationInfo.dataDir is ${applicationInfo.dataDir}")
        }
        findViewById<Button>(R.id.bt_test_replace).setOnClickListener {
            var msg = "1.0-AccountPlugin\\u0026^^\\u0026getCurrentUser2 0x01"
            LogcatUtils.showDLog(TAG, "bt_test_replace msg1 is $msg")
            if (msg.contains("\\u0026^^\\u0026")) {
                msg = msg.replace("\\u0026^^\\u0026", "&^^&")
            }
            LogcatUtils.showDLog(TAG, "bt_test_replace msg2 is $msg")
        }
        findViewById<Button>(R.id.bt_test_unlock_screen).setOnClickListener() {
            Observable.interval(10, 10, TimeUnit.SECONDS)
                    .subscribe({
                        LogcatUtils.showDLog(TAG, "bt_test_unlock_screen thread is ${Thread.currentThread()}")
//                        runOnUiThread {
//                            testUnLockScreen()
//                        }
                        checkIsWeekUpScreen()
                        LogcatUtils.showDLog(TAG, "bt_test_unlock_screen #第 $it 次")
                    }, {
                        LogcatUtils.showDLog(TAG, "bt_test_unlock_screen e : ${it.localizedMessage}")
                    })
        }
        findViewById<Button>(R.id.bt_test_rxjava_return_create).setOnClickListener() {
            Observable.create<String> {

                if (checkIsChanceHasBeenFinished("chance", it)) {
                    //恢复可操作状态
//                    setIsNotOperating()
                    LogcatUtils.showDLog(TAG, "bt_test_rxjava_return_create checkIsChanceHasBeenFinished")
                    return@create
                }
            }
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        LogcatUtils.showDLog(TAG, "bt_test_rxjava_return_create it next $it")
                    }, {
                        LogcatUtils.showDLog(TAG, "bt_test_rxjava_return_create error is ${it.localizedMessage}")
                    })
        }

        findViewById<Button>(R.id.bt_test_version).setOnClickListener() {
            val wxInfo = this.packageManager.getPackageInfo("com.tencent.mm", 0)
            LogcatUtils.showDLog(TAG, "bt_test_version wxInfo.versionName = ${wxInfo.versionName}")
            LogcatUtils.showDLog(TAG, "bt_test_version wxInfo.longVersionCode = ${wxInfo.longVersionCode}")
            LogcatUtils.showDLog(TAG, "bt_test_version wxInfo.longVersionCode = ${wxInfo.versionCode}")
            val packageInfos = this.packageManager.getInstalledPackages(0)
            var k = 0
            val builder = StringBuilder()
            for (i in packageInfos.indices) {
                val result = packageInfos[i]
                LogcatUtils.showDLog(TAG, "bt_test_version result = ${result.toString()}")
            }
        }

        findViewById<Button>(R.id.bt_test_builder).setOnClickListener() {
            val labelMap = HashMap<String, String>()
            labelMap["0"] = "tag0"
            labelMap["1"] = "tag1"
            labelMap["2"] = "tag2"
            labelMap["3"] = "tag3"
            labelMap["4"] = "tag4"

            val result = StringBuilder()
            val labelIds = "1,2,3"
            val labelIdsArray = labelIds.split(",")
            for (labelId in labelIdsArray) {
                labelMap[labelId]?.let {
                    result.append(it)
                    result.append(",")
                }
            }
            LogcatUtils.showDLog(TAG, "bt_test_builder result = ${result.toString()}")
            if (result.length > 1) {
                result.delete(result.length - 1, result.length)
            }
            LogcatUtils.showDLog(TAG, "bt_test_builder result = ${result.toString()}")
        }
        findViewById<Button>(R.id.bt_test_do_on_complete).setOnClickListener() {
            Observable.create<List<String>> {
                LogcatUtils.showDLog(TAG, "bt_test_do_on_complete")
                val data = arrayListOf<String>("aa", "cc")
                if (data.isNotEmpty()) {
                    it.onNext(data)
                }
                it.onComplete()
            }.subscribeOn(Schedulers.io())
                    .flatMap { Observable.fromIterable(it) }
                    .filter {
                        1 / 0
                        LogcatUtils.showDLog(TAG, "bt_test_do_on_complete filter")
                        it.isNotEmpty()
                    }
                    .doOnComplete {
                        LogcatUtils.showDLog(TAG, "bt_test_do_on_complete doOnComplete")
                        return@doOnComplete
                    }.subscribe({
//                        try {
                        1 / 0
//                        } catch (e: java.lang.Exception) {
//                            LogcatUtils.showELog(TAG, "bt_test_do_on_complete subscribe e is ${e.localizedMessage}")
//                        }
                        LogcatUtils.showDLog(TAG, "bt_test_do_on_complete subscribe")
                    }, {
                        LogcatUtils.showELog(TAG, "bt_test_do_on_complete e is ${it.localizedMessage}")
                    })
        }
        findViewById<Button>(R.id.bt_test_phone_status).setOnClickListener() {
            val androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
            LogcatUtils.showDLog(TAG, "bt_test_gson_serializedname androidId = $androidId")
            val wifiOn = Settings.Secure.getInt(getContentResolver(), Settings.Secure.WIFI_ON)
            LogcatUtils.showDLog(TAG, "bt_test_gson_serializedname wifiOn = $wifiOn")

        }

        findViewById<Button>(R.id.bt_test_gson_serializedname).setOnClickListener() {
            val label = "{\"a\":\"z1\"}"
            val label2 = "{\"b\":\"z2\"}"
            val label3 = "{\"d\":\"z3\"}"
            val label4 = "{\"name\":\"name4\", 'name1':'name1'," +
                    "'a':'a','desc1':'desc1'}"
            val label5 = "{\"name\":\"name4\",'desc1':'desc1'}"
            val label6 = "{\"name1\":\"name6\",'desc1':'desc6'}"

            val gsonSerializeBean = GsonSerializeBean()
            gsonSerializeBean.name = "序列化"
            gsonSerializeBean.desc = "desc"

            val seri = Gson().toJson(gsonSerializeBean)
            LogcatUtils.showDLog(TAG, "bt_test_gson_serializedname seri = $seri")

            val serig1 = Gson().fromJson(seri, GsonSerializeBean::class.java)
            val g1 = Gson().fromJson(label, GsonSerializeBean::class.java)
            val g2 = Gson().fromJson(label2, GsonSerializeBean::class.java)
            val g3 = Gson().fromJson(label3, GsonSerializeBean::class.java)
            val g4 = Gson().fromJson(label4, GsonSerializeBean::class.java)
            val g5 = Gson().fromJson(label5, GsonSerializeBean::class.java)
            val g6 = Gson().fromJson(label6, GsonSerializeBean::class.java)
            LogcatUtils.showDLog(TAG, "bt_test_gson_serializedname serig1 = $serig1")
            LogcatUtils.showDLog(TAG, "bt_test_gson_serializedname g1 = ${g1.name};${g1.desc}")
            LogcatUtils.showDLog(TAG, "bt_test_gson_serializedname g2 = ${g2.name};${g2.desc}")
            LogcatUtils.showDLog(TAG, "bt_test_gson_serializedname g3 = ${g3.name}; ${g3.desc}")
            LogcatUtils.showDLog(TAG, "bt_test_gson_serializedname g4 = ${g4.name} ; ${g4.desc}")
            LogcatUtils.showDLog(TAG, "bt_test_gson_serializedname g5 = ${g5.name} ; ${g5.desc}")
            LogcatUtils.showDLog(TAG, "bt_test_gson_serializedname g6 = ${g6.name} ; ${g6.desc}")
        }
        findViewById<Button>(R.id.bt_test_observable_interval).setOnClickListener() {
            LogcatUtils.showDLog(TAG, "bt_test_observable_interval")
            val a = "ss"
            Observable.interval(5, 10, TimeUnit.SECONDS)
                    .filter { a != null }
                    .subscribe({
                        LogcatUtils.showDLog(TAG, "测试会不会延迟.")
                    }, {

                    })

            var count = 1
            Observable.interval(15, 20, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .map {
                        LogcatUtils.showDLog(TAG, "map 1")
                        if (count % 4 == 0) {
//                            throw Exception("空指针异常.")
//                            try {
//                                var a = 1 / 0
//                            } catch (e: java.lang.Exception) {
//                                LogcatUtils.showDLog(TAG, "1 e : ${e.localizedMessage}")
//                            }
                        }
                    }
                    .map {
                        if (count == 3) {
                            LogcatUtils.showDLog(TAG, "count == 3")
                            try {
                                var a = 1 / 0
                            } catch (e: java.lang.Exception) {
                                LogcatUtils.showDLog(TAG, "count == 3 e : ${e.localizedMessage}")
                            }
                        }
                        val aList = mutableListOf<String>()
                        aList.add("a")
                        aList.add("b")
                        LogcatUtils.showDLog(TAG, "aList count == 3")
                        aList
                    }
                    .doOnNext {
                        count++
//                        if (count == 2) {
//                            LogcatUtils.showDLog(TAG, "count == 2")
//                            1 / 0
//                        }
                    }
                    .flatMapIterable {
                        LogcatUtils.showDLog(TAG, "flatMapIterable")
                        try {
                            val aInt = 1 / 0
                        } catch (e: java.lang.Exception) {
                            LogcatUtils.showDLog(TAG, "flatMapIterable e : ${e.printStackTrace()}")
                            Observable.just(1)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe {
                                        ToastUtil.showShort("${e.printStackTrace()}")
                                    }
                        }
                        LogcatUtils.showDLog(TAG, "flatMapIterable1")
                        it
                    }
                    .retry()
                    .subscribe({
                        //这里抛异常，会停止轮询？ 会
                        try {
                            1 / 0
                        } catch (e: Exception) {
                            LogcatUtils.showDLog(TAG, "subscribe e : ${e.localizedMessage}")
                        }
                        LogcatUtils.showDLog(TAG, "checkWXDBStates success")
                    }, {
//                        intervalWXDBDisposable?.dispose()
                        LogcatUtils.showDLog(TAG, "checkWXDBStates error ${it.localizedMessage}")
                    })
        }
        findViewById<Button>(R.id.bt_test_delete_file).setOnClickListener() {
            val target1 = "/sdcard/contact/123"
            val target2 = "/sdcard/contact/1587647470491"
            Log.d(TAG, "bt_test_delete_file target1 : ${FileParseUtil.deleteFileFromDisk(target1)}")
            Log.d(TAG, "bt_test_delete_file target2 : ${FileParseUtil.deleteFileFromDisk(target2)}")
        }
        findViewById<Button>(R.id.bt_test_gson).setOnClickListener() {
            try {
                val target = "/sdcard/contact/123"
                val filePath: String = "/sdcard/contact/123"
                val targetResult = Gson().toJson(target)

//                val gsonType = object : TypeToken<String>() {}.type
//                val filePathName = Gson().fromJson<String>(targetResult, gsonType)
                val ss = parseGson(targetResult, String::class.java)


                Log.d(TAG, "bt_test_gson target : $target")
                Log.d(TAG, "bt_test_gson targetResult : $targetResult")
                val isGson1 = FileParseUtil.isGoodJson(target)
                val isGson = FileParseUtil.isGoodJson(targetResult)
                Log.d(TAG, "bt_test_gson isGson1 : $isGson1")
                Log.d(TAG, "bt_test_gson isGson : $isGson")
                Log.d(TAG, "bt_test_gson filePathName : $ss")
            } catch (e: java.lang.Exception) {
                Log.e(TAG, "bt_test_gson e : ${e.localizedMessage}")

            }
        }
        findViewById<Button>(R.id.bt_test_indexof).setOnClickListener() {
            //            var fmsgContent = "<msg fromusername=\"wxid_7o2wtsl88dwb21\" encryptusername=\"v1_8d783947fd86399050b5f70e94517bd8156882e1f7f3059df7996b2fda90e871ba2c9ce30889ef2757567c001b4f0201@stranger\" fromnickname=\"飍\" content=\"测试自动同意\" fullpy=\"xiu\" shortpy=\"X\" imagestatus=\"3\" scene=\"30\" country=\"CN\" province=\"Beijing\" city=\"\" sign=\"\" percard=\"1\" sex=\"1\" alias=\"zsj1114082241\" weibo=\"\" albumflag=\"3\" albumstyle=\"0\" albumbgimgid=\"\" snsflag=\"273\" snsbgimgid=\"http://mmsns.qpic.cn/mmsns/4376ae1e0cf0ccced233def9ad1560d0dec29d64941ab85a82862ef82b9c10454a37afc832cee823b8b458de95149314823209ded595f8f1/0\" snsbgobjectid=\"13226068409436278842\" mhash=\"a27d67ed4b137057b36e423ebbb7591d\" mfullhash=\"a27d67ed4b137057b36e423ebbb7591d\" bigheadimgurl=\"http://wx.qlogo.cn/mmhead/ver_1/NKK6uOCicuBlFSbE1eRPpNc3A5ibUOUTQFicjDZmUxNWXydyCx9rAOicE4OunTFicXXlPTPquVu2LUVaWmm1QVHscCjxtPp2bBtPSx7g80ibK1NsU/0\" smallheadimgurl=\"http://wx.qlogo.cn/mmhead/ver_1/NKK6uOCicuBlFSbE1eRPpNc3A5ibUOUTQFicjDZmUxNWXydyCx9rAOicE4OunTFicXXlPTPquVu2LUVaWmm1QVHscCjxtPp2bBtPSx7g80ibK1NsU/96\" ticket=\"v4_000b708f0b040000010000000000fd2a27c1295d09f7db9faffb9e5e1000000050ded0b020927e3c97896a09d47e6e9e83ed214ad75c3ec5f0eaa4eab69e2639c93f71f918bcf3d68cfe89649ed56bdd55aff656cb225251f0011f636cd96aadc500c607df2af41684d22ad1d534f2de3a8c313b0df2185b8caa6d8d26b3a1061e73076e55c0cfe6de67b3da0f2e818439082105e6e06b8643@stranger\" opcode=\"2\" googlecontact=\"\" qrticket=\"\" chatroomusername=\"\" sourceusername=\"\" sourcenickname=\"\" sharecardusername=\"\" sharecardnickname=\"\" cardversion=\"\"><brandlist count=\"0\" ver=\"712710816\"></brandlist></msg>"
            var fmsgContent = "<msg fromusername=\"wxid_7o2wtsl88dwb21\" " +
                    "encryptusername=\"v1_8d783947fd86399050b5f70e94517bd8156882e1f7f3059df7996b2fda90e871ba2c9ce30889ef2757567c001b4f0201@stranger\" " +
                    "fromnickname=\"飍\" " +
                    "content=\"测试自动同意3\" " +
                    "fullpy=\"xiu\" " +
                    "shortpy=\"X\" " +
                    "imagestatus=\"3\" " +
                    "scene=\"30\" " +
                    "country=\"CN\" " +
                    "province=\"Beijing\" " +
                    "city=\"\" " +
                    "sign=\"\" " +
                    "percard=\"1\" " +
                    "sex=\"1\" " +
                    "alias=\"zsj1114082241\" " +
                    "weibo=\"\" " +
                    "albumflag=\"3\" " +
                    "albumstyle=\"0\" " +
                    "albumbgimgid=\"\" " +
                    "snsflag=\"273\" " +
                    "snsbgimgid=\"http://mmsns.qpic.cn/mmsns/4376ae1e0cf0ccced233def9ad1560d0dec29d64941ab85a82862ef82b9c10454a37afc832cee823b8b458de95149314823209ded595f8f1/0\" " +
                    "snsbgobjectid=\"13226068409436278842\" " +
                    "mhash=\"a27d67ed4b137057b36e423ebbb7591d\" " +
                    "mfullhash=\"a27d67ed4b137057b36e423ebbb7591d\" " +
                    "bigheadimgurl=\"http://wx.qlogo.cn/mmhead/ver_1/NKK6uOCicuBlFSbE1eRPpNc3A5ibUOUTQFicjDZmUxNWXydyCx9rAOicE4OunTFicXXlPTPquVu2LUVaWmm1QVHscCjxtPp2bBtPSx7g80ibK1NsU/0\" " +
                    "smallheadimgurl=\"http://wx.qlogo.cn/mmhead/ver_1/NKK6uOCicuBlFSbE1eRPpNc3A5ibUOUTQFicjDZmUxNWXydyCx9rAOicE4OunTFicXXlPTPquVu2LUVaWmm1QVHscCjxtPp2bBtPSx7g80ibK1NsU/96\" " +
                    "ticket=\"v4_000b708f0b040000010000000000c3672a333cb8a7abcab295d39f5e1000000050ded0b020927e3c97896a09d47e6e9e3e9ffa04d42b45244ff5851a847ed77f67fb1f05e7546d33d3495fe4f905576daa557f405397cef29a7574af3426d99087e705d137fb906a79ba33240e81c3db832854c6708ae8aa560700196a09cef1fc33ca19e32fca1f277143a0648ac9b991a40d695e59f8c6cf@stranger\" " +
                    "opcode=\"2\" " +
                    "googlecontact=\"\" " +
                    "qrticket=\"\" " +
                    "chatroomusername=\"\" " +
                    "sourceusername=\"\" " +
                    "sourcenickname=\"\" " +
                    "sharecardusername=\"\" " +
                    "sharecardnickname=\"\" " +
                    "cardversion=\"\">" +
                    "<brandlist count=\"0\" ver=\"712710816\"></brandlist>" +
                    "</msg>"
            try {
//                fmsgContent = fmsgContent?.split("ticket=\"")?.get(1)
//                Log.d(TAG, "bt_test_indexof fmsgContent : $fmsgContent")
//                val startIndex = fmsgContent?.indexOf("v4_") ?: 0
//                Log.d(TAG, "bt_test_indexof startIndex : $startIndex")
//                val endIndex = fmsgContent?.indexOf("\"") ?: 0
//                Log.d(TAG, "bt_test_indexof endIndex : $endIndex")
//                val param2Value = fmsgContent?.substring(startIndex, endIndex)
//                Log.d(TAG, "bt_test_indexof param2Value : $param2Value")
//                Log.d(TAG, "bt_test_indexof param2Value endsWith : ${param2Value.endsWith("@stranger")}")

                val ticket = FileParseUtil.parseMsgContentTicket(fmsgContent)

                Log.d(TAG, "bt_test_indexof ticket : $ticket")

            } catch (e: java.lang.Exception) {
                Log.e(TAG, "bt_test_indexof e : ${e.localizedMessage}")
            }

            val imgPath = "THUMBNAIL_DIRPATH://th_590fbf192527b0b8c8588bde805efc88"
            val index = imgPath.indexOf("th_")

            val folder1 = imgPath.substring(index + 3, index + 5)
            val folder2 = imgPath.substring(index + 5, index + 7)

            Log.e(TAG, "bt_test_indexof folder1 : $folder1")
            Log.e(TAG, "bt_test_indexof folder2 : $folder2")
        }
        findViewById<Button>(R.id.bt_test_new_task).setOnClickListener() {
            startActivity(Intent(this, ActivityA::class.java))
        }
        findViewById<Button>(R.id.bt_test_next).setOnClickListener() {

            Observable.just(1, 2, 3)
                    .delaySubscription(5, TimeUnit.SECONDS)
                    .subscribe {
                        Log.e("RX", "$it")
                    }

            val sTest = "Test next"
            Observable.create<String> {
                LogcatUtils.showDLog(TAG, "thread name = ${Thread.currentThread().name}")
//                it.onNext(sTest)
                it.onComplete()
            }.onTerminateDetach()
                    .observeOn(Schedulers.io())
                    .doOnComplete {
                        var doNextTest = "doNext $it"
                        LogcatUtils.showDLog(TAG, "thread name doOnNext = ${Thread.currentThread().name}")
                        LogcatUtils.showDLog(TAG, "doOnComplete = $doNextTest")
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        LogcatUtils.showDLog(TAG, "next = $it")
                        LogcatUtils.showDLog(TAG, "thread name next = ${Thread.currentThread().name}")
                    }, {
                        LogcatUtils.showDLog(TAG, "error = ${it.localizedMessage}")
                    }, {
                        LogcatUtils.showDLog(TAG, "complete = $it")
                        LogcatUtils.showDLog(TAG, "thread name complete = ${Thread.currentThread().name}")
                    })
        }
        findViewById<Button>(R.id.bt_test_for_exception).setOnClickListener() {
            val al = arrayListOf(4)
            al.add(0)
            al.add(1)
            al.add(2)
            al.add(3)
            for (a in al) {
                try {
                    if (a == 2) {
                        a / 0
                    }
                } catch (e: Exception) {
                    LogcatUtils.showELog(TAG, "e = $e")
                }
                LogcatUtils.showDLog(TAG, "a = $a")
            }
        }
        findViewById<Button>(R.id.bt_test_array_blocking_queue).setOnClickListener() {
            val a = ArrayBlockingQueue<String>(30)
            a.add("1")
            a.add("2")
            a.add("3")
            a.add("4")
            LogcatUtils.showDLog(TAG, "a = $a")
            val v1 = a.peek()
            LogcatUtils.showDLog(TAG, "a.peek() v1 = $v1")
            val v = a.poll()
            LogcatUtils.showDLog(TAG, "a.poll() a = $a")
        }
        findViewById<Button>(R.id.bt_test_object_empty).setOnClickListener() {
            var n = getTestData()?.name
            LogcatUtils.showDLog(TAG, "n = null is ${n == null}")
            LogcatUtils.showDLog(TAG, "n?.isEmpty() is ${n?.isEmpty()}")
//                    ?:throw RuntimeException("getChanceFromQueueSafe context = null")
        }

        findViewById<Button>(R.id.bt_test_list_empty).setOnClickListener() {
            LogcatUtils.showDLog(TAG, "bt_test_list_empty")
            var tags1 = listOf("", "")
            var tags2 = listOf(" ", " ")

            for (tag1 in tags1) {
                if (tag1.isNotEmpty()) {
                    LogcatUtils.showDLog(TAG, "tag1 isNotEmpty = $tag1")
                }
            }

            for (tag2 in tags2) {
                if (tag2.isNotEmpty()) {
                    LogcatUtils.showDLog(TAG, "tag2 isNotEmpty = $tag2")
                }
            }

            for (tag1 in tags1) {
                if (!tag1.isBlank()) {
                    LogcatUtils.showDLog(TAG, "tag1 isBlank = $tag1")
                }
            }

            for (tag2 in tags2) {
                if (!tag2.isBlank()) {
                    LogcatUtils.showDLog(TAG, "tag2 isBlank = $tag2")
                }
            }

            LogcatUtils.showDLog(TAG, "tags1 = $tags1")
            LogcatUtils.showDLog(TAG, "tags2 = $tags2")
            LogcatUtils.showDLog(TAG, "tags1.isNotEmpty() = ${tags1.isNotEmpty()}")
            LogcatUtils.showDLog(TAG, "tags2.isNotEmpty() = ${tags2.isNotEmpty()}")
        }
        findViewById<Button>(R.id.bt_test_list).setOnClickListener() {
            LogcatUtils.showDLog(TAG, "bt_test_list")
            var tags1 = listOf("a", "b")
            var tags2 = arrayOf("c", "d")

            LogcatUtils.showDLog(TAG, "tags1 = $tags1")
            LogcatUtils.showDLog(TAG, "tags2 = $tags2")
        }
        findViewById<Button>(R.id.bt_test_sub_str).setOnClickListener() {
            LogcatUtils.showDLog(TAG, "bt_test_sub_str")
//            var path = "/data/data/com.tencent.mm/MicroMsg/89664f0a81e15cd6169910f646f0d39b"
            var path = "/data/data/io.va.exposed/virtual/data/user/0/com.tencent.mm/MicroMsg/8b7b4003c346df9e6056e1474a0d5d16/EnMicroMsg.db"
            val mmIndex = path.indexOf("com.tencent.mm")
            val PAC = "com.tencent.mm"
            val pck = path.substring("/data/data/".length, mmIndex)
            val mmPath = path.substring(0, mmIndex + PAC.length + 1)
            LogcatUtils.showDLog(TAG, "pck: $pck")
            LogcatUtils.showDLog(TAG, "mmPath: $mmPath")
        }

        findViewById<Button>(R.id.bt_test_for).setOnClickListener() {
            LogcatUtils.showDLog(TAG, "bt_test_for")
            val a1 = mutableListOf<String>()
            a1.add("a1")
            a1.add("a2")
            a1.add("a3")
            val b1 = mutableListOf<String>()
            b1.add("b1")
            b1.add("b2")
            b1.add("b3")

            for (a in a1) {
                for (b in b1) {
                    if (a.equals("a2")) {
                        break
                    }
                    LogcatUtils.showDLog(TAG, "$b 内层")
                }
                LogcatUtils.showDLog(TAG, "$a 外层")
            }
        }
        findViewById<Button>(R.id.bt_test_shr).setOnClickListener() {
            LogcatUtils.showDLog(TAG, "bt_test_let")
            var type = 3
            val flag = type.shr(3) and 1 == 1
            LogcatUtils.showDLog(TAG, "flag = $flag")
            LogcatUtils.showDLog(TAG, "type.shr(3) = ${type.shr(3)}")
            val test = "5,7,34"
            val testArray = test.split(",")
            for (v in testArray) {
                LogcatUtils.showDLog(TAG, "v = $v")
            }
            LogcatUtils.showDLog(TAG, "test = ${test.contains("3")}")

        }
        findViewById<Button>(R.id.bt_test_let).setOnClickListener() {
            LogcatUtils.showDLog(TAG, "bt_test_let")
            var testNull = null
            try {
                testNull?.let {
                    LogcatUtils.showDLog(TAG, "aa ")
                }
            } catch (e: Exception) {
                LogcatUtils.showDLog(TAG, "e =${e.localizedMessage} ")
            }
        }
        findViewById<Button>(R.id.bt_test_filter).setOnClickListener() {
            //val filterMsgs = uploadMsgs.filter { it.flag == 1 }
//            var uploadMsgs = arrayListOf<FilterBean>()
//            for (i in 1..20) {
//                var filterBean = FilterBean()
//                filterBean.state = i
//                filterBean.name = "name$i"
//                uploadMsgs.add(filterBean)
//            }
//            LogcatUtils.showDLog(TAG, "old uploadMsgs = $uploadMsgs")
//            val filterMsg = uploadMsgs.filter { it.state == 4 }
//
//            LogcatUtils.showDLog(TAG, "new filterMsg = $filterMsg")

            var uploadMsgs = arrayListOf<Int>()
            for (i in 1..20) {
                uploadMsgs.add(i)
            }
            LogcatUtils.showDLog(TAG, "old uploadMsgs = $uploadMsgs")
            val filterMsg = uploadMsgs.filter { it == 4 }

            LogcatUtils.showDLog(TAG, "new filterMsg = $filterMsg")
        }

        findViewById<Button>(R.id.bt_test_to).setOnClickListener() {
            val a = 29.8f
            val a1 = 29.4f
            val a2 = 29.5f
            val b = a.toInt()
            val b1 = a1.toInt()
            val b2 = a2.toInt()
            LogcatUtils.showDLog(TAG, "b = $b")
            LogcatUtils.showDLog(TAG, "b1 = $b1")
            LogcatUtils.showDLog(TAG, "b2 = $b2")
        }
        findViewById<Button>(R.id.bt_test_count_down).setOnClickListener() {
            Observable.interval(0, 3, TimeUnit.SECONDS, Schedulers.newThread())
                    .take(6)
                    .subscribe({
                        LogcatUtils.showDLog(TAG, "计数开始： $it")
                        if (it == 5L) {
                        }
                    }, {
                        LogcatUtils.showDLog(TAG, "startCountDown onError " + it.localizedMessage)
                    })
        }

        findViewById<Button>(R.id.bt_test_error).setOnClickListener() {
            Observable.just(1)
                    .doOnError {
                        LogcatUtils.showDLog(TAG, "doOnError = " + it.localizedMessage)
                    }
                    .map {
                        //                        val a = 1 / 0
                        try {
                            throw Exception("错误")

                        } catch (e: java.lang.Exception) {
                            LogcatUtils.showDLog(TAG, "catch error = " + e.localizedMessage)
                        }

                        ""
                    }
                    .subscribe({
                        LogcatUtils.showDLog(TAG, "it = $it")
                    }, {
                        LogcatUtils.showDLog(TAG, "error = " + it.localizedMessage)
                    })

        }

        findViewById<Button>(R.id.bt_test_rxdownload).setOnClickListener() {
            //            val url = "http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/platform/chatagent_v3.1.15_wechat-server_d8f9c626_release.apk";
            val url = "https://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/chatagent_v3.1.29_dev_853d4649_release.apk";
            val taskM = url.manager()
            try {
                url.download()
                        .observeOn(AndroidSchedulers.mainThread())
                        .delaySubscription(800, TimeUnit.MILLISECONDS)
                        .doOnError {
                            LogcatUtils.showDLog(TAG, "doOnError = " + it.localizedMessage)
                        }
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
            } catch (e: java.lang.Exception) {

            }

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
            Observable.just(System.currentTimeMillis())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        LogcatUtils.showDLog(TAG, "thread: ${Thread.currentThread()}")
                    }, {})

            LogcatUtils.showDLog(TAG, "clipe = " + cmb?.primaryClip?.getItemAt(0)?.text)
            clearFriendCirclePic()

            getCRMRoomList()
                    ?.map {
                        it.forEach { user ->
                            val string = Gson().toJson(user)
                            LogcatUtils.showDLog(TAG, "chatroom = $string")
                        }
                        return@map "a"
                    }?.subscribe(
                            {
                                LogcatUtils.showDLog(TAG, "saveChatRoomListToDisk path = $it")
                            }, {
                        LogcatUtils.showELog(TAG, "saveChatRoomListToDisk throwable = ${it.localizedMessage}")
                    }
                    )


            val callbackClass1 = this.classLoader
                    .loadClass("prictise.com.application1.interfaceCus.PersonInterface")
            val a1 = Proxy.newProxyInstance(this.classLoader, arrayOf(callbackClass1), InvocationHandler { proxy, method, args ->
                LogcatUtils.showDLog(TAG, "sendRichMessage2 method.name=${method.name}")
                LogcatUtils.showDLog(TAG, "sendRichMessage2 method.args[0]=${args[0]}")
                "asss"
                "sdsfs"
                "rtygb"
                LogcatUtils.showDLog(TAG, "sendRichMessage3 method.name=${method.name}")
            })

            val callbackClass2 = this.classLoader
                    .loadClass("prictise.com.application1.interfaceCus.PersonInterface")
            val a2 = Proxy.newProxyInstance(this.classLoader, arrayOf(callbackClass2), InvocationHandler { proxy, method, args ->
                LogcatUtils.showDLog(TAG, "sendRichMessage4 method.name=${method.name}")
                LogcatUtils.showDLog(TAG, "sendRichMessage4 method.args[0]=${args[0]}")
                args[0]
            })
            val a11 = a1 as PersonInterface
            val a21 = a2 as PersonInterface
            a11.onResult(1)
            a21.onResult(2)

//            LogcatUtils.showDLog(TAG, "sendRichMessage3 a1 =${a11.onResult(1)}")
//            LogcatUtils.showDLog(TAG, "sendRichMessage3 a2 =${a21.onResult()}")
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

    private fun checkIsWeekUpScreen(): Boolean {
        if (!checkAndWakeUpScreen(applicationContext)) {
            //没唤醒屏幕和解锁
            LogcatUtils.showDLog(TAG, "onNewChanceComing## cant wake or unlock")
            return true
        }
        return false
    }

    fun checkAndWakeUpScreen(context: Context): Boolean {
        LogcatUtils.showDLog(TAG, "checkAndWakeUpScreen()")
        val mList = ArrayList<String>()
        /*唤醒屏幕*/
        if (!isScreenLight(context)) {
            LogcatUtils.showDLog(TAG, "need wake screen")
            mList.add("input keyevent " + KeyEvent.KEYCODE_WAKEUP)
        }

        if (isScreenLocked(context)) {
            LogcatUtils.showDLog(TAG, "need unlock")
            /*从下往上滑动解锁*/
            val lock = "input keyevent 82"
            mList.add(lock)
        }
        if (mList.isNotEmpty()) {
        }
        Thread.sleep(1500L)
        if (isScreenLocked(context)) {
            LogcatUtils.showDLog(TAG, "need unlock either")
            return false
        }
        return true
    }

    fun isScreenLight(context: Context): Boolean {
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return pm.isInteractive
    }

    fun isScreenLocked(context: Context): Boolean {
        val keyguardManager = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        return keyguardManager.inKeyguardRestrictedInputMode()
    }

    private fun testUnLockScreen() {
        // 获取电源管理器对象
        val pm: PowerManager = applicationContext
                .getSystemService(Context.POWER_SERVICE) as PowerManager
        val screenOn: Boolean = pm.isScreenOn()
        Log.d("WakeScreen0", "screenOn: $screenOn")

        if (!screenOn) {
            // 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
            @SuppressLint("InvalidWakeLockTag")
            val wl: PowerManager.WakeLock = pm.newWakeLock(
                    PowerManager.ACQUIRE_CAUSES_WAKEUP or
                            PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright")
            wl.acquire(10000) // 点亮屏幕
            wl.release() // 释放
        }
        // 屏幕解锁
        // 屏幕解锁
        val keyguardManager: KeyguardManager = applicationContext
                .getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        val keyguardLock: KeyguardManager.KeyguardLock = keyguardManager.newKeyguardLock("unLock")
        // 屏幕锁定
//        keyguardLock.reenableKeyguard();
        // 屏幕锁定
//        keyguardLock.reenableKeyguard();

        unLockScreen()
    }

    private fun unLockScreen() {
        val win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        win.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        win.addFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON)
        win.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
    }

    private fun checkIsChanceHasBeenFinished(chance: String, it: ObservableEmitter<String>): Boolean {
        Thread.sleep(1000)
        it.onNext(chance)
        it.onComplete()
        return true
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

    fun <S> parseGsonToS(contentGson: String, clazz: Class<S>): S {
        if (TextUtils.isEmpty(contentGson)) {
            return null!!
        }
        try {
            val gsonType = object : TypeToken<S>() {}.type
            return Gson().fromJson<S>(contentGson, gsonType)
        } catch (e: java.lang.Exception) {
            LogcatUtils.showDLog(TAG, "parseGson e : ${e.localizedMessage}")
        }
        return null!!
    }

    fun getCRMRoomList(): Single<MutableList<PersonNormal>>? {
        LogcatUtils.showDLog(TAG, "getCRMRoomList()")
        return Observable.create<MutableList<PersonNormal>> {
            val personNormal = ArrayList<PersonNormal>()
            for (index in 0 until 10) {
                val p = PersonNormal("name$index", "1")
                personNormal.add(p)
            }
            it.onNext(personNormal)
            it.onComplete()
        }.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .flatMap {
                    Observable.fromIterable(it)
                }.toList()

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
