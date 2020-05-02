package prictise.com.application1.eventDispatch;

import static prictise.com.application1.utils.Constants.EventDispatchActivity_TAG;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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

  private Paint mPaint;

  private String mText = "测试文字2，自定义view2";
  //绘制时控制文本绘制的范围
  private Rect mBound;

  public CusView2(Context context) {
    this(context, null, 0);
  }

  public CusView2(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CusView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
//      this.mContext = context;
    mBound = new Rect();
    mPaint = new Paint();
    mPaint.setTextSize(60);
    mPaint.getTextBounds(mText, 0, mText.length(), mBound);
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

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawColor(Color.GRAY);
    mPaint.setColor(Color.RED);
    canvas
        .drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2,
            mPaint);

  }
}
