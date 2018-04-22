package com.test.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.app.R;
import com.test.app.data.ArticleData;

import java.util.List;

/**
 * Created by welcome on 4/22/2018.
 */

public class ArticleListItemAdapter extends BaseAdapter {

    private Context context;
    private List<ArticleData> rowItems;
    private ViewHolder holder = null;


    public ArticleListItemAdapter(Context context,
                                        List<ArticleData> items) {
        this.context = context;
        this.rowItems = items;
    }

    /* private view holder class */
    private class ViewHolder {
        TextView txt_title;
        TextView txt_sub_title;
        TextView txt_date;
        ImageView img_article;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int i) {
        return rowItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.article_list_item,
                    null);
            holder = new ViewHolder();
            holder.txt_title = (TextView) convertView
                    .findViewById(R.id.txt_title);
            holder.txt_sub_title = (TextView)convertView.findViewById(R.id.txt_sub_title);
            holder.txt_date = (TextView) convertView
                    .findViewById(R.id.txt_date);
            holder.img_article =(ImageView)convertView.findViewById(R.id.img_article_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ArticleData rowItem = (ArticleData) getItem(position);

        holder.txt_title.setText(rowItem.getTitle());
        holder.txt_sub_title.setText(rowItem.getByline());
        holder.txt_date.setText(rowItem.getPublished_date());


        return convertView;
    }
}
