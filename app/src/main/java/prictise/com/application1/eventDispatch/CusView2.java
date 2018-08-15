package prictise.com.application1.eventDispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author zhisiyi
 * @date 18.7.11 21:16
 * @comment
 */
public class CusView2 extends View {
    private String TAG = CusView2.class.getSimpleName();

    public CusView2(Context context) {
        super(context);
    }

    public CusView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CusView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
