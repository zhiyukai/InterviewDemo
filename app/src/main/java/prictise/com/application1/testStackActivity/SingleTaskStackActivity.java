package prictise.com.application1.testStackActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.BaseActivity;
import prictise.com.application1.R;

public class SingleTaskStackActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_stack_single_task);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.bt_lanch_singleInstacne)
  public void lanchTestStackSingleInstanceActivity() {
    startActivity(new Intent(this, SingleInstanceStackActivity.class));
  }

}
