/**
 * 
 * Copyright � ARP online,inc
 */
package com.test.app.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.app.R;


/**
 * @author Welcome
 * 
 *         list adaper Class
 */
@SuppressLint("InflateParams")
public class ListPopupAdapter extends BaseAdapter {

	private Context mContext;
	private String[] mData;
	private ViewHolder mHolder = null;

	public ListPopupAdapter(Context context, String[] data) {
		this.mContext = context;
		this.mData = data;
	}

	/* private view holder class */
	private class ViewHolder {
		TextView txt_menu;
		View view_menu;
	}

	@Override
	public int getCount() {
		return mData.length;
	}

	@Override
	public Object getItem(int position) {

		return mData[position];
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@SuppressLint({ "NewApi", "InflateParams" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater mInflater = (LayoutInflater) mContext
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_popup_item, null);
			mHolder = new ViewHolder();
			mHolder.txt_menu = (TextView) convertView
					.findViewById(R.id.txt_menu);
			mHolder.view_menu = (View) convertView.findViewById(R.id.view_menu);

			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}

		mHolder.txt_menu.setText(mData[position]);

		mHolder.view_menu.setVisibility(View.GONE);

		if (position == 0 || position == 1 || position == 2)
			mHolder.view_menu.setVisibility(View.VISIBLE);
		
		if(mData.length>=5)
			mHolder.view_menu.setVisibility(View.VISIBLE);

		return convertView;
	}

}
