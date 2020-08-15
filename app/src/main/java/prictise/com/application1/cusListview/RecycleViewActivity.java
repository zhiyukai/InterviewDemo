package prictise.com.application1.cusListview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import prictise.com.application1.R;
import prictise.com.application1.bean.PositionBean;
import prictise.com.application1.cusListview.PopupWindowAdapter.OnPositionItemClickListener;

/**
 * @Author zhisiyi
 * @Date 2020.08.15 13:47
 * @Comment
 */
public class RecycleViewActivity extends Activity {

  private RecyclerView recyclerView;
  private PopupWindowAdapter popupWindowAdapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycle_view);
    initValue();
    initListener();
  }

  private void initListener() {
    popupWindowAdapter.setOnItemClickListener(new OnPositionItemClickListener() {
      @Override
      public void onItemClick(Object o, int position) {

      }
    });
  }

  private void initValue() {
    ArrayList<PositionBean> data = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      PositionBean positionBean = new PositionBean();
      positionBean.positionString = "postion " + i;
      data.add(positionBean);
    }
    recyclerView = findViewById(R.id.recyclerview);
    popupWindowAdapter = new PopupWindowAdapter(this);
//    recyclerView
//        .addItemDecoration(new RecycleDecoration(this, RecycleDecoration.VERTICAL_LIST));
    recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    popupWindowAdapter.setData(data);
    recyclerView.setAdapter(popupWindowAdapter);
  }


}
