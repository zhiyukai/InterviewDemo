package prictise.com.application1.kotlin

import android.util.Log

/**
 * @Author zhisiyi
 * @Date 2019.11.06 17:47
 * @Comment
 */
class BaseKt {
    var initialized = 1

    // Private function, so the return type is the anonymous object type
    private fun foo() = object {
        val x: String = "x"
    }

    // Public function, so the return type is Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x        // Works
//        val x2 = publicFoo().x  // ERROR: Unresolved reference 'x'
    }

    interface A {
        fun foo() {
            print("A")
        }

        fun bar()
    }

    interface B {
        fun foo() {
            print("B")
        }

        fun bar() {
            print("bar")
        }
    }

    class C : A {
        override fun bar() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    class D : A, B {
        override fun foo() {
            super<A>.foo()
            super<B>.foo()
        }

        override fun bar() {
            super<B>.bar()
        }
    }

    fun test(a: Int, b: Int): Int {
        return a + b
    }

    val test2: (Int, Int) -> Int = { a, b -> a + b }


    fun <T> get(a: T): T {
        when (a) {
//            a is Int ->
        }
        var sk: Int? = 22
        return a;
    }

    class Room {
        // 这是声明一个变量，问号跟在类名后面
        var room: Room? = Room()

        private fun checkRoom() {
            // 因为加上了问号，所以可以任意的把room变成空
            room = null

            // 因为在调用时加上了问号，所以程序不会抛出异常
            Log.d("TAG", "-->> room name = ${room?.checkRoom()}")
        }

        fun c() {
            val myList: ArrayList<String>? = null
            var a = myList ?: ArrayList<String>();
            Log.d("TAG", "-->> List Size = ${myList!!.size}")
        }
    }

    data class Q(var age: Int = 12) {
    }

    data class User(var name: String? = "张三", var age: Int? = 12, var phoneNum: String? = "111111")

    companion object {
        val roomList: ArrayList<Room>? = null
        //        if (roomList?.size ?: 0 > 0){    // 这一行添加了?:
//            Log.d("TAG", "-->> 房间数不是0")
//        }
        @JvmStatic
        fun main(args: Array<String>) {
//            var baseKt = BaseKt();
//            System.out.println(baseKt.get("asdf"))
//            System.out.println(roomList?.size)
//            var items = setOf("apple", "banana", "kiwi")
//            val items2 = mutableSetOf("apple", "banana", "kiwi")
//            val item3 = mutableListOf<Q>(Q(2), Q(4))
//            val item4 = mutableMapOf<String, Int>(Pair("张三", 1), Pair("李四", 2))
//
//            items2.add(Q(34).toString())
//            println(items2)
//            println(item4)
//            val price = "${'$'}9.99"
//            println(price)
//            val a = 123
//            val s = "123"
//            println("a = ${a}")
//            println("a2 = $a")
//            println("s.len = ${s.length}")


            val user = User("Kotlin", 1, "1111111")

            val result = user?.run {
                println("my name is $name, I am $age years old, my phone number is $phoneNum")
                "end"
            }
            println("result: $result")
            /**
             * 整体作用功能和run函数很像，唯一不同点就是它返回的值是对象本身，而run函数是一个闭包形式返回，返回的是最后一行的值。
             * 正是基于这一点差异它的适用场景稍微与run函数有点不一样。apply一般用于一个对象实例初始化的时候，
             * 需要对对象中的属性进行赋值。或者动态inflate出一个XML的View的时候需要给View绑定数据也会用到，
             * 这种情景非常常见。特别是在我们开发中会有一些数据model向View model转化实例化的过程中需要用到。
             */

            val result3 = user.apply {
                println("my name is $name, I am $age years old, my phone number is $phoneNum")
                1000
            }
            println("result3: $result3")

            val result4 = "testLet"?.also {
                println(it.length)
                1000
            }
            println(result4)

            val result5 = "testLet"?.let {
                println(it.length)
                var s = it.substring(4)
                println(s)
                1000
            }
            println("result5 = " + result5)
        }

    }
}