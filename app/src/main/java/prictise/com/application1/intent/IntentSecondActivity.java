package prictise.com.application1.intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import prictise.com.application1.BaseActivity;
import prictise.com.application1.R;

/**
 * @Author zhisiyi
 * @Date 2019.10.10 19:16
 * @Comment
 */
public class IntentSecondActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intent_test2);
    ButterKnife.bind(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

}
