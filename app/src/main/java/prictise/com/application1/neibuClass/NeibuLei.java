package prictise.com.application1.neibuClass;

import android.util.Log;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author zhisiyi
 * @Date 2019.10.28 23:44
 * @Comment
 */
public class NeibuLei {

  private static final String TAG = NeibuLei.class.getSimpleName();

  class A {

  }

  static class B {

  }

  public static void main(String[] args) {
    A a = new NeibuLei().new A();
    B b = new NeibuLei.B();

    String url = "http://wwww.baidu.com";
    OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
      @Override
      public Response intercept(Chain chain) throws IOException {
        //第一步，获得chain内的request
        Request request = chain.request();
        Log.d(TAG, "intercept-request:" + request.url());
        //第二步，用chain执行request
        Response response = chain.proceed(request);
        Log.d(TAG, "intercept-response" + "-" + response.request().url());
        //第三步，返回response
        return response;
      }
    }).build();
    final Request request = new Request.Builder()
        .url(url)
        .get()//默认就是GET请求，可以不写
        .build();
    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
//        Log.d(TAG, "onFailure: ");
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
//        Log.d(TAG, "onResponse: " + response.body().string());
      }
    });
  }

//  public static abstract void a();
}
