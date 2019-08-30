package prictise.com.application1.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;

public class StandardActivity extends Activity {

  public String TAG = StandardActivity.class.getSimpleName();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_standard);
    Log.e(TAG, "onCreate");
    Button bt = (Button) findViewById(R.id.bt_lanch_standard);
    bt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(StandardActivity.this, StandardActivity2.class));
      }
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.e(TAG, "onStart");
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.e(TAG, "onResume");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Log.e(TAG, "onRestart");
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    Log.e(TAG, "onNewIntent");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Log.e(TAG, "onSaveInstanceState");
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    Log.e(TAG, "onRestoreInstanceState");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.e(TAG, "onPause");
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.e(TAG, "onStop");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.e(TAG, "onDestroy");
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    LogcatUtils.showELog(TAG, "onConfigurationChanged(Configuration newConfig)");
  }
}
