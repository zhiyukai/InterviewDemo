package prictise.com.application1.cusListview.viewpagerListview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zhisiyi
 * @Date 2020.04.25 10:55
 * @Comment
 */
public class SecondPagerAdapter extends PagerAdapter {

  private String TAG = SecondPagerAdapter.class.getSimpleName();

  private Context mCtx;

  private ArrayList<SecondViewPagerModel> mData = new ArrayList<>();


  public SecondPagerAdapter(Context context) {
    mCtx = context;
  }

  @Override
  public int getCount() {
    return mData.size();
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view == object;
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    Holder holder = null;

    SecondViewPagerModel spm = mData.get(position);
    View view = LayoutInflater.from(mCtx).inflate(R.layout.fragment_second_viewpager_item, null);
    ImageView iv_show_pic = view.findViewById(R.id.iv_show_pic);
    if (container != null) {
      LogcatUtils.showDLog(TAG, "container.getChildCount() = " + container.getChildCount());
    }
    Glide.with(mCtx)
        .load(spm.picUrl)
        .into(iv_show_pic);

    container.addView(view);
    return view;
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView((View) object);
  }

  private static class Holder {

    ImageView ivBg;
  }

  public void setData(ArrayList<SecondViewPagerModel> data) {
    mData.clear();
    mData.addAll(data);
    notifyDataSetChanged();
  }

  public void appendData(ArrayList<SecondViewPagerModel> data) {
    mData.addAll(data);
    notifyDataSetChanged();
  }
}
