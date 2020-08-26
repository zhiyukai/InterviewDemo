package prictise.com.application1.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;
import prictise.com.application1.utils.ToastUtil;
import prictise.com.application1.viewpager.adapter.SourceViewPagerAdapter;
import prictise.com.application1.viewpager.adapter.SourceViewPagerAdapter.ViewPagerOnClick;
import prictise.com.application1.viewpager.bean.SourceViewPagerBean;

/**
 * @Author zhisiyi
 * @Date 2020.08.26 09:51
 * @Comment
 */
public class ViewPagerSourceActivity extends Activity {

  private String TAG = "ViewPagerSourceActivity";
  private boolean isStop = false;//线程是否停止
  private static int PAGER_TIOME = 3000;//间隔时间
  private ViewPager mViewPager;

  private SourceViewPagerAdapter mSourceViewPagerAdapter;

  private ArrayList<SourceViewPagerBean> mData = new ArrayList<>();

  private ArrayList<ImageView> dos = new ArrayList<>();
  private LinearLayout mDotLinearLayout;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_pager_source);
    mDotLinearLayout = findViewById(R.id.ll_dot);
    SourceViewPagerBean sourceViewPagerBean = new SourceViewPagerBean();
    sourceViewPagerBean.imgUrl = "http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/picture/wechat_mements/wechat_moment04.png";
    SourceViewPagerBean sourceViewPagerBean2 = new SourceViewPagerBean();
    sourceViewPagerBean2.imgUrl = "http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/picture/wechat_mements/wechat_moment05.png";
    SourceViewPagerBean sourceViewPagerBean3 = new SourceViewPagerBean();
    sourceViewPagerBean3.imgUrl = "http://dragnet-sunlands.oss-cn-beijing.aliyuncs.com/android/picture/wechat_mements/wechat_moment06.png";

    mData.add(sourceViewPagerBean);
    mData.add(sourceViewPagerBean2);
    mData.add(sourceViewPagerBean3);
    intDos();
    mViewPager = findViewById(R.id.view_pager);

    mSourceViewPagerAdapter = new SourceViewPagerAdapter(this);
    mSourceViewPagerAdapter.updateData(mData);
    mViewPager.setAdapter(mSourceViewPagerAdapter);

    mSourceViewPagerAdapter.setViewPagerOnClick(new ViewPagerOnClick() {
      @Override
      public void itemClick(SourceViewPagerBean sourceViewPagerBean, int p) {
        ToastUtil.showShort("测试 p = " + p);
      }
    });
    mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
      @Override
      public void onPageScrolled(int i, float v, int i1) {
        LogcatUtils.showDLog(TAG, "onPageScrolled i = " + i % mData.size());
        LogcatUtils.showDLog(TAG, "onPageScrolled v = " + v);
        LogcatUtils.showDLog(TAG, "onPageScrolled i1 = " + i1 % mData.size());
      }

      @Override
      public void onPageSelected(int i) {
        LogcatUtils.showDLog(TAG, "onPageSelected i = " + i % mData.size());
        int p = i % mData.size();
        if (dos.size() > 2) {
          for (int a = 0; a < mData.size(); a++) {
            ImageView imageView = dos.get(a);
            if (a == p) {
              imageView.setImageResource(R.drawable.corner_second_no_start_bg);
            } else {
              imageView.setImageResource(R.drawable.awrad_count_shape_gradient);
            }
          }
        }
      }

      @Override
      public void onPageScrollStateChanged(int i) {
        LogcatUtils.showDLog(TAG, "onPageScrollStateChanged i = " + i % mData.size());
      }
    });
    int m = (Integer.MAX_VALUE / 2) % mData.size();
    int currentPosition = Integer.MAX_VALUE / 2 - m - 1;
    mViewPager.setCurrentItem(currentPosition);
    autoPlayView();

  }

  private void intDos() {
    int size = mData.size();
    for (int i = 0; i < size; i++) {
      ImageView iv = new ImageView(this);
      if (i == 0) {
        iv.setImageResource(R.drawable.corner_second_no_start_bg);
      } else {
        iv.setImageResource(R.drawable.awrad_count_shape_gradient);
      }
      // 设置空间大小
      LayoutParams layoutParams = new LayoutParams(10, 10);
      // 设置宽高
      layoutParams.setMargins(5, 0, 5, 0);
      // 把小圆点放到页面的LinnearLayout容器中
      mDotLinearLayout.addView(iv, layoutParams);
      // 把小圆点存放到集合中
      dos.add(iv);
    }
  }

  private void autoPlayView() {
    //自动播放图片
    new Thread(new Runnable() {
      @Override
      public void run() {
        while (!isStop) {
          runOnUiThread(new Runnable() {
            @Override
            public void run() {
              mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            }
          });
          try {
            Thread.sleep(PAGER_TIOME);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
  }
}
