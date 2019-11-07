package prictise.com.application1.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.BaseActivity;
import prictise.com.application1.R;

/**
 * @Author zhisiyi
 * @Date 2019.10.10 19:16
 * @Comment
 */
public class IntentActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intent_test);
    ButterKnife.bind(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @OnClick({R.id.bt_launch_distinct, R.id.bt_launch_hide})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.bt_launch_distinct:

        startActivity(new Intent(this, IntentSecondActivity.class));

        break;
      case R.id.bt_launch_hide:
        Intent intent = new Intent();
        intent.setAction("testHideIntent");
//        intent.addCategory("android.intent.category.DEFAULT");
        if (intent.resolveActivity(getPackageManager()) != null) {
          startActivity(intent);
        }
        break;
      default:
        break;
    }
  }
}
