package prictise.com.application1.gridview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import prictise.com.application1.R;

/**
 * @author zhisiyi
 * @date 18.11.2 19:34
 * @comment
 */
public class RecyclerGridViewAdapter extends RecyclerView.Adapter<RecyclerGridViewAdapter.CusViewHolder> {

    private LayoutInflater mInflate;
    private Context mContext;
    private ArrayList<PassModel> mData = new ArrayList<>(4);
    private OnItemClickListener mOnItemClickListener;

    public RecyclerGridViewAdapter(Context context) {
        this.mContext = context;
        PassModel passModel = new PassModel();
        for (int i = 0; i < 4; i++) {
            mData.add(passModel);
        }
    }

    @Override
    public CusViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mInflate = LayoutInflater.from(viewGroup.getContext());
        View view = mInflate.inflate(R.layout.adapter_recycle_pass, viewGroup, false);
        CusViewHolder myViewHolder = new CusViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CusViewHolder cusViewHolder, final int position) {

        PassModel passModel = mData.get(position);

//        if (passModel.isInput) {
//            cusViewHolder.tv.setBackgroundResource(R.drawable.pass_select);
//        } else {
//            cusViewHolder.tv.setBackgroundResource(R.drawable.pass_no_select);
//        }

        cusViewHolder.tv.setText("测试");
        if (mOnItemClickListener != null) {
            cusViewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mOnItemClickListener.onClick(position);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class CusViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public CusViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_pass);
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
