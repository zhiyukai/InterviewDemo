package prictise.com.application1;

import okhttp3.*;
import prictise.com.application1.utils.LogcatUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author mxz
 * @CreatTime 2019/9/11
 **/
public class OkHttpClientUtils {
    private static final String TAG = OkHttpClientUtils.class.getSimpleName();
//    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OkHttpClientUtils.class);

    private static OkHttpClientUtils ins;
    private static Integer TIMEOUT_SECONDS = 300;

    private OkHttpClient client;

    private OkHttpClientUtils() {
        if (client == null) {
            client = new OkHttpClient.Builder().connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS).build();
        }
    }

    public static OkHttpClientUtils getInstance() {
        if (ins == null) {
            synchronized (OkHttpClientUtils.class) {
                if (ins == null) {
                    ins = new OkHttpClientUtils();
                }
            }
        }
        return ins;
    }


    /**
     * Post请求
     * MediaType：application/json
     *
     * @param httpClient 自定义参数的OkHttpClient
     * @param uri
     * @param paramJson
     * @return
     */
    public String doPostWithJsonResult(OkHttpClient httpClient, String uri,
                                       String paramJson) {
//        log.info("doPostWithJsonResult [{}] param [{}] - start", uri, paramJson);
        String retString = "";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, paramJson);
        Request request = new Request.Builder().addHeader("token","d3c1MzQxZWE3NzBiZmMzYzA3Om4zYUt4YzJMb1VOX3ZWaUJEcVE3bFRaaG1EWUM2MTFqd21NSnRwNVVwZDgxMjM0NTY3ODk=").url(uri).post(body).build();
        Response response = null;
        try{
        httpClient.newCall(request).execute();
        } catch (IOException e) {
//            LogcatUtils.showELog(TAG,"doPostWithJsonResult [{}] param [{}] errorMessage [{}]"+uri, paramJson, e);
            return retString;
        }
//        LogcatUtils.showELog(TAG,"1");
        System.out.println("1");
        return "";
    }


    /**
     * Post请求
     * MediaType：application/json
     *
     * @param uri
     * @param paramJson
     * @return
     */
    public String doPostWithJsonResult(String uri, String paramJson) {
        return doPostWithJsonResult(client, uri, paramJson);
    }
}
