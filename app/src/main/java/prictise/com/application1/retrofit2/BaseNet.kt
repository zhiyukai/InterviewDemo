package prictise.com.application1.retrofit2

import android.database.Observable
import prictise.com.application1.dynamicProxy.BODY
import prictise.com.application1.dynamicProxy.POST
import prictise.com.application1.kotlin.BaseKt
import retrofit2.http.Body

/**
 * @Author zhisiyi
 * @Date 2019.12.05 21:09
 * @Comment
 */
interface BaseNet {
    @POST("/net/login")
    fun loginBody(@Body user:LoginUser):Observable<BaseRes>
}