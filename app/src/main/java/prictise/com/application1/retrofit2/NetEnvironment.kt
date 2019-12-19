package prictise.com.application1.retrofit2

import prictise.com.application1.BuildConfig

/**
 * @Author zhisiyi
 * @Date 2019.12.05 22:37
 * @Comment
 */
object NetEnvironment{
    val baseHostArrayDebug = arrayOf(
            "172.16.117.151:8080"//测试环境 0
    )

    var base_host_debug = baseHostArrayDebug[0]
    private const val PATH = "/"
    private const val HTTP = "http://"
    fun getWechatPlatformBaseUrl() =
            if (BuildConfig.DEBUG)
                HTTP + base_host_debug + PATH
            else
                ""
//                HTTP + base_host_release + PATH

}