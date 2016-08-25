package thibautvincent.reddit.Model.Listings;

import com.google.gson.annotations.Expose;

/**
 * Created by Thibaut on 8/23/16.
 */
public class ListingData {

    @Expose
    private String title;

    @Expose
    private String thumbnail;

    @Expose
    private String url;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
