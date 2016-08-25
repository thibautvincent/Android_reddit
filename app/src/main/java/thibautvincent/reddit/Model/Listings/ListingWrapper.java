package thibautvincent.reddit.Model.Listings;

import com.google.gson.annotations.Expose;

/**
 * Created by Thibaut on 8/23/16.
 */
public class ListingWrapper {

    @Expose
    private String kind;

    @Expose
    private ListingData data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public ListingData getData() {
        return data;
    }

    public void setData(ListingData data) {
        this.data = data;
    }
}
