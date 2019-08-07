package prictise.com.application1.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;
import prictise.com.application1.utils.ToastUtil;

/**
 * @Author zhi
 * @Date 2019/8/7 5:10
 * @Describer
 */
public class Fragment1 extends Fragment {

  private final String TAG = Fragment1.class.getSimpleName();

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    ToastUtil.showShort("onAttach");
    LogcatUtils.showDLog(TAG, "Fragment1 - onAttach(context)");
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    LogcatUtils.showDLog(TAG, "Fragment1 - onAttach(activity)");
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      onAttachToContext(activity);
    }
  }

  /*
   * Called when the fragment attaches to the context
   */
  protected void onAttachToContext(Context context) {
    //do something
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LogcatUtils.showDLog(TAG, "Fragment1 - onCreate()");
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    LogcatUtils.showDLog(TAG, "Fragment1 - onCreateView()");
    return inflater.inflate(R.layout.fragment_test1, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    LogcatUtils.showDLog(TAG, "Fragment1 - onActivityCreated()");
  }

  @Override
  public void onStart() {
    super.onStart();
    LogcatUtils.showDLog(TAG, "Fragment1 - onStart()");
  }

  @Override
  public void onResume() {
    super.onResume();
    LogcatUtils.showDLog(TAG, "Fragment1 - onResume()");
  }

  @Override
  public void onPause() {
    super.onPause();
    LogcatUtils.showDLog(TAG, "Fragment1 - onPause()");
  }

  @Override
  public void onStop() {
    super.onStop();
    LogcatUtils.showDLog(TAG, "Fragment1 - onStop()");
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    LogcatUtils.showDLog(TAG, "Fragment1 - onDestroyView()");
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    LogcatUtils.showDLog(TAG, "Fragment1 - onDestroy()");
  }

  @Override
  public void onDetach() {
    super.onDetach();
    LogcatUtils.showDLog(TAG, "Fragment1 - onDetach()");
  }
}
