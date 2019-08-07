package prictise.com.application1.fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;
import prictise.com.application1.utils.ToastUtil;

/**
 * @author zhisiyi
 * @date 18.7.23 17:00
 * @comment
 */
public class FragmentMainActivity extends Activity {

  private final String TAG = FragmentMainActivity.class.getSimpleName();

  @BindView(R.id.fl_main)
  FrameLayout flMain;
  @BindView(R.id.bt_add)
  Button btAdd;
  @BindView(R.id.bt_remove)
  Button btRemove;
  @BindView(R.id.bt_replace)
  Button btReplace;
  @BindView(R.id.bt_hide)
  Button btHide;
  @BindView(R.id.bt_show)
  Button btShow;

  FragmentTransaction mFragmentTransaction;
  Fragment1 fragment1;
  Fragment2 fragment2;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_fragment);
    ButterKnife.bind(this);

    initValue();
  }

  private void initValue() {
    fragment1 = new Fragment1();
    fragment2 = new Fragment2();
  }

  @OnClick({R.id.bt_add, R.id.bt_remove, R.id.bt_replace, R.id.bt_hide, R.id.bt_show})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.bt_add:
        mFragmentTransaction = getFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.fl_main, fragment1);
        mFragmentTransaction.commitAllowingStateLoss();
        break;
      case R.id.bt_remove:
        mFragmentTransaction = getFragmentManager().beginTransaction();
        mFragmentTransaction.remove(fragment1);
        mFragmentTransaction.commitAllowingStateLoss();
        break;
      case R.id.bt_replace:
        mFragmentTransaction = getFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.fl_main, fragment2);
        mFragmentTransaction.commitAllowingStateLoss();
        break;
      case R.id.bt_hide:
        ToastUtil.showShort("hide");
        mFragmentTransaction = getFragmentManager().beginTransaction();
        mFragmentTransaction.hide(fragment1);
        mFragmentTransaction.commitAllowingStateLoss();
        break;
      case R.id.bt_show:
        ToastUtil.showShort("show");
        mFragmentTransaction = getFragmentManager().beginTransaction();
        mFragmentTransaction.show(fragment1);
        mFragmentTransaction.commitAllowingStateLoss();
        break;
      default:
        break;
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onSaveInstanceState()");
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onRestoreInstanceState()");
  }

  @Override
  protected void onStart() {
    super.onStart();
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onStart()");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onRestart()");
  }

  @Override
  protected void onResume() {
    super.onResume();
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onResume()");
  }

  @Override
  protected void onPostResume() {
    super.onPostResume();
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onPostResume()");
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onNewIntent()");
  }

  @Override
  protected void onPause() {
    super.onPause();
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onPause()");
  }

  @Override
  protected void onStop() {
    super.onStop();
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onStop()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onDestroy()");
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onConfigurationChanged()");
  }

  @Override
  public void onContentChanged() {
    super.onContentChanged();
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onContentChanged()");
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onWindowFocusChanged()");
  }

  @Override
  public boolean dispatchKeyEvent(KeyEvent event) {
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -dispatchKeyEvent()");
    return super.dispatchKeyEvent(event);
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -dispatchTouchEvent()");
    return super.dispatchTouchEvent(ev);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    LogcatUtils.showDLog(TAG, "FragmentMainActivity -onCreateOptionsMenu()");
    return super.onCreateOptionsMenu(menu);
  }
}
