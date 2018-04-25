package com.test.app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.app.adapter.ArticleListItemAdapter;
import com.test.app.constant.CommonConstants;
import com.test.app.controller.TestController;
import com.test.app.data.ArticleData;
import com.test.app.listeners.WebserviceResponse;
import com.test.app.util.Utils;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, WebserviceResponse {


    private TestController testController;

    private ImageView img_left_menu;
    private ImageView img_right_menu;
    private ListView lst_articles;

    public ArticleListItemAdapter articleListItemAdapter;

    private EditText edt_search;
    private TextView txt_title_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get Global Controller Class object (see application tag in AndroidManifest.xml)
        testController = (TestController) getApplicationContext();
        initializeViews();

        if (isOnline()) {
            testController.launchBarDialog(MainActivity.this,"Articles Request ","Articles request in progress...");
            testController.webserviceCall(CommonConstants.ARTICAL_URL,this);
        } else {
            Toast.makeText(this, "Need internet connection!", Toast.LENGTH_LONG).show();
        }

        edt_search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (articleListItemAdapter != null)
                    articleListItemAdapter.getFilter().filter(s.toString());
            }
        });

    }

    public void initializeViews() {
        edt_search = (EditText) findViewById(R.id.edt_search);
        txt_title_name = (TextView) findViewById(R.id.txt_title_name);
        edt_search.setVisibility(View.VISIBLE);
        txt_title_name.setVisibility(View.GONE);
        lst_articles = (ListView) findViewById(R.id.lst_articles);
        img_left_menu = (ImageView) findViewById(R.id.img_left_menu);
        img_right_menu = (ImageView) findViewById(R.id.img_right_menu);
        img_left_menu.setOnClickListener(this);
        img_right_menu.setOnClickListener(this);
        lst_articles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ArticleData articleData = (ArticleData) lst_articles.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, ArticleDetailsActivity.class);
                intent.putExtra("selectedArticle", articleData);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_left_menu:
                setLeftRightViews(MainActivity.this, img_left_menu);
                break;
            case R.id.img_right_menu:
                setLeftRightViews(MainActivity.this, img_right_menu);
                break;
            default:
                break;

        }
    }

    /**
     * @return true is there is a network connection
     */
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onCompleted() {
        if (testController.barProgressDialog != null && testController.barProgressDialog.isShowing())
            testController.barProgressDialog.dismiss();
        Log.d("onCompleted", "Completed");
    }

    @Override
    public void onError(Throwable e) {
        if (testController.barProgressDialog != null && testController.barProgressDialog.isShowing())
            testController.barProgressDialog.dismiss();
        Log.d("onError", "onError");
    }

    @Override
    public void onNext(Object s) {
        Log.d("onNext", "" + s);
        try {
            List<ArticleData> list_data = Utils.getResponseData(s.toString());
            articleListItemAdapter = new ArticleListItemAdapter(MainActivity.this, list_data);
            lst_articles.setAdapter(articleListItemAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (testController.barProgressDialog != null && testController.barProgressDialog.isShowing())
            testController.barProgressDialog.dismiss();

    }
}
