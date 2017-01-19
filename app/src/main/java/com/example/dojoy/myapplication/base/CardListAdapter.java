package com.example.dojoy.myapplication.base;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dojoy.myapplication.R;


/**
 * Created by dojoy on 2016/8/1.
 */
public class CardListAdapter extends BaseQuickAdapter<Card> {


	public CardListAdapter (Context context) {
		super (context, R.layout.liu_cardlist_item, null);
	}

	@Override
	protected void convert (BaseViewHolder baseViewHolder, final Card card) {
		baseViewHolder.setText (R.id.itemCardNo, "NO:  " + card.getUserCardNo ());


	}
}
