package thibautvincent.reddit.Model.Listings;

import com.google.gson.annotations.Expose;

/**
 * Created by Thibaut on 8/23/16.
 */
public class ListingsWrapper {

    @Expose
    private String kind;

    @Expose
    private ListingsData data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public ListingsData getData() {
        return data;
    }

    public void setData(ListingsData data) {
        this.data = data;
    }
}
