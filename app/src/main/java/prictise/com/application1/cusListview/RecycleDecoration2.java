package prictise.com.application1.cusListview;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;

/**
 * @Author zhisiyi
 * @Date 2020.08.15 12:24
 * @Comment
 */
public class RecycleDecoration2 extends DividerItemDecoration {

  private Context mContext;
  private Paint mPaint;
  private Drawable mDivider;
  private int mDividerHeight = 2;//分割线高度，默认为1px
  private int mOrientation;//列表的方向：LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL
  private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

  /**
   * Creates a divider {@link ItemDecoration} that can be used with a {@link LinearLayoutManager}.
   *
   * @param context Current context, it will be used to access resources.
   * @param orientation Divider orientation. Should be {@link #HORIZONTAL} or {@link #VERTICAL}.
   */
  public RecycleDecoration2(Context context, int orientation) {
    super(context, orientation);
  }

  //获取分割线尺寸
  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
//    if (mOrientation == LinearLayoutManager.VERTICAL) {
//      outRect.set(0, 0, 0, mDividerHeight);
//    } else {
//      outRect.set(0, 0, mDividerHeight, 0);
//    }

    //不是第一个的格子都设一个上边和底部的间距  这些间隔大小可以自行修改
    int pos = parent.getChildLayoutPosition(view);  //当前条目的position
    int itemCount = state.getItemCount() - 1;           //最后一条的postion
    if (pos == itemCount) {   //最后一条
      outRect.bottom = 0;
      outRect.top = 10;
    } else {
      if (pos % 2 == 1) {  //下面一行

        outRect.bottom = 10;
        outRect.top = 10;
      } else { //上面一行
        if (pos == 0) {
          outRect.bottom = 10;
          outRect.top = 0;
        } else {
          outRect.top = 10;
          outRect.bottom = 10;
        }
      }
    }
  }
}
