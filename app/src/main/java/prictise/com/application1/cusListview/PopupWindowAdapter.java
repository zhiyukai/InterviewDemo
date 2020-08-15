package prictise.com.application1.cusListview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import prictise.com.application1.R;
import prictise.com.application1.bean.PositionBean;

/**
 * @Author zhisiyi
 * @Date 2020.04.20 22:29
 * @Comment
 */
public class PopupWindowAdapter extends Adapter {

  private Context mCtx;
  private ArrayList<PositionBean> mData = new ArrayList<>();

  private OnPositionItemClickListener onItemClickListener;

  public PopupWindowAdapter(Context context) {
    mCtx = context;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mCtx)
        .inflate(R.layout.popup_window_list_item, parent, false);
    PopupWindowAdapterViewHolder viewHolder = new PopupWindowAdapterViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    final PopupWindowAdapterViewHolder viewHolder = (PopupWindowAdapterViewHolder) holder;
    //获取到条目对应的数据
    final PositionBean activeModel = mData.get(position);
    final int p = position;
    viewHolder.tv_popup_list_item.setText(activeModel.positionString);

    viewHolder.itemView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        onItemClickListener.onItemClick(activeModel, p);
      }
    });
  }

  @Override
  public int getItemCount() {
    return mData != null ? mData.size() : 0;
  }

  static class PopupWindowAdapterViewHolder extends ViewHolder {

    @BindView(R.id.tv_popup_list_item)
    TextView tv_popup_list_item;

    PopupWindowAdapterViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }

  public void setData(ArrayList<PositionBean> data) {
    mData.clear();
    mData.addAll(data);
    notifyDataSetChanged();
  }

  public void appendData(ArrayList<PositionBean> data) {
    mData.addAll(data);
    notifyDataSetChanged();
  }

  public void setOnItemClickListener(OnPositionItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  public interface OnPositionItemClickListener<T> {

    void onItemClick(T t, int position);
  }
}
