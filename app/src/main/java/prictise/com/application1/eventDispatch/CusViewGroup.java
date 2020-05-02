package prictise.com.application1.eventDispatch;

import static prictise.com.application1.utils.Constants.EventDispatchActivity_TAG;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @author zhisiyi
 * @date 18.7.11 21:11
 * @comment
 */
public class CusViewGroup extends ViewGroup {

  private String TAG = EventDispatchActivity_TAG;

  public CusViewGroup(Context context) {
    super(context);
  }

  public CusViewGroup(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CusViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    LogcatUtils.showDLog(TAG, "CusViewGroup dispatchTouchEvent");
    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        LogcatUtils.showDLog(TAG, "CusViewGroup dispatchTouchEvent MotionEvent.ACTION_DOWN");
        break;
      case MotionEvent.ACTION_MOVE:
        LogcatUtils.showDLog(TAG, "CusViewGroup dispatchTouchEvent MotionEvent.ACTION_MOVE");
        break;
      case MotionEvent.ACTION_UP:
        LogcatUtils.showDLog(TAG, "CusViewGroup dispatchTouchEvent MotionEvent.ACTION_UP");
        break;
      case MotionEvent.ACTION_CANCEL:
        LogcatUtils.showDLog(TAG, "CusViewGroup dispatchTouchEvent MotionEvent.ACTION_CANCEL");
        break;
      default:
        break;
    }
    return super.dispatchTouchEvent(ev);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    LogcatUtils.showDLog(TAG, "CusViewGroup onInterceptTouchEvent onInterceptTouchEvent");
    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        LogcatUtils.showDLog(TAG, "CusViewGroup onInterceptTouchEvent MotionEvent.ACTION_DOWN");
        break;
      case MotionEvent.ACTION_MOVE:
        LogcatUtils.showDLog(TAG, "CusViewGroup onInterceptTouchEvent MotionEvent.ACTION_MOVE");
        break;
      case MotionEvent.ACTION_UP:
        LogcatUtils.showDLog(TAG, "CusViewGroup onInterceptTouchEvent MotionEvent.ACTION_UP");
        break;
      case MotionEvent.ACTION_CANCEL:
        LogcatUtils.showDLog(TAG, "CusViewGroup onInterceptTouchEvent MotionEvent.ACTION_CANCEL");
        break;
      default:
        break;
    }
    return super.onInterceptTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    LogcatUtils.showDLog(TAG, "CusViewGroup onTouchEvent");
    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        LogcatUtils.showDLog(TAG, "CusViewGroup onTouchEvent MotionEvent.ACTION_DOWN");
        break;
      case MotionEvent.ACTION_MOVE:
        LogcatUtils.showDLog(TAG, "CusViewGroup onTouchEvent MotionEvent.ACTION_MOVE");
        break;
      case MotionEvent.ACTION_UP:
        LogcatUtils.showDLog(TAG, "CusViewGroup onTouchEvent MotionEvent.ACTION_UP");
        break;
      case MotionEvent.ACTION_CANCEL:
        LogcatUtils.showDLog(TAG, "CusViewGroup onTouchEvent MotionEvent.ACTION_CANCEL");
        break;
      default:
        break;
    }
    return super.onTouchEvent(ev);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {

  }
}
