package prictise.com.application1.cusview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import prictise.com.application1.utils.Help;

/**
 * @author zhisiyi
 * @date 18.7.28 14:06
 * @comment
 */
public class ClockView extends View {
    private final String TAG = ClockView.class.getSimpleName();

    private float mRadius;
    private float mRadius1;
    private Paint mPaint;
    private int mLeft, mTop;
    private int clockSize;
    int i = 30;
    float x = 0;
    float y = 0;
    float hudu = 0;
    float x1 = 0;
    float y1 = 0;
    float hudu1 = 0;
    private Handler mHandler = new Handler();

    public ClockView(Context context) {
        this(context, null, 0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.)
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) getLayoutParams();
        mRadius = Help.dp2dip(context, 100);
        mRadius1 = Help.dp2dip(context, 90);
        clockSize = Help.sp2px(context, 11);
        Log.e(TAG, "mRadius = " + mRadius);
        mPaint = new Paint();
        hudu = (float) (2 * Math.PI / 360) * 6 * i;
        x = (float) (mRadius - Math.sin(hudu) * mRadius);
        y = (float) (mRadius + Math.cos(hudu) * mRadius);

        hudu1 = (float) (2 * Math.PI / 360) * 6 * i;
        x1 = (float) (mRadius1 - Math.sin(hudu1) * mRadius1);
        y1 = (float) (mRadius1 + Math.cos(hudu1) * mRadius1);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "线程：" + Thread.currentThread());
                i = i % 60;
                Log.e(TAG, "i = " + i);
                hudu = (float) (2 * Math.PI / 360) * 6 * i;
                x = (float) (mRadius - Math.sin(hudu) * mRadius);
                y = (float) (mRadius + Math.cos(hudu) * mRadius);
                mHandler.postDelayed(this, 1000);
                invalidate();
                i++;
            }
        };


        mHandler.postDelayed(runnable, 1000);

    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        mLeft = getLeft();
        mTop = getTop();

        Log.e(TAG, "mLeft = " + mLeft + ", mTop = " + mTop);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(clockSize);
        canvas.drawText("12", mRadius - 25, 38, mPaint);
        canvas.drawText("3", 2 * mRadius - 30, mRadius + 18, mPaint);
        canvas.drawText("6", mRadius - 14, 2 * mRadius - 10, mPaint);
        canvas.drawText("9", 10, mRadius + 17, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);

        canvas.drawLine(mRadius, mRadius, x, y, mPaint);

        canvas.drawLine(x1, y1, x, y, mPaint);
    }
}
