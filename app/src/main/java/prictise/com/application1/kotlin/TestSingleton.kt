package prictise.com.application1.kotlin

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
            fun printMsg() = System.out.println("MySingleton")
            printMsg();
        }
    }
}