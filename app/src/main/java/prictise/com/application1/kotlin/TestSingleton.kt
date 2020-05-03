package prictise.com.application1.kotlin

import java.io.ByteArrayInputStream
import javax.xml.parsers.DocumentBuilderFactory

/**
 * @Author zhisiyi
 * @Date 2019.11.06 19:16
 * @Comment
 */
class TestSingleton private constructor() {
    companion object {
        @Volatile
        private var mS: TestSingleton? = null;

        fun getInstance(): TestSingleton {
            if (mS == null) {
                synchronized(TestSingleton::class) {
                    if (mS == null) {
                        mS = TestSingleton();
                    }
                }
            }
            return mS!!
        }

        @JvmStatic
        fun main(args: Array<String>) {
//            fun printMsg() = System.out.println("MySingleton")
//            printMsg();
            val msg = "<msg fromusername=\"wxid_7o2wtsl88dwb21\" encryptusername=\"v1_a1902655b6419b08026067f626664675d2e66a206c252490ad00c54368e144e568b151c04bfb42bf10f77151d9a4194b@stranger\" fromnickname=\"飍\" content=\"你好，我是智绍举\" fullpy=\"xiu\" shortpy=\"X\" imagestatus=\"3\" scene=\"30\" country=\"CN\" province=\"Beijing\" city=\"\" sign=\"\" percard=\"1\" sex=\"1\" alias=\"zsj1114082241\" weibo=\"\" albumflag=\"3\" albumstyle=\"0\" albumbgimgid=\"\" snsflag=\"17\" snsbgimgid=\"http://mmsns.qpic.cn/mmsns/4376ae1e0cf0ccced233def9ad1560d0dec29d64941ab85a30d36e9d20551e73d134f94732dc674092655741238a060bb20928f661ef59cc/0\" snsbgobjectid=\"12244535649829064934\" mhash=\"a27d67ed4b137057b36e423ebbb7591d\" mfullhash=\"a27d67ed4b137057b36e423ebbb7591d\" bigheadimgurl=\"http://wx.qlogo.cn/mmhead/ver_1/mmdxVoHpzz8prbB7qR129bEsYUyKn6cVhH6fEfIxIlx8MEsosQQk2gZQK7ialWwBj9ASibLUUvCXT7NHnmmZWU0y6ibfgNAJicQj8gBXwTUrtAU/0\" smallheadimgurl=\"http://wx.qlogo.cn/mmhead/ver_1/mmdxVoHpzz8prbB7qR129bEsYUyKn6cVhH6fEfIxIlx8MEsosQQk2gZQK7ialWwBj9ASibLUUvCXT7NHnmmZWU0y6ibfgNAJicQj8gBXwTUrtAU/96\" ticket=\"v2_86a6e08f9e037c5afd4176332dd32aa484133eb083e8b8329f54abedff7b150562ebf4be0b09cfdf8e23d2ecef17e0b97911459654d61d6b04a2ae5b67e7d601ac7a48752c0a6ded7501f45316705798@stranger\" opcode=\"2\" googlecontact=\"\" qrticket=\"\" chatroomusername=\"\" sourceusername=\"\" sourcenickname=\"\" sharecardusername=\"\" sharecardnickname=\"\" cardversion=\"\"><brandlist count=\"0\" ver=\"670495341\"></brandlist></msg>"
            parseHeadImg(msg)
        }

        private fun parseHeadImg(msgContent: String) {
            println("parseHeadImg = " + msgContent)
            val documentBuilderFactory = DocumentBuilderFactory.newInstance()
            var documentBuilder = documentBuilderFactory.newDocumentBuilder()
            val bytes = ByteArrayInputStream(msgContent.toByteArray())
            var document = documentBuilder.parse(bytes)
            println("document = " + document.toString())
            val attri = document.childNodes.item(0).attributes
            var attribute = document.attributes
            if (attri != null) {
                val len = attri.length
                for (i in 0 until len) {
                    var node = attri.item(i)
                    println("node.nodeName = " + node.nodeName)
                    println("node.nodeValue = " + node.nodeValue)
                    println()
                }

                println("-------------")
                var bigImgUrl = attri.getNamedItem("bigheadimgurl")
                println("bigImgUrl = ${bigImgUrl.nodeValue}")
                var alias = attri.getNamedItem("alias")
                println("alias = ${alias.nodeValue}")


            }
        }
    }
}