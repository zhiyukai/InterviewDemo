package prictise.com.application1.cusview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import prictise.com.application1.R;

/**
 * @author zhisiyi
 * @date 18.8.1 10:56
 * @comment
 */
public class Switchbutton extends View {
    private Bitmap background;
    private Bitmap slide;
    private boolean state;
    private OnStateChangeListener onStateChangeListener;

    //new的时候调用
    public Switchbutton(Context context) {
        this(context, null);
    }

    //使用style的使用调用
    public Switchbutton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //写在布局文件里面的时候调用
    public Switchbutton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化两张图片
        //将资源 文件转成Bitmap对象
//      getResources() 包含图片资源的资源对象
//      R.drawable.background 图片资源的id
        background = BitmapFactory.decodeResource(getResources(), R.mipmap.f);
        slide = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    //将图片绘制到控件上面
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制
        //参数1.图片对象
        //参数2.x轴的开始位置
        //参数3.y轴的开始位置
        //参数4.画笔
        canvas.drawBitmap(background, 0, 0, null);//背景
        int left = 0;
        if (isTouch) {
            //滑块和手指一起移动
            left = currentX;//修正位置
            left = currentX - slide.getWidth() / 2;
            //处理边界问题
            if (left < 0) {
                left = 0;
            }
            if (left >= background.getWidth() - slide.getWidth()) {
                left = background.getWidth() - slide.getWidth();
            }
            canvas.drawBitmap(slide, left, 0, null);

        } else {
            //根据开关的状态绘制
            if (state) {
                left = background.getWidth() - slide.getWidth();

            } else {
                left = 0;

            }
            canvas.drawBitmap(slide, left, 0, null);
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将需要的宽设置进去.根据背景图
        setMeasuredDimension(background.getWidth(), background.getHeight());

    }

    //给外部提供一个设置开关的方法
    public void setState(boolean state) {
        this.state = state;
    }

    //处理事件
    private int currentX;
    private boolean isTouch;//判断是否在触摸状态

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                currentX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                currentX = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:
                currentX = (int) event.getX();
                isTouch = false;
                //当手指抬起的时候,应该判断一下滑板儿的位置,然后设置开关的状态
                int centerX = background.getWidth() / 2;
                if (currentX > centerX) {
                    state = true;
                } else {
                    state = false;
                }
                //调用接口里的方法
                onStateChangeListener.onStateChange(state);
                break;
        }
        //重新绘制
        invalidate();//在子线程中
        //postInvalidate();//在主线程中
        return true;
    }

    //接口回调
    public interface OnStateChangeListener {
        void onStateChange(boolean state);
    }

    //提供一个外部访问的方法
    public void setOnStateChange(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }
}
