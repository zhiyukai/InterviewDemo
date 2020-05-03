package prictise.com.application1.retrofit2

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * @Author zhisiyi
 * @Date 2019.12.05 21:31
 * @Comment
 */
object RetrofitManager {
    private var baseNet:BaseNet?= null

    fun getAPIService(): BaseNet {
        if (baseNet == null) {
            synchronized(this@RetrofitManager) {
                if (baseNet == null) {
                    val builder = OkHttpClient.Builder()
                    builder.connectTimeout(20, TimeUnit.SECONDS)

                    //信任所有服务器地址
                    builder.hostnameVerifier { _, _ -> true }

                    //创建管理器
                    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }


                        @Throws(java.security.cert.CertificateException::class)
                        override fun checkClientTrusted(x509Certificates: Array<X509Certificate>, s: String) {
                        }

                        @Throws(java.security.cert.CertificateException::class)
                        override fun checkServerTrusted(x509Certificates: Array<X509Certificate>, s: String) {
                        }
                    })
                    try {
                        val sslContext = SSLContext.getInstance("TLS")
                        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                        val sslSocketFactory = sslContext.socketFactory
                        //为OkHttpClient设置sslSocketFactory
                        builder.sslSocketFactory(sslSocketFactory)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }


                    val okHttpClient = builder.build()
                    val retrofit = Retrofit.Builder()
                            .baseUrl(NetEnvironment.getWechatPlatformBaseUrl())
                            .client(okHttpClient)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                            .addConverterFactory(GsonConverterFactory.create())
                            .build()

                    baseNet = retrofit.create(BaseNet::class.java)
                }
            }
        }

        return baseNet!!
    }
}