package prictise.com.application1.volley;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * @author zhisiyi
 * @date 18.7.24 23:19
 * @comment
 */
public class InternetHelp {

    String getData(Context context) {
        RequestQueue requestQueue = RequestQueueUtil.getRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, "url", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
//                Bean bean = JSON.parseObject(s, Bean.class);
//                switch (bean.getStatus()) {
//                    //成功逻辑处理
//                    case 1000:
//                        //do something...
//                        break;
//                    //case xxxx:
//                    //其他逻辑处理...
//                    //break;
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(
                5 * 1000,//链接超时时间
                0,//重新尝试连接次数
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        requestQueue.add(request);

        return "";
    }
}
