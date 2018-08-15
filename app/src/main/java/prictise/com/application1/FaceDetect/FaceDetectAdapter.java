package prictise.com.application1.FaceDetect;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import prictise.com.application1.R;

/**
 * @author zhisiyi
 * @date 18.8.10 15:43
 * @comment
 */
public class FaceDetectAdapter extends BaseAdapter {
    private Context mCtx;
    private ArrayList<Bitmap> mData = new ArrayList<>();

    public FaceDetectAdapter(Context context) {
        mCtx = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.adapter_face_detect_item, null);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_face_detect);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.iv.setImageBitmap(mData.get(position));
        return convertView;
    }

    public void setData(ArrayList<Bitmap> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    class Holder {
        ImageView iv;
    }
}
