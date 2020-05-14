package prictise.com.application1.kotlin.bean

import com.google.gson.annotations.SerializedName

/**
 * @Author zhisiyi
 * @Date 2020.04.28 16:14
 * @Comment
 */
class GsonSerializeBean {

    /**
     * value name1,代表序列化字符串之后的名字
     * alternate a,b,c 代表反序列化把接收过来的a,b,c 赋值到name上
     */
    @SerializedName(value = "name1", alternate = ["a", "b", "c"])
    var name: String = ""
    @SerializedName(value = "desc1")
    var desc:String = ""

//    override fun toString(): String {
//        return "GsonSerializeBean(name='$name', desc='$desc')"
//    }


}