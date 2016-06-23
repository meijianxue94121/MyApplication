package com.example.dojoy.myapplication.viewpager.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.viewpager.PhotoLoader;
import com.example.dojoy.myapplication.viewpager.info.SubjectsInfo;

import java.util.ArrayList;

/**
 * Created by leo on 16/5/7.
 */
public class PageAdapter extends PagerAdapter {
    private ArrayList<SubjectsInfo> subjectsInfos;
    private Context context;

    public PageAdapter(Context context, ArrayList<SubjectsInfo> subjectsInfos) {
        this.context = context;
        this.subjectsInfos = subjectsInfos;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
//        return subjectsInfos.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_movie);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        position =position%subjectsInfos.size();
        SubjectsInfo subjectsInfo = subjectsInfos.get(position);
        title.setText(subjectsInfo.getTitle());
        PhotoLoader.display(container.getContext(), imageView, subjectsInfo.getImages().getLarge(), context.getResources().getDrawable(R.mipmap.ic_loading));
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        View view = (View) object;
//        container.removeView(view);
    }

}
