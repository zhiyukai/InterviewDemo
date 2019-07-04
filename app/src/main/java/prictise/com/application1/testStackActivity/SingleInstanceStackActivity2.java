package prictise.com.application1.testStackActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import prictise.com.application1.BaseActivity;
import prictise.com.application1.R;

public class SingleInstanceStackActivity2 extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_stack_single_instance2);
    ButterKnife.bind(this);
  }
}
