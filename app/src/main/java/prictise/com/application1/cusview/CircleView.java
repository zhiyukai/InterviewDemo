package prictise.com.application1.cusview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import prictise.com.application1.R;

public class CircleView extends View {

  private int mCirlceViewColor;
  private int mCirlceViweProgressColor;
  private int mProgress;
  private float mTextSize;
  private int mTextColor;
  private int mStyle;
  private float mRoudWidth;
  private int max;
  private Paint mPaint;

  private static final int STROKE = 0;
  private static final int FILL = 1;

  public CircleView(Context context) {
    this(context, null);
  }

  public CircleView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mPaint = new Paint();

    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
    mCirlceViewColor = array.getColor(R.styleable.CircleView_roundColor, Color.BLACK);
    mCirlceViweProgressColor = array.getColor(R.styleable.CircleView_roundProgressColor, Color.RED);
    mTextColor = array.getColor(R.styleable.CircleView_textColor, Color.BLUE);
    mTextSize = array.getDimension(R.styleable.CircleView_textSize, 40);
    mRoudWidth = array.getDimension(R.styleable.CircleView_roundWidth, 6);
    max = array.getInteger(R.styleable.CircleView_max, 100);
    mStyle = array.getInt(R.styleable.CircleView_style, 0);

    array.recycle();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    /**
     * 先画圆,直接使用draw Circle
     * Draw the specified circle using the specified paint. If radius is <= 0,
     * then nothing will be drawn. The circle will be filled or framed based
     * on the Style in the paint.
     *
     public void drawCircle(float cx, float cy, float radius, @NonNull Paint paint)
     * @param cx     The x-coordinate of the center of the cirle to be drawn
     * @param cy     The y-coordinate of the center of the cirle to be drawn
     * @param radius The radius of the cirle to be drawn
     * @param paint  The paint used to draw the circle
     */

    int cx = getWidth() / 2;

    int radius = (int) (cx - mRoudWidth / 2);//设置圆环的半径
    mPaint.setColor(mCirlceViewColor); //设置圆环的颜色
    mPaint.setStyle(Paint.Style.STROKE); //设置空心
    mPaint.setStrokeWidth(mRoudWidth); //设置圆环的宽度
    mPaint.setAntiAlias(true);  //消除锯齿
    canvas.drawCircle(cx, cx, radius, mPaint);

    /**
     * 有了外层的圆,现在绘制圆内的文字.使用drawText
     * Draw the text, with origin at (x,y), using the specified paint. The
     * origin is interpreted based on the Align setting in the paint.
     *
     public void drawText(@NonNull String text, float x, float y, @NonNull Paint paint)
     * @param text  The text to be drawn
     * @param x     The x-coordinate of the origin of the text being drawn
     * @param y     The y-coordinate of the baseline of the text being drawn
     * @param paint The paint used for the text (e.g. color, size, style)
     */

    mPaint.setStrokeWidth(0);
    mPaint.setColor(mTextColor);
    mPaint.setTextSize(mTextSize);
    int percent = (int) (((float) mProgress / (float) max) * 100);
    float textWidth = mPaint.measureText(percent + "%");
    canvas.drawText(percent + "%", cx - textWidth / 2, cx + mTextSize / 2, mPaint);

    /**
     * 画圆弧 ，画圆环的进度
     */

    mPaint.setStrokeWidth(mRoudWidth); //设置圆环的宽度
    mPaint.setColor(mCirlceViweProgressColor);  //设置进度的颜色
    RectF oval = new RectF(cx - radius, cx - radius, cx
        + radius, cx + radius);  //用于定义的圆弧的形状和大小的界限

    switch (mStyle) {
      case STROKE: {
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, 0, 360 * mProgress / max, false, mPaint);  //根据进度画圆弧
        break;
      }
      case FILL: {
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        if (mProgress != 0) {
          canvas.drawArc(oval, 0, 360 * mProgress / max, true, mPaint);  //根据进度画圆弧
        }
        break;
      }
    }
  }

  public void setProgress(int progress) {
    this.mProgress = progress;
  }

  private int getProgress() {
    return this.mProgress;


  }

  private void setMax(int max) {
    this.max = max;
  }

  private int getMax() {
    return max;
  }
}
