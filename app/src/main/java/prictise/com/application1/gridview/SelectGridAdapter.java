package prictise.com.application1.gridview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import prictise.com.application1.R;
import prictise.com.application1.utils.Constants;
import prictise.com.application1.utils.SharedPrefsUtil;

/**
 * @author zhisiyi
 * @date 18.8.3 10:03
 * @comment
 */
public class SelectGridAdapter extends BaseAdapter {


    private Context activity = null;
    List<String> itemList;
    private int mLayerIndex;

    /**
     * 自定义构造方法
     *
     * @param activity
     * @param //list
     */
    public SelectGridAdapter(Context activity, List<String> itemList) {
        this.activity = activity;
        this.itemList = itemList;

    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setLayerIndex(int mLayerIndex) {
        this.mLayerIndex = mLayerIndex;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            // 下拉项布局
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_gridview_select, null);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        int color1 = activity.getResources().getColor(R.color.gridText);
        int color2 = activity.getResources().getColor(R.color.white);

        String mTableName = SharedPrefsUtil.get(activity, "a", "");
        if (!TextUtils.isEmpty(mTableName) && mTableName.equals(itemList.get(position))) {
//			holder.iv_icon.setVisibility(View.VISIBLE);
            holder.tv_name.setTextColor(color2);
            holder.tv_name.setBackgroundResource(R.mipmap.shizi_bg_press);
        } else {
            holder.tv_name.setTextColor(color2);//之前是color1
            holder.tv_name.setBackgroundResource(R.mipmap.shuzi_bg);
//			holder.iv_icon.setVisibility(View.GONE);
        }
        holder.tv_name.setText(itemList.get(position));

        return convertView;
    }

    class ViewHolder {
        //		ImageView iv_icon;
        TextView tv_name;
    }
}