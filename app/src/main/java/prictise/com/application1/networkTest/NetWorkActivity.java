package prictise.com.application1.networkTest;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @author zhisiyi
 * @date 18.8.28 10:14
 * @comment https://www.cnblogs.com/fnlingnzb-learner/p/7531811.html
 * 判断多种情况的网络
 */
public class NetWorkActivity extends Activity {
    private final String TAG = NetWorkActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        findViewById(R.id.bt_test_net).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogcatUtils.showELog(TAG, "isNetWorkConnected = "
                        + isNetWorkConnected(NetWorkActivity.this) + "");
            }
        });
    }

    //判断时候有网络
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
