package com.example.dojoy.myapplication.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.helputils.ZhUtils;

import java.util.ArrayList;

/**
 * 首页顶部6大菜单滑动适配器
 * Created by dojoy on 2016/6/7.
 */

public class HomeCoverAdapter extends FancyCoverFlowAdapter {
    private Context mContext;
    public ArrayList<HomeItem> list;
    public static final int MAX = Integer.MAX_VALUE;

    public HomeCoverAdapter() {
    }

    public HomeCoverAdapter(Context mContext, ArrayList<HomeItem> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public View getCoverFlowItem(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.liu_home_item_fancycoverflow, null);
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            //            int width = wm.getDefaultDisplay().getWidth();
            convertView.setLayoutParams(new FancyCoverFlow.LayoutParams(ZhUtils.DimenTrans.dip2px(mContext, 210), FancyCoverFlow.LayoutParams.WRAP_CONTENT));
            holder = new ViewHolder();
            //            holder.name = (TextView) convertView.findViewById(R.id.itemName);
            //            holder.content = (TextView) convertView.findViewById(R.id.itemContent);
            holder.img = (ImageView) convertView.findViewById(R.id.itemImg);
            holder.layout = (LinearLayout) convertView.findViewById(R.id.itemLayout);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //        final HomeItem item = getItem(position);
        final HomeItem item = getItem(position % list.size());

        //        holder.name.setText(item.getName());
        //        holder.content.setText(item.getContent());
        holder.img.setImageResource(item.getResId());
        return convertView;
    }

    @Override
    public int getCount() {
        //        return Integer.MAX_VALUE;
        return MAX;
        //        return list==null?0:list.size();
    }

    @Override
    public HomeItem getItem(int i) {
        return list.get(i % list.size());
        //        return list.get(i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class ViewHolder {
        //        TextView name;
        //        TextView content;
        LinearLayout layout;
        ImageView img;
    }

    //    public void setSelected(int position) {
    //        //        position = position ;
    //        position = position % list.size();
    ////        if (list != null) {
    ////            for (int i = 0; i < list.size(); i++) {
    ////                if (i == position) {
    ////                    list.get(i).setSelected(true);
    ////                } else {
    ////                    list.get(i).setSelected(false);
    ////                }
    ////            }
    ////        }
    //        notifyDataSetChanged();
    //
    //    }
}
