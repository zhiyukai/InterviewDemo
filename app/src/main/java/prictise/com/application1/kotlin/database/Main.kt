package prictise.com.application1.kotlin.database

import android.util.Log
import io.reactivex.Observable
import prictise.com.application1.kotlin.rxjava.TestData

/**
 * @Author zhisiyi
 * @Date 2019.11.08 11:03
 * @Comment
 */
class Main {

    var wechatVersion = 2
    var SelectConversationUI_SEARCH_LV: String = "com.tencent.mm:id/fw"
        //        get() = when (wechatVersion) {
//            1 -> "com.tencent.mm:id/fw"
//            2 -> "com.tencent.mm:id/h8"
//            3 -> "com.tencent.mm:id/hc"
//            else -> ""
//        }
        get() {
            return field
        }
        set(value) {
            field = value
        }

    companion object {
        val TAG = Main::class.simpleName
        fun test1() {
            val array = arrayOf("a", "b", "c")
            var size = array.size

            for (index in array.indices) {
                println("index=$index")//输出0，1，2
            }

            for (index in 0..size) {
                println("index=$index")
            }
        }


        @JvmStatic
        fun main(args: Array<String>) {
//            test1()
            var m = Main()
//            m.wechatVersion = 3
//            m.test4()
            m.test6()
        }
    }

    fun test6() {
        for (index in 1..5) {
            println("index = $index")
            Observable.just(1)
                    .map {
                        TestData("zhangsan" + it)
                    }
                    .flatMap {
                        val td = mutableListOf<TestData>()
                        td.add(it)
                        td.add(it)
                        Observable.fromIterable(td)
                    }
                    .subscribe({
                        println("it = $it")
                    }, {
                        println("onBindChanged error ${it.localizedMessage}")
                    })
        }
    }

    fun test4() {
        ::SelectConversationUI_SEARCH_LV.set("1")
        println(::SelectConversationUI_SEARCH_LV.get())
    }

    fun test2() {
        val okStates = arrayOf("2", "3");
        val inList = StringBuilder(okStates.size * 2)
        for (i in 0 until okStates.size) {
            if (i > 0) {
                inList.append(",")
            }
            inList.append("?")
        }
        val sel = "status IN ($inList)"
        print(sel)
    }
}