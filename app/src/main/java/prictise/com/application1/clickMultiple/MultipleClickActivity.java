package prictise.com.application1.clickMultiple;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.BaseActivity;
import prictise.com.application1.R;

/**
 * @Author zhiyukai
 * @Date 2020.08.28 20:25
 * @Comment
 */
public class MultipleClickActivity extends BaseActivity {

  @BindView(R.id.bt_click_mutiple)
  Button btClickMutiple;

  int counts = 0;//点击次数
  long DURATION = (5 * 1000);//规定有效时间
  long lastStartTime = 0L;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_click_mutiple);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.bt_click_mutiple)
  public void onViewClicked() {

    long currentClickTime = SystemClock.elapsedRealtime();
    if (currentClickTime - lastStartTime > DURATION) {
      counts = 0;
      lastStartTime = currentClickTime;
    }
    counts++;
    if (counts == 4) {
      Toast.makeText(this,
          "个人微信Http地址: $wechatHttpNet" +
              "\n 个人微信Socket地址: $wechatSocketNet" +
              "\n 企业微信http地址: $weworkHttpNet" +
              "\n 企业微信Socket地址: $weworSocketNet ", Toast.LENGTH_LONG).show();
    }
  }
}
