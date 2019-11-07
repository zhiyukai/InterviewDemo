package prictise.com.application1.asyncTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.OnClick;
import prictise.com.application1.BaseActivity;
import prictise.com.application1.R;

/**
 * @Author zhisiyi
 * @Date 2019.10.19 14:11
 * @Comment
 */
public class AsyncTaskActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_async_task);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.bt_lunch_async_task)
  public void onViewClicked() {
    CusAsync cusAsync = new CusAsync();
    cusAsync.execute();
  }

  static class CusAsync extends AsyncTask<String, Integer, Object> {

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(Object o) {
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
    }

    @Override
    protected void onCancelled() {
    }

    @Override
    protected Object doInBackground(String... strings) {
      publishProgress(1);
      return null;
    }
  }
}
