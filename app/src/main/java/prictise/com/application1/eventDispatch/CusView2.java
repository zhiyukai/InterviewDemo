package prictise.com.application1.eventDispatch;

import static prictise.com.application1.utils.Constants.EventDispatchActivity_TAG;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @author zhisiyi
 * @date 18.7.11 21:16
 * @comment
 */
public class CusView2 extends View {

  private String TAG = EventDispatchActivity_TAG;

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
    Log.e(TAG, "CusView2 dispatchTouchEvent");
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        LogcatUtils.showDLog(TAG, "CusView2 dispatchTouchEvent MotionEvent.ACTION_DOWN");
        break;
      case MotionEvent.ACTION_MOVE:
        LogcatUtils.showDLog(TAG, "CusView2 dispatchTouchEvent MotionEvent.ACTION_MOVE");
        break;
      case MotionEvent.ACTION_UP:
        LogcatUtils.showDLog(TAG, "CusView2 dispatchTouchEvent MotionEvent.ACTION_UP");
        break;
      case MotionEvent.ACTION_CANCEL:
        LogcatUtils.showDLog(TAG, "CusView2 dispatchTouchEvent MotionEvent.ACTION_CANCEL");
        break;
      default:
        break;
    }
    return super.dispatchTouchEvent(event);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.e(TAG, "CusView2 onTouchEvent");
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        LogcatUtils.showDLog(TAG, "CusView2 onTouchEvent MotionEvent.ACTION_DOWN");
        break;
      case MotionEvent.ACTION_MOVE:
        LogcatUtils.showDLog(TAG, "CusView2 onTouchEvent MotionEvent.ACTION_MOVE");
        break;
      case MotionEvent.ACTION_UP:
        LogcatUtils.showDLog(TAG, "CusView2 onTouchEvent MotionEvent.ACTION_UP");
        break;
      case MotionEvent.ACTION_CANCEL:
        LogcatUtils.showDLog(TAG, "CusView2 onTouchEvent MotionEvent.ACTION_CANCEL");
        break;
      default:
        break;
    }
    return super.onTouchEvent(event);
  }
}
