package prictise.com.application1.viewpager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;
import prictise.com.application1.viewpager.bean.SourceViewPagerBean;

/**
 * @Author zhisiyi
 * @Date 2020.08.26 10:12
 * @Comment
 */
public class SourceViewPagerAdapter extends PagerAdapter {

  private String TAG = SourceViewPagerAdapter.class.getSimpleName();
  private Context mCtx;

  private ArrayList<SourceViewPagerBean> mData = new ArrayList<>();

  private ViewPagerOnClick mViewPagerOnClick;

  public SourceViewPagerAdapter(Context ctx) {
    LogcatUtils.showDLog(TAG, "SourceViewPagerAdapter");
    mCtx = ctx;
  }

  public void setViewPagerOnClick(ViewPagerOnClick l) {
    mViewPagerOnClick = l;
  }

  /**
   * 返回这个ViewPager一共有多少个子页面 可以把所有要展示的页面存到数组或集合中然后返回长度 这个函数在ViewPager对象创建后自动执行 且只会执行一次
   *
   * @return ViewPager 一共有多少子页面
   */
  @Override
  public int getCount() {
    LogcatUtils.showDLog(TAG, "getCount");
    return Integer.MAX_VALUE;
  }

  /**
   * 这个方法就返回 这两个参数是否相等 具体作用暂不知
   */
  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
    LogcatUtils.showDLog(TAG, "isViewFromObject");
    LogcatUtils.showDLog(TAG, "isViewFromObject view = " + view);
    LogcatUtils.showDLog(TAG, "isViewFromObject o = " + o);
    return view == o;
  }

  @Override
  public void startUpdate(@NonNull ViewGroup container) {
    LogcatUtils.showDLog(TAG, "startUpdate");
    super.startUpdate(container);
  }

  /**
   * ① ViewPager会预加载子页面 在预加载时会自动调用这个方法 虽然一共有几个子页面已经知道了，但这些页面并未创建出来 我们要在这个方法中创建子页面 并且将要展示的内容添加到子页面中 ②
   * 当ViewPage第一次加载时会执行这个函数并且执行两次 第一次执行加载第0页面， 第二次执行加载第1页 ③ 当从第0页滑动到第1页后 会触发这个函数 预加载第2页 当从第1页滑到第2页后
   * 会触发这个函数 预加载第3页 以类此推
   *
   * 同理，反向滑动也是一样的
   *
   * ④ 这个方法要返回预加载的子页面要显示的组件对象
   *
   * @param container 使用这个适配器的 ViewPager 对象
   * @param position 预加载页面（要创建的页面）的编号（从0开始计）
   * @return 预加载的子页面要显示的组件对象
   */
  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, final int position) {
    LogcatUtils.showDLog(TAG, "instantiateItem position = " + position % mData.size());
    final SourceViewPagerBean sourceViewPagerBean = mData.get(position % mData.size());
    LogcatUtils.showDLog(TAG, "instantiateItem sourceViewPagerBean = " + sourceViewPagerBean);
    View view = LayoutInflater.from(mCtx).inflate(R.layout.adapter_view_pager_source, null);
    ImageView imageView = view.findViewById(R.id.iv_show);

    if (mViewPagerOnClick != null) {
      view.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          mViewPagerOnClick.itemClick(sourceViewPagerBean, position % mData.size());
        }
      });
    }

    Glide.with(mCtx)
        .asBitmap()//只加载静态图片，如果是git图片则只加载第一帧。
        .load(sourceViewPagerBean.imgUrl)
        .placeholder(R.mipmap.ic_launcher_round)
        .error(R.mipmap.ic_launcher_round)
        .into(imageView);
    container.addView(view);
    return view;
  }

  /**
   * ① 这个方法用来销毁某个子页面释放资源 ② 当刚进入 ViewPager 时 这时已经加载了 0号页与1号页 此时用户处于0号页 用户可能还要滑到1号页 此时哪个页面都不会销毁 ②
   * 当用户从0号页滑到1号页 这时已加载的页面时 0、1、2 页 此时用户即可能滑到 0号页 也可能滑到 2号页  所以也不会触发这个函数 ③ 当用户从 1号页 滑到 2号页 此时已加载的有
   * 0、1、2、3 页 用户处于 2号页 不可能会直接滑到0号页 所以，此时会触发这个函数来销毁 0号页 通过这个规律 我们就能知道这个方法何时会调用了
   *
   * @param container 使用这个适配器的 ViewPager 对象
   * @param position 要销毁的页面编号
   * @param object 在 instantiateItem()方法 中 return的组件对象会传给它
   */
  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    LogcatUtils.showDLog(TAG, "destroyItem position = " + position);
    LogcatUtils.showDLog(TAG, "destroyItem object = " + object);
    // 根据 object 删除子页面
    // 子页面都是以 k-v 的形式管理的
    // 而 instantiateItem()方法 return的对象就是当时创建的那个子页面的key，所以destroyItem()方法的第三个参数就是页面的key
    // 所以可以根据第三个参数销毁子页面
    container.removeView((View) object);
  }

  @Override
  public void finishUpdate(@NonNull ViewGroup container) {
    LogcatUtils.showDLog(TAG, "finishUpdate");
    super.finishUpdate(container);
  }

  public void updateData(ArrayList<SourceViewPagerBean> data) {
    mData.clear();
    mData.addAll(data);
    notifyDataSetChanged();
  }

  public interface ViewPagerOnClick {

    public void itemClick(SourceViewPagerBean sourceViewPagerBean, int p);
  }

}
