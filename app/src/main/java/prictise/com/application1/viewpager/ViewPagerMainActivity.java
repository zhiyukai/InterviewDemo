package prictise.com.application1.viewpager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import prictise.com.application1.BaseActivity;
import prictise.com.application1.R;
import prictise.com.application1.viewpager.widget.CircleIndicator;
import prictise.com.application1.viewpager.widget.CircleViewPager;
import prictise.com.application1.viewpager.widget.SimpleCirclePageAdapter;

/**
 * @Author zhisiyi
 * @Date 2020.04.25 09:11
 * @Comment
 */
public class ViewPagerMainActivity extends BaseActivity {

  private CircleViewPager mCircleViewPager;
  private int[] imgIds = {R.mipmap.i1, R.mipmap.i2, R.mipmap.i3, R.mipmap.i4};
  private List<View> views;
  private CircleIndicator mCircleIndicator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_viewpager_main);
    mCircleViewPager = (CircleViewPager) findViewById(R.id.circle_page);
    mCircleIndicator = (CircleIndicator) findViewById(R.id.circle_indicator);
    views = new ArrayList<View>();
    for (int i = 0; i < imgIds.length; i++) {
      ImageView imageView = new ImageView(this);
      imageView.setImageResource(imgIds[i]);
      imageView.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
          // TODO Auto-generated method stub
          Toast.makeText(ViewPagerMainActivity.this, mCircleViewPager.getRealCurrentItem() + "",
              Toast.LENGTH_SHORT).show();
        }
      });
      views.add(imageView);
    }

    SimpleCirclePageAdapter adapter = new SimpleCirclePageAdapter(views);
    mCircleViewPager.setAdapter(adapter);
    mCircleIndicator.setCircleViewPager(mCircleViewPager);
//		mCircleViewPager.setIntervalTime(1000);
    mCircleViewPager.startAutoScroll();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
