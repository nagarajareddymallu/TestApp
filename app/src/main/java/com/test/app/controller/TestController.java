package com.test.app.controller;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.test.app.listeners.WebserviceResponse;
import com.test.app.util.Utils;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by welcome on 4/25/2018.
 */

public class TestController extends Application {

    public ProgressDialog barProgressDialog;
    private WebserviceResponse webserviceResponse;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void launchBarDialog(Context context,String title,String message) {
        barProgressDialog = new ProgressDialog(context);
        barProgressDialog.setTitle(title);
        barProgressDialog.setMessage(message);
        barProgressDialog.setCancelable(false);
        barProgressDialog.setCanceledOnTouchOutside(false);
        barProgressDialog.show();
    }

public void webserviceCall(String url,WebserviceResponse webserviceResponse)
{
    this.webserviceResponse = webserviceResponse;
    getObservable(url).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer);
}

    Observer observer = new Observer() {
        @Override
        public void onCompleted() {
            Log.d("onCompleted", "Completed");
            webserviceResponse.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            Log.d("onError", "onError");
            webserviceResponse.onError(e);
        }

        @Override
        public void onNext(Object s) {
            Log.d("onNext", "" + s);
            webserviceResponse.onNext(s);
        }
    };

    public Observable<String> getObservable(final String url) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String response = Utils.getJsonFromUrl(url);
                    subscriber.onNext(response);
                    subscriber.onCompleted();
                } catch (Exception ex) {
                    subscriber.onError(ex);
                }

            }
        });
    }

}
