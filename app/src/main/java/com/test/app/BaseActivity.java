package com.test.app;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import com.test.app.adapter.ListPopupAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by welcome on 4/22/2018.
 */

public class BaseActivity extends AppCompatActivity {

    private ListPopupWindow mListPopupWindow;
    private List<String> mData;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void setLeftRightViews(final Activity activity, View view)
    {

        mData = new ArrayList<>();

        mListPopupWindow = new ListPopupWindow(activity);

        if(view.getId()==R.id.img_left_menu)
        {
            mData.add("Home");
            mData.add("Profile");
            mData.add("Sync");
        }
        else
        {
            mData.add("About");
            mData.add("News");
        }


        mListPopupWindow.setAdapter(new ListPopupAdapter(BaseActivity.this,
                mData.toArray(new String[mData.size()])));

        mListPopupWindow.setAnchorView(view);
        mListPopupWindow.setWidth(350);
        mListPopupWindow.setHeight(ViewPager.LayoutParams.WRAP_CONTENT);
        mListPopupWindow.setModal(true);

        mListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mListPopupWindow.dismiss();
                Toast.makeText(activity," Clicked on : "+mData.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        mListPopupWindow.dismiss();
        mListPopupWindow.show();
    }
}
