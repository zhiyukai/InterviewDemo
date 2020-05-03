package prictise.com.application1.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import android.util.TypedValue;
import prictise.com.application1.MainApplication;

/**
 * @author zhisiyi
 * @date 18.7.28 14:22
 * @comment
 */
public class Help {

    private static final String TAG = Help.class.getSimpleName();

    public static int dp2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue, context.getResources().getDisplayMetrics());
        return size;
    }

    public static boolean isApkDebugable() {
        //debug 返回true  release 返回false
        try {
            ApplicationInfo info = MainApplication.getApplicationInstance().getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            LogcatUtils.showELog(TAG, "isApkDebugable exception e = " + e);
        }
        return false;
    }

}
