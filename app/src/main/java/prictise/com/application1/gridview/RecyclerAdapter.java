package prictise.com.application1.gridview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import prictise.com.application1.R;
import prictise.com.application1.utils.SharedPrefsUtil;

/**
 * @author zhisiyi
 * @date 18.8.3 11:32
 * @comment
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private LayoutInflater mInflate;
    private Context mContext;
    private List<String> mData;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerAdapter(Context context, LayoutInflater mInflate, List<String> mData) {
        this.mContext = context;
        this.mInflate = mInflate;
        this.mData = mData;
    }

    /**
     * @param viewGroup 父布局 RecyclerView
     * @param i         相当于position
     * @return ViewHolder
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = mInflate.inflate(R.layout.adapter_gridview_select, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        int color2 = mContext.getResources().getColor(R.color.white);

        String mTableName = SharedPrefsUtil.get(mContext, "a", "");
        if (!TextUtils.isEmpty(mTableName) && mTableName.equals(mData.get(position))) {
            myViewHolder.tv.setTextColor(color2);
            myViewHolder.tv.setBackgroundResource(R.mipmap.shizi_bg_press);
        } else {
            myViewHolder.tv.setTextColor(color2);//之前是color1
            myViewHolder.tv.setBackgroundResource(R.mipmap.shuzi_bg);
        }

        myViewHolder.tv.setText(mData.get(position));
        if (mOnItemClickListener != null) {
            myViewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mOnItemClickListener.onClick(position);
                    return false;
                }
            });
//            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickListener.onClick(position);
//                }
//            });
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * MyViewHolder中获得子布局中的view
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
