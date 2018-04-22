package com.test.app.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.DisplayMetrics;

import com.test.app.data.ArticleData;
import com.test.app.data.MediaData;
import com.test.app.data.MediaMetaData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nagaraja Reddy on 4/22/18.
 */

public class Utils {

    static final float BITMAP_SCALE = 0.4f;
    static final float BLUR_RADIUS = 2.5f;

    //get json results from url
    public static String getJsonFromUrl(String mUrl) throws IOException {
        URL Url = new URL(mUrl);
        HttpURLConnection connection = (HttpURLConnection) Url.openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        line = sb.toString();
        connection.disconnect();
        is.close();
        sb.delete(0, sb.length());
        return line;
    }

    //get bitmap from url image
    public static Bitmap drawableFromUrl(String url) throws IOException {
        Bitmap x;
        System.out.println("Urls: " + url);
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return x;
    }

    //parse jon data

    public static List<ArticleData> getResponseData(String jsonData) {
        List<ArticleData> array_article_data = null;
        try {
            array_article_data = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(jsonData);
            if (jsonObject.has("status") && jsonObject.getString("status").equals("OK")) {

                JSONArray jsonArray = new JSONArray(jsonObject.getString("results"));

                for (int i = 0; i < jsonArray.length(); i++) {

                    ArticleData articleData = new ArticleData();

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    articleData.setUrl(jsonObject1.getString("url"));
                    articleData.setAdx_keywords(jsonObject1.getString("adx_keywords"));
                    articleData.setColumn(jsonObject1.getString("column"));
                    articleData.setSection(jsonObject1.getString("section"));

                    articleData.setByline(jsonObject1.getString("byline"));
                    articleData.setType(jsonObject1.getString("type"));
                    articleData.setTitle(jsonObject1.getString("title"));
                    articleData.setAbstract_key(jsonObject1.getString("abstract"));
                    articleData.setPublished_date(jsonObject1.getString("published_date"));
                    articleData.setSource(jsonObject1.getString("source"));
                    articleData.setId(jsonObject1.getString("id"));

                    articleData.setAsset_id(jsonObject1.getString("asset_id"));
                    articleData.setViews(jsonObject1.getString("views"));
                    articleData.setDes_facet(jsonObject1.getString("des_facet"));

                    articleData.setOrg_facet(jsonObject1.getString("org_facet"));
                    articleData.setPer_facet(jsonObject1.getString("per_facet"));
                    articleData.setGeo_facet(jsonObject1.getString("geo_facet"));

                    JSONArray jsonArray1 = new JSONArray(jsonObject1.getString("media"));

                    for (int j = 0; j < jsonArray1.length(); j++) {
                        JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                        MediaData mediaData = new MediaData();
                        mediaData.setType(jsonObject2.getString("type"));
                        mediaData.setSubtype(jsonObject2.getString("subtype"));
                        mediaData.setCaption(jsonObject2.getString("caption"));
                        mediaData.setCopyright(jsonObject2.getString("copyright"));
                        mediaData.setApproved_for_syndication(jsonObject2.getString("approved_for_syndication"));

                        JSONArray jsonArray2 = new JSONArray(jsonObject2.getString("media-metadata"));

                        for (int k = 0; k < jsonArray2.length(); k++) {
                            MediaMetaData mediaMetaData = new MediaMetaData();

                            JSONObject jsonObject3 = jsonArray2.getJSONObject(k);

                            mediaMetaData.setUrl(jsonObject3.getString("url"));
                            mediaMetaData.setFormat(jsonObject3.getString("format"));
                            mediaMetaData.setHeight(jsonObject3.getString("height"));
                            mediaMetaData.setWidth(jsonObject3.getString("width"));

                            mediaData.setArray_media_metadata(mediaMetaData);

                        }

                    }


                    array_article_data.add(articleData);
                }


            }
        } catch (JSONException je) {
            je.printStackTrace();
        }

        return array_article_data;

    }


}
