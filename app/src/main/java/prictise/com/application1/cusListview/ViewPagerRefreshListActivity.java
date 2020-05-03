package prictise.com.application1.cusListview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import prictise.com.application1.BaseActivity;
import prictise.com.application1.R;
import prictise.com.application1.cusListview.viewpagerListview.ActiveAdapter;
import prictise.com.application1.cusListview.viewpagerListview.ActiveModel;
import prictise.com.application1.cusListview.viewpagerListview.CusRecycleView;
import prictise.com.application1.cusListview.viewpagerListview.SecondPagerAdapter;
import prictise.com.application1.cusListview.viewpagerListview.SecondViewPagerModel;
import prictise.com.application1.cusListview.viewpagerListview.ViewPagerPullToRefreshLayout;

/**
 * @Author zhisiyi
 * @Date 2020.05.03 14:16
 * @Comment
 */
public class ViewPagerRefreshListActivity extends BaseActivity {

  @BindView(R.id.pull_icon)
  ImageView pullIcon;
  @BindView(R.id.refreshing_icon)
  ImageView refreshingIcon;
  @BindView(R.id.state_tv)
  TextView stateTv;
  @BindView(R.id.state_iv)
  ImageView stateIv;
  @BindView(R.id.head_view)
  RelativeLayout headView;
  @BindView(R.id.viewPager)
  ViewPager viewPager;
  @BindView(R.id.content_view)
  CusRecycleView contentView;
  @BindView(R.id.pullup_icon)
  ImageView pullupIcon;
  @BindView(R.id.loading_icon)
  ImageView loadingIcon;
  @BindView(R.id.loadstate_tv)
  TextView loadstateTv;
  @BindView(R.id.loadstate_iv)
  ImageView loadstateIv;
  @BindView(R.id.loadmore_view)
  RelativeLayout loadmoreView;
  @BindView(R.id.refresh_view)
  ViewPagerPullToRefreshLayout refreshView;
  private SecondPagerAdapter mSecondPagerAdapter;

  private ActiveAdapter mActiveAdapter;

  private ArrayList<SecondViewPagerModel> mData;
  private ArrayList<ActiveModel> mActiveModelList;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_viewpager_listview);
    ButterKnife.bind(this);
    initValue();
  }

  private void initValue() {
    mData = new ArrayList<>();
    mActiveModelList = new ArrayList<>();
    mSecondPagerAdapter = new SecondPagerAdapter(this);
    mActiveAdapter = new ActiveAdapter(this);
    contentView.setLayoutManager(new LinearLayoutManager(this));
    viewPager.setAdapter(mSecondPagerAdapter);

    SecondViewPagerModel secondViewPagerModel1 = new SecondViewPagerModel();
    secondViewPagerModel1.picUrl =
        "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1587785579&di=bbf01fe8138ca1a06072dfef7c065ab8&src=http://a3.att.hudong.com/14/75/01300000164186121366756803686.jpg";

    SecondViewPagerModel secondViewPagerModel2 = new SecondViewPagerModel();
    secondViewPagerModel2.picUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587795671000&di=3fdc39d8af33f1065ea3531e12dd9207&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fd009b3de9c82d1587e249850820a19d8bd3e42a9.jpg";
    mData.add(secondViewPagerModel1);
    mData.add(secondViewPagerModel2);

    mSecondPagerAdapter.setData(mData);

    ActiveModel activeModel = new ActiveModel();
    activeModel.title = "test1";
    activeModel.beginTime = "20200425";
    activeModel.endTime = "20200609";
    activeModel.count = "2131";
    activeModel.status = 1;

    ActiveModel activeModel1 = new ActiveModel();
    activeModel1.title = "test1";
    activeModel1.beginTime = "20200426";
    activeModel1.endTime = "20200612";
    activeModel1.count = "213166";
    activeModel1.status = 2;

    mActiveModelList.add(activeModel);
    mActiveModelList.add(activeModel1);

    mActiveAdapter.setData(mActiveModelList);

    contentView.setAdapter(mActiveAdapter);
  }
}
