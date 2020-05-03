package prictise.com.application1.cusListview.viewpagerListview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import prictise.com.application1.R;

/**
 * @Author zhisiyi
 * @Date 2020.04.20 22:29
 * @Comment
 */
public class ActiveAdapter extends Adapter {

  private Context mCtx;
  private ArrayList<ActiveModel> mData = new ArrayList<>();

  public ActiveAdapter(Context context) {
    mCtx = context;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mCtx)
        .inflate(R.layout.adapter_active_item, parent, false);
    ActiveViewHolder viewHolder = new ActiveViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    final ActiveViewHolder viewHolder = (ActiveViewHolder) holder;
    //获取到条目对应的数据
    ActiveModel activeModel = mData.get(position);
    viewHolder.tvActiveName.setText(activeModel.title);
    viewHolder.tvTimeScope.setText(activeModel.beginTime + "-" + activeModel.endTime);
    viewHolder.tvCanYuRen.setText(activeModel.count + "");
    viewHolder.tvActiveNoStart.setText(activeModel.status + "");
    viewHolder.tvJoin.setText(activeModel.endTime);
  }

  @Override
  public int getItemCount() {
    return mData != null ? mData.size() : 0;
  }

  static class ActiveViewHolder extends ViewHolder {

    @BindView(R.id.tv_join)
    TextView tvJoin;
    @BindView(R.id.tv_active_name)
    TextView tvActiveName;
    @BindView(R.id.tv_active_no_start)
    TextView tvActiveNoStart;
    @BindView(R.id.tv_time_scope)
    TextView tvTimeScope;
    @BindView(R.id.tv_can_yu_ren)
    TextView tvCanYuRen;

    ActiveViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }

  public void setData(ArrayList<ActiveModel> data) {
    mData.clear();
    mData.addAll(data);
    notifyDataSetChanged();
  }

  public void appendData(ArrayList<ActiveModel> data) {
    mData.addAll(data);
    notifyDataSetChanged();
  }
}
