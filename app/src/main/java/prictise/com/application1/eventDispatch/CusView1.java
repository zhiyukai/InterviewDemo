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
public class CusView1 extends View {

  //    private String TAG = CusView1.class.getSimpleName();
  private String TAG = EventDispatchActivity_TAG;

  private Context mContext;
  //定义一个paint
  private Paint mPaint;

  private String mText = "测试文字，自定义view";
  //绘制时控制文本绘制的范围
  private Rect mBound;

  public CusView1(Context context) {
    this(context, null);
  }

  public CusView1(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawColor(Color.BLUE);
    mPaint.setColor(Color.RED);
    canvas
        .drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2,
            mPaint);

  }

  public CusView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.mContext = context;
    mBound = new Rect();
    mPaint = new Paint();
    mPaint.setTextSize(60);
    mPaint.getTextBounds(mText, 0, mText.length(), mBound);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    final int minimumWidth = getSuggestedMinimumWidth();
    final int minimumHeight = getSuggestedMinimumHeight();
    Log.e("YView", "CusView1 ---minimumWidth = " + minimumWidth + "");
    Log.e("YView", "CusView1 ---minimumHeight = " + minimumHeight + "");
    int width = measureWidth(minimumWidth, widthMeasureSpec);
    int height = measureHeight(minimumHeight, heightMeasureSpec);
    setMeasuredDimension(width, height);
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent event) {
    Log.e(TAG, "CusView1 dispatchTouchEvent");
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        LogcatUtils.showDLog(TAG, "CusView1 dispatchTouchEvent MotionEvent.ACTION_DOWN");
        break;
      case MotionEvent.ACTION_MOVE:
        LogcatUtils.showDLog(TAG, "CusView1 dispatchTouchEvent MotionEvent.ACTION_MOVE");
        break;
      case MotionEvent.ACTION_UP:
        LogcatUtils.showDLog(TAG, "CusView1 dispatchTouchEvent MotionEvent.ACTION_UP");
        break;
      case MotionEvent.ACTION_CANCEL:
        LogcatUtils.showDLog(TAG, "CusView1 dispatchTouchEvent MotionEvent.ACTION_CANCEL");
        break;
      default:
        break;
    }
    return super.dispatchTouchEvent(event);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.e(TAG, "CusView1 onTouchEvent");
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        LogcatUtils.showDLog(TAG, "CusView1 onTouchEvent MotionEvent.ACTION_DOWN");
//        return true;
        break;
      case MotionEvent.ACTION_MOVE:
        LogcatUtils.showDLog(TAG, "CusView1 onTouchEvent MotionEvent.ACTION_MOVE");
        break;
      case MotionEvent.ACTION_UP:
        LogcatUtils.showDLog(TAG, "CusView1 onTouchEvent MotionEvent.ACTION_UP");
        break;
      case MotionEvent.ACTION_CANCEL:
        LogcatUtils.showDLog(TAG, "CusView1 onTouchEvent MotionEvent.ACTION_CANCEL");
        break;
      default:
        break;
    }
    return super.onTouchEvent(event);
  }

  private int measureWidth(int defaultWidth, int measureSpec) {

    int specMode = MeasureSpec.getMode(measureSpec);
    int specSize = MeasureSpec.getSize(measureSpec);
    Log.e(TAG, "CusView1 ---speSize = " + specSize + "");

    switch (specMode) {
      case MeasureSpec.AT_MOST:
        defaultWidth = (int) mPaint.measureText(mText) + getPaddingLeft() + getPaddingRight();

        Log.e(TAG, "YViewWidth ---speMode = AT_MOST");
        break;
      case MeasureSpec.EXACTLY:
        Log.e(TAG, "YViewWidth ---speMode = EXACTLY");
        defaultWidth = specSize;
        break;
      case MeasureSpec.UNSPECIFIED:
        Log.e(TAG, "YViewWidth ---speMode = UNSPECIFIED");
        defaultWidth = Math.max(defaultWidth, specSize);
    }
    return defaultWidth;
  }

  private int measureHeight(int defaultHeight, int measureSpec) {

    int specMode = MeasureSpec.getMode(measureSpec);
    int specSize = MeasureSpec.getSize(measureSpec);
    Log.e(TAG, "YViewHeight ---speSize = " + specSize + "");

    switch (specMode) {
      case MeasureSpec.AT_MOST:
        defaultHeight =
            (int) (-mPaint.ascent() + mPaint.descent()) + getPaddingTop() + getPaddingBottom();
        Log.e(TAG, "YViewHeight ---speMode = AT_MOST");
        break;
      case MeasureSpec.EXACTLY:
        defaultHeight = specSize;
        Log.e(TAG, "YViewHeight ---speSize = EXACTLY");
        break;
      case MeasureSpec.UNSPECIFIED:
        defaultHeight = Math.max(defaultHeight, specSize);
        Log.e(TAG, "YViewHeight ---speSize = UNSPECIFIED");
//        1.基准点是baseline
//        2.ascent：是baseline之上至字符最高处的距离
//        3.descent：是baseline之下至字符最低处的距离
//        4.leading：是上一行字符的descent到下一行的ascent之间的距离,也就是相邻行间的空白距离
//        5.top：是指的是最高字符到baseline的值,即ascent的最大值
//        6.bottom：是指最低字符到baseline的值,即descent的最大值

        break;
    }
    return defaultHeight;


  }
}
