package prictise.com.application1.eventDispatch;

import static prictise.com.application1.utils.Constants.EventDispatchActivity_TAG;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
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
    final int count = getChildCount();
    int left = 0;
    int right = 0;
    int top = 0;
    int bottom = 0;
    int childMeasureWidth = 0;
    int childMeasureHeight = 0;
    int layoutWidth = 0;    // 容器已经占据的宽度
    int layoutHeight = 0;   // 容器已经占据的宽度
    int maxChildHeight = 0; //一行中子控件最高的高度，用于决定下一行高度应该在目前基础上累加多少
    for(int i = 0; i<count; i++){
      View child = getChildAt(i);
      //注意此处不能使用getWidth和getHeight，这两个方法必须在onLayout执行完，才能正确获取宽高
      childMeasureWidth = child.getMeasuredWidth();
      childMeasureHeight = child.getMeasuredHeight();
      if(layoutWidth<getWidth()){
        //如果一行没有排满，继续往右排列
        left = layoutWidth;
        right = left+childMeasureWidth;
        top = layoutHeight;
        bottom = top+childMeasureHeight;
      } else{
        //排满后换行
        layoutWidth = 0;
        layoutHeight += maxChildHeight;
        maxChildHeight = 0;

        left = layoutWidth;
        right = left+childMeasureWidth;
        top = layoutHeight;
        bottom = top+childMeasureHeight;
      }

      layoutWidth += childMeasureWidth;  //宽度累加
      if(childMeasureHeight>maxChildHeight){
        maxChildHeight = childMeasureHeight;
      }

      //确定子控件的位置，四个参数分别代表（左上右下）点的坐标值
      child.layout(left, top, right, bottom);
    }

  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    // 计算出所有的childView的宽和高
    measureChildren(widthMeasureSpec, heightMeasureSpec);
    //测量并保存layout的宽高(使用getDefaultSize时，wrap_content和match_perent都是填充屏幕)
    //稍后会重新写这个方法，能达到wrap_content的效果
    setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
        getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));

  }
}
