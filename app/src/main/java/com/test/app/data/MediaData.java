package com.test.app.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by welcome on 4/22/2018.
 */

public class MediaData implements Serializable {

    private String type = null;
    private String subtype = null;
    private String caption = null;
    private String copyright = null;
    private String approved_for_syndication = null;
    private List<MediaMetaData> array_media_metadata = new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getApproved_for_syndication() {
        return approved_for_syndication;
    }

    public void setApproved_for_syndication(String approved_for_syndication) {
        this.approved_for_syndication = approved_for_syndication;
    }

    public List<MediaMetaData> getArray_media_metadata() {
        return array_media_metadata;
    }

    public void setArray_media_metadata(MediaMetaData media_metadata) {
        this.array_media_metadata.add(media_metadata);
    }


}
