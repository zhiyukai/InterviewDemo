package prictise.com.application1.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author zhisiyi
 * @date 18.7.28 14:22
 * @comment
 */
public class Help {
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
}
