package prictise.com.application1.retrofit2

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * @Author zhisiyi
 * @Date 2019.12.11 21:44
 * @Comment
 */
interface NetServer {

    @Multipart
    @POST("api/wechat/uploadFile")
    fun uploadWxFiles(@Part("type") type: RequestBody, @Part file: MultipartBody.Part)
}