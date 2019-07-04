package prictise.com.application1.testStackActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.BaseActivity;
import prictise.com.application1.R;

public class MainStackActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_stack_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.bt_lanch_singleInstacne)
  public void lanchTestStackSingleInstanceActivity() {
    startActivity(new Intent(this, SingleInstanceStackActivity.class));
  }

  @OnClick(R.id.bt_lanch_single_top)
  public void lanchTestSingleTopActivity() {
    startActivity(new Intent(this, SingleTopStackActivity.class));
  }

  @OnClick(R.id.bt_lanch_single_task)
  public void lanchTestSingleTaskStackActivity() {
    startActivity(new Intent(this, SingleTaskStackActivity.class));
  }
}
