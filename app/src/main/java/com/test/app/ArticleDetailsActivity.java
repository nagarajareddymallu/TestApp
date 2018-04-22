package com.test.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.app.data.ArticleData;

public class ArticleDetailsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView img_left_menu;
    private ImageView img_right_menu;

    private TextView txt_title_detail;
    private TextView txt_abstract_detail;
    private ImageView img_detail;

    private ArticleData articleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        intializeViews();
        articleData = (ArticleData)getIntent().getExtras().get("selectedArticle");

        txt_title_detail.setText(articleData.getTitle());
        txt_abstract_detail.setText(articleData.getAbstract_key());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_left_menu:
                setLeftRightViews(ArticleDetailsActivity.this, img_left_menu);
                break;
            case R.id.img_right_menu:
                setLeftRightViews(ArticleDetailsActivity.this, img_right_menu);
                break;
            default:
                break;

        }
    }
    private void intializeViews()
    {

        img_left_menu = (ImageView) findViewById(R.id.img_left_menu);
        img_right_menu = (ImageView) findViewById(R.id.img_right_menu);
        img_left_menu.setOnClickListener(this);
        img_right_menu.setOnClickListener(this);

        txt_title_detail = (TextView) findViewById(R.id.txt_title_detail);
        txt_abstract_detail = (TextView) findViewById(R.id.txt_abstract_detail);
        img_detail = (ImageView) findViewById(R.id.img_article_detail);
    }

}
