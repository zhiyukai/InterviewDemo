package prictise.com.application1.otherApplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.R;

/**
 * @Author zhisiyi
 * @Date 2020.08.16 11:55
 * @Comment
 */
public class ArouseOtherApplication extends Activity {

  @BindView(R.id.bt_lunch_other)
  Button btLunchOther;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_arouse_other);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.bt_lunch_other)
  public void onViewClicked() {
    ComponentName componentName = new ComponentName(
        //这个是另外一个应用程序的包名
        "com.youyu.gao.xiao",
        //这个参数是要启动的Activity
        "com.youyu.gao.xiao.MainActivity");
    Intent intent = new Intent();
    //我们给他添加一个参数表示从apk1传过去的
    Bundle bundle = new Bundle();
    bundle.putString("from", "这是跳转过来的！来自测试");
    intent.putExtra("bundle", bundle);
    intent.setComponent(componentName);
    startActivity(intent);
  }
}
