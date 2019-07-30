package prictise.com.application1.cusGroupView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class CusGroupViewTest extends ViewGroup {

  public CusGroupViewTest(Context context) {
    super(context);
  }

  public CusGroupViewTest(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CusGroupViewTest(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {

  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    /**
     * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
     */
    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
    int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);


    // 计算出所有的childView的宽和高
    measureChildren(widthMeasureSpec, heightMeasureSpec);
  }
}
