package com.example.dojoy.myapplication.redPoint;

import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dojoy.myapplication.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RedPointListAct extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.activity_red_point_list)
    RelativeLayout activityRedPointList;
    TestAdapter ada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_point_list);
        ButterKnife.bind(this);
        list.setLayoutManager(new LinearLayoutManager(RedPointListAct.this, LinearLayoutManager.VERTICAL, false));
        ada = new TestAdapter(RedPointListAct.this);
        list.setAdapter(ada);
        ada.setNewData(getda());

    }

    private List<String> getda() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            arrayList.add("当前位置：" +
                    "");
        }

        return arrayList;
    }
}

class TestAdapter extends BaseQuickAdapter<String> {
    HashSet<Integer> mRemoved = new HashSet<Integer>();

    public TestAdapter(Context context) {
        super(context, R.layout.testtest, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.text, s).setText(R.id.point, baseViewHolder.getPosition() + "");
        TextView po = baseViewHolder.getView(R.id.point);
        final int position = baseViewHolder.getPosition();
        boolean visiable = !mRemoved.contains(position);
        po.setVisibility(visiable ? View.VISIBLE : View.GONE);
        if (visiable) {

            po.setText(String.valueOf(position));
            po.setTag(position);
            GooViewListener mGooListener = new GooViewListener(mContext, po) {
                @Override
                public void onDisappear(PointF mDragCenter) {
                    super.onDisappear(mDragCenter);
                    mRemoved.add(position);
                    notifyDataSetChanged();
                    Utils.showToast(mContext, "Cheers! We have get rid of it!");
                }

                @Override
                public void onReset(boolean isOutOfRange) {
                    super.onReset(isOutOfRange);
                    notifyDataSetChanged();
                    Utils.showToast(mContext, isOutOfRange ? "Are you regret?" : "Try again!");
                }
            };
            po.setOnTouchListener(mGooListener);
        }
    }
}
