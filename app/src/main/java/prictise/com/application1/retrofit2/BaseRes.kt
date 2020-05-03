package prictise.com.application1.retrofit2

/**
 * @Author zhisiyi
 * @Date 2019.12.05 21:11
 * @Comment
 */
class BaseRes {
    var flag: Int = 0
    var message: String? = null
    var data: String? = null

    override fun toString(): String {
        return "BaseRes(flag=$flag, message=$message, data=$data)"
    }
}