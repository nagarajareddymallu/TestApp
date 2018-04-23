package com.test.app;

import android.app.ProgressDialog;
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
import com.test.app.data.ArticleData;
import com.test.app.util.Utils;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ProgressDialog barProgressDialog;

    private ImageView img_left_menu;
    private ImageView img_right_menu;
    private ListView lst_articles;

    private ArticleListItemAdapter articleListItemAdapter;

    private EditText edt_search;
    private TextView txt_title_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        if (isOnline()) {
            launchBarDialog(MainActivity.this);
            getObservable().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
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
                Intent intent = new Intent(MainActivity.this,ArticleDetailsActivity.class);
                intent.putExtra("selectedArticle",articleData);
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

    public void launchBarDialog(Context context) {
        barProgressDialog = new ProgressDialog(context);
        barProgressDialog.setTitle("Articles Request ");
        barProgressDialog.setMessage("Articles request in progress..");
        barProgressDialog.setCancelable(false);
        barProgressDialog.setCanceledOnTouchOutside(false);
        barProgressDialog.show();
    }

    Observer observer = new Observer() {
        @Override
        public void onCompleted() {
            if (barProgressDialog != null && barProgressDialog.isShowing())
                barProgressDialog.dismiss();
            Log.d("onCompleted", "Completed");
        }

        @Override
        public void onError(Throwable e) {
            if (barProgressDialog != null && barProgressDialog.isShowing())
                barProgressDialog.dismiss();
            Log.d("onError", "onError");
        }

        @Override
        public void onNext(Object s) {
            Log.d("onNext", "" + s);
            try {
                List<ArticleData> list_data = Utils.getResponseData(s.toString());
                articleListItemAdapter = new ArticleListItemAdapter(MainActivity.this,list_data);
                lst_articles.setAdapter(articleListItemAdapter);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (barProgressDialog != null && barProgressDialog.isShowing())
                barProgressDialog.dismiss();

        }
    };

    public Observable<String> getObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                //launchBarDialog(MainActivity.this);
                try {
                    String response = Utils.getJsonFromUrl(CommonConstants.ARTICAL_URL);
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                } catch (Exception ex) {
                    subscriber.onError(ex);
                }

            }
        });
    }

}
