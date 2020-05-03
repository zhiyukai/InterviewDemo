package prictise.com.application1

import io.reactivex.Observable
import org.junit.Test
import prictise.com.application1.bean.Student
import java.util.*

/**
 * @Author zhisiyi
 * @Date 2019.11.26 15:02
 * @Comment
 */
class TestCaseKT {

    val TAG = TestCaseKT::class.simpleName
    @Test
    fun testAddFriend() {
        var fmsgContent = "<msg fromusername=\"wxid_7o2wtsl88dwb21\" encryptusername=\"v1_a1902655b6419b08026067f626664675d2e66a206c252490ad00c54368e144e568b151c04bfb42bf10f77151d9a4194b@stranger\" fromnickname=\"飍\" content=\"我是\" fullpy=\"xiu\" shortpy=\"X\" imagestatus=\"3\" scene=\"30\" country=\"CN\" province=\"Beijing\" city=\"\" sign=\"\" percard=\"1\" sex=\"1\" alias=\"zsj1114082241\" weibo=\"\" albumflag=\"3\" albumstyle=\"0\" albumbgimgid=\"\" snsflag=\"17\" snsbgimgid=\"http://mmsns.qpic.cn/mmsns/4376ae1e0cf0ccced233def9ad1560d0dec29d64941ab85a30d36e9d20551e73d134f94732dc674092655741238a060bb20928f661ef59cc/0\" snsbgobjectid=\"12244535649829064934\" mhash=\"a27d67ed4b137057b36e423ebbb7591d\" mfullhash=\"a27d67ed4b137057b36e423ebbb7591d\" bigheadimgurl=\"http://wx.qlogo.cn/mmhead/ver_1/Cbgno8PicGOOCiaASuGgXQ74ibM0WKejZA53wZHnEGxFmUrMdyerLml9WqU0bf8vpM7WqfibNXBJ2tiaDBlXo2iarxkLDhgUdCPbbkg5aWiaXH6CNs/0\" smallheadimgurl=\"http://wx.qlogo.cn/mmhead/ver_1/Cbgno8PicGOOCiaASuGgXQ74ibM0WKejZA53wZHnEGxFmUrMdyerLml9WqU0bf8vpM7WqfibNXBJ2tiaDBlXo2iarxkLDhgUdCPbbkg5aWiaXH6CNs/96\" ticket=\"v2_86a6e08f9e037c5afd4176332dd32aa44466efee9b7df0cfca9745399a8c359ba5de338b781f7bddd81d3bbd8db30574e0b1144109a63e2257930245bafabb79ab06fea8e5523f963703aa358334ff16@stranger\" opcode=\"2\" googlecontact=\"\" qrticket=\"\" chatroomusername=\"\" sourceusername=\"\" sourcenickname=\"\" sharecardusername=\"\" sharecardnickname=\"\" cardversion=\"\"><brandlist count=\"0\" ver=\"670495261\"></brandlist></msg>"
        fmsgContent = fmsgContent?.split("ticket=\"")?.get(1)
        val startIndex = fmsgContent?.indexOf("v2_") ?: 0
        val endIndex = fmsgContent?.indexOf("\"") ?: 0
        val param2Value = fmsgContent?.substring(startIndex, endIndex)
        println("$param2Value")
    }

    @Test
    fun testNull() {
        var t: String? = null
        println(t!!.length)
    }

    @Test
    fun testComparable() {
        val students = ArrayList<Student>()
        students.add(Student("b", 19))
        students.add(Student("c", 28))
        students.add(Student("a", 18))

        Collections.sort(students) { o1, o2 -> o2.age - o1.age }
        for (student in students) {
            println(student.toString())
        }
    }

    fun isOdd(x: Int): Boolean {
        return x % 2 == 1
    }

    @Test
    fun testFilter() {
        val list = listOf(1, 2, 3, 4, 5)

        println(list.filter(::isOdd).toString())

    }

    @Test
    fun testWithIndex() {
        var messageList: List<Student>? = mutableListOf(Student("zhangsan", 23), Student("lisi", 45))
        for (i in messageList!!.withIndex()) {

            println(i)
        }
    }

    @Test
    fun testInitList() {
//        var list = arrayListOf<String>()
        val list = ArrayList<String>(23)
        for (i in 0 until 30) {
            list.add("s".plus(i))
        }

        for (s in list) {
            println(s)
        }
    }

    @Test
    fun testContainsDot() {
        var s = "eewsss.sdsdds"
        println(s.contains("."))
    }

    @Test
    fun testFlatMapIterable() {
        val list = Arrays.asList(1, 2, 3);
        Observable.just(list)
                .flatMapIterable { it }
                .subscribe{
                    println(it)
                }
    }


}