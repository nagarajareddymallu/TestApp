package com.test.app.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by welcome on 4/22/2018.
 */

public class ArticleData implements Serializable {
    private String url = null;
    private String adx_keywords = null;
    private String column = null;
    private String section = null;
    private String byline = null;

    private String type = null;
    private String title = null;
    private String abstract_key = null;
    private String published_date = null;
    private String source = null;

    private String id = null;
    private String asset_id = null;
    private String views = null;
    private String des_facet = null;
    private String org_facet = null;

    private String per_facet = null;
    private String geo_facet = null;


    private List<MediaData> array_media_data = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAdx_keywords() {
        return adx_keywords;
    }

    public void setAdx_keywords(String adx_keywords) {
        this.adx_keywords = adx_keywords;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract_key() {
        return abstract_key;
    }

    public void setAbstract_key(String abstract_key) {
        this.abstract_key = abstract_key;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getDes_facet() {
        return des_facet;
    }

    public void setDes_facet(String des_facet) {
        this.des_facet = des_facet;
    }

    public String getOrg_facet() {
        return org_facet;
    }

    public void setOrg_facet(String org_facet) {
        this.org_facet = org_facet;
    }

    public String getPer_facet() {
        return per_facet;
    }

    public void setPer_facet(String per_facet) {
        this.per_facet = per_facet;
    }

    public String getGeo_facet() {
        return geo_facet;
    }

    public void setGeo_facet(String geo_facet) {
        this.geo_facet = geo_facet;
    }

    public List<MediaData> getArray_media_data() {
        return array_media_data;
    }

    public void setArray_media_data(MediaData media_data) {
        this.array_media_data.add(media_data);
    }

}
