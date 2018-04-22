package com.test.app.data;

import java.io.Serializable;

/**
 * Created by welcome on 4/22/2018.
 */

public class MediaMetaData implements Serializable {

    private String url = null;
    private String format = null;
    private String height = null;
    private String width = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }


}
