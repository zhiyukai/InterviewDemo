package prictise.com.application1.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zhi
 * @Date 2019/8/7 5:10
 * @Describer
 */
public class Fragment2 extends Fragment {

  private final String TAG = Fragment2.class.getSimpleName();

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    LogcatUtils.showDLog(TAG, "Fragment2 - onAttach()");
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LogcatUtils.showDLog(TAG, "Fragment2 - onCreate()");
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    LogcatUtils.showDLog(TAG, "Fragment2 - onCreateView()");
    return inflater.inflate(R.layout.fragment_test2, container, false);
  }

  @Override
  public void onStart() {
    super.onStart();
    LogcatUtils.showDLog(TAG, "Fragment2 - onStart()");
  }

  @Override
  public void onResume() {
    super.onResume();
    LogcatUtils.showDLog(TAG, "Fragment2 - onResume()");
  }

  @Override
  public void onPause() {
    super.onPause();
    LogcatUtils.showDLog(TAG, "Fragment2 - onPause()");
  }

  @Override
  public void onStop() {
    super.onStop();
    LogcatUtils.showDLog(TAG, "Fragment2 - onStop()");
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    LogcatUtils.showDLog(TAG, "Fragment2 - onDestroyView()");
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    LogcatUtils.showDLog(TAG, "Fragment2 - onDestroy()");
  }

  @Override
  public void onDetach() {
    super.onDetach();
    LogcatUtils.showDLog(TAG, "Fragment2 - onDetach()");
  }
}
