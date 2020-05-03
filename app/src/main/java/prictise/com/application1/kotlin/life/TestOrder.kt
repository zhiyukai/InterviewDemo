package prictise.com.application1.kotlin.life

/**
 * @Author zhisiyi
 * @Date 2019.11.19 14:25
 * @Comment
 */
class TestOrder(var name: String, var age: Int) {
    private var n: String = name
    private var a: Int = 0

    init {
        println("init")
        println("n = $n")
    }

    internal open class ClassLoaderA {
        init {
            print("2")
        }

        init {
            print("3")
        }

        constructor() {
            print("5")
        }

        constructor(name: String) : this() {
            print("6")
        }

        constructor(name: String, id: String) {
            print("7")
        }

        open fun print() {
            print("4")
        }

        companion object {
            init {
                print("1")
            }
        }
    }

    internal class ClassLoaderB : ClassLoaderA("s") {
        init {
            print("b")
        }

        init {
            print("c")
        }

        override fun print() {
            print("d")
        }

        companion object {
            init {
                print("a")
            }
        }
    }

    companion object {
        lateinit var h: String
        @JvmStatic
        fun main(args: Array<String>) {
//            var testOrder = TestOrder("张三", 30)
            var classLoaderB: ClassLoaderA = ClassLoaderB()
            classLoaderB.print()
            println()
            classLoaderB = ClassLoaderB()
            
        }
    }


}