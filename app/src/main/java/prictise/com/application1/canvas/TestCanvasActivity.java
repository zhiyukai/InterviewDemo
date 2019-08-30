package prictise.com.application1.canvas;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import prictise.com.application1.R;

/**
 * @Author zhisiyi
 * @Date 2019.08.21 10:18
 * @Comment
 */
public class TestCanvasActivity extends Activity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_canvas);
//    TestCanvasView testCanvasView = new TestCanvasView(this);
//    setContentView(testCanvasView);
  }
}
